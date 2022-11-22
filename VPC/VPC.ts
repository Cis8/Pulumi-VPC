// CIDR: 10.136.0.0/16

import * as pulumi from "@pulumi/pulumi";
import * as aws from "@pulumi/aws";
import { GetVpcResult } from "@pulumi/aws/ec2";
import { Vpc } from "@pulumi/aws/ec2";
import { ComponentResource, ResourceOptions } from "@pulumi/pulumi";

export class VPC extends ComponentResource {
  constructor(name: string, opts?: ResourceOptions) {
    super("VPC:infrastructure:base", name, {}, opts);
    this.CreateVPC();
    this.CreateIGW();

    this.registerOutputs({
      vpc: this.vpc,
      gw: this.gw
    })
  }

  public vpc?: aws.ec2.Vpc;

  public gw?: aws.ec2.InternetGateway;

  public CreateVPC() {
    this.vpc = new aws.ec2.Vpc("vpc_res", {
      cidrBlock: "10.136.0.0/16",
      tags: {
        Name: "main",
    },
    },{
    parent: this,
    });
  }

  public CreateIGW(){
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


}