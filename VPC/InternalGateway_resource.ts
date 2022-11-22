import * as pulumi from "@pulumi/pulumi";
import * as aws from "@pulumi/aws";
import { VPC } from './VPC_resource';
import { Vpc } from "@pulumi/aws/ec2";


export class InternalGateway{
  constructor(vpc: VPC){
    this.myVpc = vpc;
  }

  myVpc: VPC;
  gw?: aws.ec2.InternetGateway;

  CreateIGW(){
    this.gw = new aws.ec2.InternetGateway("gw", {
      vpcId: this.myVpc.vpc?.id,
      tags: {
          Name: "IGW",
      },
  });
  }
}
