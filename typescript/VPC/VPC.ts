import * as pulumi from "@pulumi/pulumi";
import * as aws from "@pulumi/aws";
import { GetVpcResult, InternetGateway, RouteTable, Subnet } from "@pulumi/aws/ec2";
import { Vpc } from "@pulumi/aws/ec2";
import { ComponentResource, Output, ResourceOptions } from "@pulumi/pulumi";
import { threadId } from "worker_threads";
import { VpcIpv4CidrBlockAssociation } from "@pulumi/aws/ec2/vpcIpv4CidrBlockAssociation";
import { stringify } from "querystring";

export class MyVPC extends ComponentResource {
  constructor(name: string, opts?: ResourceOptions) {
    super("VPC:infrastructure:base", name, {}, opts);
    this.createVPC();
    this.createIGW();
    this.prvSubNets = this.createAZsSubnets(true);
    this.pubSubNets = this.createAZsSubnets(false);
    this.createRouteTable();
    this.attachRouteTableToPubSubnets();

    this.registerOutputs({
      vpc: this.vpc,
      gw: this.gw,
      availableZones: this.availableZones,
      prvSubNets: this.prvSubNets,
      pubSubNets: this.pubSubNets,
      routeTable: this.routeTable,
      routeTableAssociation: this.routeTableAssociation,
    })
  }

  // FIELDS

  pvtSubnetsCidrs = ["10.136.0.0/27", "10.136.0.32/27" ,"10.136.0.64/27"]
  pubSubnetsCidrs = ["10.136.0.96/27", "10.136.0.128/27" ,"10.136.0.160/27"]

  public vpc?: aws.ec2.Vpc;

  public gw?: aws.ec2.InternetGateway;

  public availableZones?: pulumi.Output<aws.GetAvailabilityZonesResult>;

  public prvSubNets: Output<aws.ec2.Subnet[]>;

  public pubSubNets: Output<aws.ec2.Subnet[]>;

  public routeTable?: aws.ec2.RouteTable;

  public routeTableAssociation?: aws.ec2.RouteTableAssociation;


  // METHODS

  protected createVPC() {
    this.vpc = new Vpc("vpc_res", {
      cidrBlock: "10.136.0.0/24",
      tags: {
        Name: "myVPC-typescript",
      },
    },
    {
      parent: this,
    });
  }

  protected createIGW(){
    this.gw = new InternetGateway("gw", {
      vpcId: this.vpc?.id,
      tags: {
          Name: "myIGW-typescript",
      },
    },
    {
      parent: this.vpc,
    });
  }


  /*protected createAZsSubnets(isPvt: Boolean){
    this.availableZones = aws.getAvailabilityZonesOutput()
    pulumi.all([this.availableZones.names, this.vpc!.id]).apply(([azNames, vpcId]) => {
      let i = 0
      let listToPushInto: Subnet[] = Array<aws.ec2.Subnet>()
      azNames.forEach(azName => {
        let compName = azName + (isPvt ? "-pvt" : "-pub") + "-subnet-typescript"
        listToPushInto.push(new Subnet(compName, {
          vpcId: vpcId,
          availabilityZone: azName,
          cidrBlock: isPvt ? this.pvtSubnetsCidrs[i] : this.pubSubnetsCidrs[i],
          tags: {
            Name: compName,
          },
        },
        {
          parent: this.vpc
        }
        ));
        i++;
      });

      // attaching the route table to the pub sub nets
      if(!isPvt){
        this.pubSubNets = listToPushInto
        this.attachRouteTableToPubSubnets()
      }
      else
        this.prvSubNets = listToPushInto
        
    });
  }*/

  protected createAZsSubnets(isPvt: Boolean) : Output<Subnet[]>{
    this.availableZones = aws.getAvailabilityZonesOutput()
    return pulumi.all([this.availableZones.names, this.vpc!.id]).apply(([azNames, vpcId]) => {
      let i = 0
      let listToPushInto: Subnet[] = Array<aws.ec2.Subnet>()
      azNames.forEach(azName => {
        let fullName = azName + (isPvt ? "-pvt" : "-pub") + "-subnet-typescript"
        listToPushInto.push(new Subnet(fullName, {
          vpcId: vpcId,
          availabilityZone: azName,
          cidrBlock: isPvt ? this.pvtSubnetsCidrs[i] : this.pubSubnetsCidrs[i],
          tags: {
            Name: fullName,
          },
        },{
          parent: this.vpc
        }));
        i++;
      });
      return listToPushInto
    });
  }

  protected createRouteTable() {
    this.routeTable = new RouteTable("example", {
      vpcId: this.vpc!.id,
      routes: [
          {
              cidrBlock: "0.0.0.0/0",
              gatewayId: this.gw!.id,
          },
      ],
      tags: {
          Name: "myRouteTable-typescript",
      },
  },
  {
    parent: this.vpc,
  });
  }

  protected attachRouteTableToPubSubnets(){
    let i = 0
    this.pubSubNets.apply(subNets => {
      subNets.forEach(sn => {
        new aws.ec2.RouteTableAssociation(`${i}-routeTableAssociation-typescript`, {
          subnetId: sn.id,
          routeTableId: this.routeTable!.id,
        },
        {
          parent: this.vpc
        });
        i++
      })
    });
  }

  /*protected attachRouteTableToGateway() {
    this.routeTableAssociation = new aws.ec2.RouteTableAssociation("routeTableAssociation", {
      gatewayId: this.gw!.id,
      routeTableId: this.routeTable!.id,
    },
    {
      parent: this.vpc,
    });
  }*/
}