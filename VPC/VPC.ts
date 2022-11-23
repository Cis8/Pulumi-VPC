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

export class VPC extends ComponentResource {
  constructor(name: string, opts?: ResourceOptions) {
    super("VPC:infrastructure:base", name, {}, opts);
    this.createVPC();
    this.createIGW();
    this.createAZsSubnets();

    this.registerOutputs({
      vpc: this.vpc,
      gw: this.gw,
      availableZones: this.availableZones,
    })
  }

  // FIELDS

  pvtSubnetsCidrs = ["10.136.0.0/27", "10.136.0.32/27" ,"10.136.0.64/27"]
  pubSubnetsCidrs = ["10.136.0.96/27", "10.136.0.128/27" ,"10.136.0.160/27"]

  public vpc?: aws.ec2.Vpc;

  public gw?: aws.ec2.InternetGateway;

  public availableZones?: pulumi.Output<aws.GetAvailabilityZonesResult>;


  // METHODS

  protected createVPC() {
    this.vpc = new aws.ec2.Vpc("vpc_res", {
      cidrBlock: "10.136.0.0/24",
      tags: {
        Name: "main",
    },
    },{
    parent: this,
    });
  }

  protected createIGW(){
    this.gw = new aws.ec2.InternetGateway("gw", {
      vpcId: this.vpc?.id,
      tags: {
          Name: "IGW",
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
        new Subnet(azName + "-pvt-subnet", {
          vpcId: vpcId,
          availabilityZone: azName,
          cidrBlock: this.pvtSubnetsCidrs[i]
        },
        {
          parent: this,
        }
        );

        new Subnet(azName + "-pub-subnet", {
          vpcId: vpcId,
          availabilityZone: azName,
          cidrBlock: this.pubSubnetsCidrs[i]
        },
        {
          parent: this,
        }
        );
        i++;
      });
    });
  }
}