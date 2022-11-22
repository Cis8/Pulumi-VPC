// CIDR: 10.136.0.0/16

import * as pulumi from "@pulumi/pulumi";
import * as aws from "@pulumi/aws";
import { GetVpcResult } from "@pulumi/aws/ec2";

export class VPC {
  constructor(){
  }

  public vpc?: aws.ec2.Vpc;

  getName(){return "";}

  CreateVPC() {
    this.vpc = new aws.ec2.Vpc("vpc_res", {
      cidrBlock: "10.136.0.0/16",
      tags: {
        Name: "main",
    },
  });
  }


}
