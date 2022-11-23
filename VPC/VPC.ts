// CIDR: 10.136.0.0/24
// CIDRa: 10.136.0.1/24
// CIDRb: 10.136.0.2/24
// CIDRc: 10.136.0.3/24

import * as pulumi from "@pulumi/pulumi";
import * as aws from "@pulumi/aws";
import { GetVpcResult, Subnet } from "@pulumi/aws/ec2";
import { Vpc } from "@pulumi/aws/ec2";
import { ComponentResource, Output, ResourceOptions } from "@pulumi/pulumi";
import { threadId } from "worker_threads";
import { VpcIpv4CidrBlockAssociation } from "@pulumi/aws/ec2/vpcIpv4CidrBlockAssociation";
import { stringify } from "querystring";

export class VPC extends ComponentResource {
  constructor(name: string, opts?: ResourceOptions) {
    super("VPC:infrastructure:base", name, {}, opts);
    this.pubSubNets = Array<aws.ec2.Subnet>();
    this.prvSubNets = Array<aws.ec2.Subnet>();
    this.createVPC();
    this.createIGW();
    this.createAZsSubnets();
    this.createRouteTable();
    //this.attachRouteTableToPubSubnets();

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

  public prvSubNets: aws.ec2.Subnet[];

  public pubSubNets: aws.ec2.Subnet[];

  public routeTable?: aws.ec2.RouteTable;

  public routeTableAssociation?: aws.ec2.RouteTableAssociation;


  // METHODS

  protected createVPC() {
    this.vpc = new aws.ec2.Vpc("vpc_res", {
      cidrBlock: "10.136.0.0/24",
      tags: {
        Name: "myVPC",
    },
    },{
    parent: this,
    });
  }

  protected createIGW(){
    this.gw = new aws.ec2.InternetGateway("gw", {
      vpcId: this.vpc?.id,
      tags: {
          Name: "myIGW",
      },
    },
    {
      parent: this,
    });
  }


  protected createAZsSubnets(){
    this.availableZones = aws.getAvailabilityZonesOutput();
    pulumi.all([this.availableZones.names, this.vpc!.id]).apply(([azNames, vpcId]) => {
    //this.availableZones.names.apply((azNames: string[]) => {
      let i = 0
      azNames.forEach(azName => {
        this.prvSubNets.push(new Subnet(azName + "-pvt-subnet", {
          vpcId: vpcId,
          availabilityZone: azName,
          cidrBlock: this.pvtSubnetsCidrs[i]
        },
        {
          parent: this,
        }
        ));

        this.pubSubNets.push(new Subnet(azName + "-pub-subnet", {
          vpcId: vpcId,
          availabilityZone: azName,
          cidrBlock: this.pubSubnetsCidrs[i]
        },
        {
          parent: this,
        }
        ));
        i++;
      });

      // attaching the route table to the sub net
      this.attachRouteTableToPubSubnets()
    });
  }

  protected createRouteTable() {
    this.routeTable = new aws.ec2.RouteTable("example", {
      vpcId: this.vpc!.id,
      routes: [
          {
              cidrBlock: "0.0.0.0/0",
              gatewayId: this.gw!.id,
          },
      ],
      tags: {
          Name: "myRouteTable",
      },
  },
  {
    parent: this,
  });
  }

  protected attachRouteTableToPubSubnets(){
    let i = 0
    //console.log(`this.pubSubNets length: ${this.pubSubNets.length}`)
    this.pubSubNets.forEach(subNet => {
      new aws.ec2.RouteTableAssociation(`${i}-routeTableAssociation`, {
        subnetId: subNet.id,
        routeTableId: this.routeTable!.id,
      });
      i++
    });
  }

  /*protected attachRouteTableToGateway() {
    this.routeTableAssociation = new aws.ec2.RouteTableAssociation("routeTableAssociation", {
      gatewayId: this.gw!.id,
      routeTableId: this.routeTable!.id,
    },
    {
      parent: this,
    });
  }*/
}