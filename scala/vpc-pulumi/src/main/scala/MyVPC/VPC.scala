package MyVPC

import com.pulumi.Context
import com.pulumi.Pulumi
import com.pulumi.resources
import com.pulumi.resources.ComponentResource
import com.pulumi.resources.ComponentResourceOptions
import com.pulumi.aws
import com.pulumi.aws.ec2.Vpc
import com.pulumi.aws.ec2.VpcArgs

//class VPC(ty: String, name: String, args: com.pulumi.resources.ResourceArgs, opts: com.pulumi.resources.ComponentResourceOptions) extends ComponentResource(ty, name, args, opts) {
class VPC(name: String, ty: String = "VPC", opts: com.pulumi.resources.ComponentResourceOptions = null) extends ComponentResource(ty, name, opts) {
  def myVpc: Vpc = Vpc("main", VpcArgs.builder()        
            .cidrBlock("10.0.0.0/16")
            .build())
}

