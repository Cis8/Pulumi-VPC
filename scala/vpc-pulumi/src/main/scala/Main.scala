import com.pulumi.Context
import com.pulumi.Pulumi
import com.pulumi.aws.ec2.Vpc
import com.pulumi.aws.ec2.VpcArgs
import scala.compiletime.ops.string

import MyVPC.VPC

@main def hello: Unit = 
  println("Hello world!")
  println("Object instantiated...")
  Pulumi.run(stack)

def stack(ctx: Context) = 
        VPC("My Custom VPC")
        
