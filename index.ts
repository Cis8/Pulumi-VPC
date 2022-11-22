import * as pulumi from "@pulumi/pulumi";
import * as aws from "@pulumi/aws";
import * as awsx from "@pulumi/awsx";
import { VPC } from './VPC/VPC_resource';
import { InternalGateway } from './VPC/InternalGateway_resource';

const vpc = new VPC();
vpc.CreateVPC();

const igw = new InternalGateway(vpc);
igw.CreateIGW();

