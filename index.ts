import * as pulumi from "@pulumi/pulumi";
import * as aws from "@pulumi/aws";
import * as awsx from "@pulumi/awsx";
import { VPC } from './VPC/VPC_resource';

// Create an AWS resource (S3 Bucket)
const bucket = new aws.s3.Bucket("my-bucket");

// Export the name of the bucket
export const bucketName = bucket.id;

const vpc = new VPC();
// Export the name of the bucket
export const vpcName = vpc;
vpc.CreateVPC();

