package com.cisotto.myvpc

import com.pulumi.Context
import com.pulumi.Pulumi
import com.pulumi.aws.ec2.Vpc
import com.pulumi.aws.ec2.VpcArgs
import scala.compiletime.ops.string


@main def hello: Unit = 
  Pulumi.run(stack)

def stack(ctx: Context) = 
        VPC("VPC", "My Custom scala VPC")
        
