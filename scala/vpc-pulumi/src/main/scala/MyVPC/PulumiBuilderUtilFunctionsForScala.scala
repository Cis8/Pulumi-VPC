package MyVPC

import com.pulumi.Context
import com.pulumi.Pulumi
import com.pulumi.core.Output
import com.pulumi.resources
import com.pulumi.resources.ComponentResource
import com.pulumi.resources.ComponentResourceOptions
import com.pulumi.aws
import com.pulumi.aws.ec2.Vpc
import com.pulumi.aws.ec2.VpcArgs
import com.pulumi.aws.ec2.InternetGateway
import com.pulumi.resources.CustomResourceOptions
import com.pulumi.aws.ec2.InternetGatewayArgs
import com.pulumi.aws.ec2.AvailabilityZoneGroup
import com.pulumi.aws.AwsFunctions
import java.util.concurrent.CompletableFuture
import com.pulumi.aws.inputs.GetAvailabilityZonesArgs
import com.pulumi.aws.inputs.GetAvailabilityZonesPlainArgs
import com.pulumi.aws.outputs.GetAvailabilityZonesResult
import collection.convert.ImplicitConversionsToScala.`collection AsScalaIterable`
import com.pulumi.aws.ec2.Subnet
import com.pulumi.aws.ec2.SubnetArgs
import MyVPC.MonadPkg.given_Monad_Output
import scala.compiletime.ops.boolean
import com.pulumi.resources.Resource
import scala.compiletime.ops.string
import com.pulumi.aws.ec2.RouteTableArgs
import com.pulumi.aws.ec2.RouteTable
import com.pulumi.aws.ec2.Route
import com.pulumi.aws.ec2.inputs.RouteTableRouteArgs;
import com.pulumi.aws.ec2.outputs.RouteTableRoute
import scala.collection.JavaConverters._
import com.pulumi.aws.ec2.RouteTableAssociationArgs
import com.pulumi.aws.ec2.RouteTableAssociation

// one definition for each builder b that could be used
// could reflection solve the overloading problem? We could use a single function and use reflection at runtime to check the builder's type. Then we use a builder match to match the corresponding type
// we shall define a match with all the possible cases and use that
def cidrBlock(block: String | Output[String])(using b: VpcArgs.Builder | SubnetArgs.Builder | RouteTableRouteArgs.Builder): Unit =
  b match
    case builder: VpcArgs.Builder =>
      block match
        case o: Output[String] => builder.cidrBlock(o)
        case o: String => builder.cidrBlock(o)
    case builder: SubnetArgs.Builder =>
      block match
        case o: Output[String] => builder.cidrBlock(o)
        case o: String => builder.cidrBlock(o)
    case builder: RouteTableRouteArgs.Builder =>
      block match
        case o: Output[String] => builder.cidrBlock(o)
        case o: String => builder.cidrBlock(o)
  
  
// this is a possible overload of cidrBlock that will cause an error (ambiguous overloading call) in the cidrBlock("10.0.0.0/24") call in def myVpc
// !!!UNCOMMENT!!! // def cidrBlock(block: String | Output[String])(using b: VpcArgs): Unit = ???
def vpcId(id: String | Output[String])(using b: InternetGatewayArgs.Builder | SubnetArgs.Builder | RouteTableArgs.Builder): Unit =
  b match
    case builder: InternetGatewayArgs.Builder => 
      id match
        case o: Output[String] => builder.vpcId(o)
        case o: String => builder.vpcId(o)
    case builder: SubnetArgs.Builder => 
      id match
        case o: Output[String] => builder.vpcId(o)
        case o: String => builder.vpcId(o)
    case builder: RouteTableArgs.Builder => 
      id match
        case o: Output[String] => builder.vpcId(o)
        case o: String => builder.vpcId(o)

def availabilityZone(azName: String | Output[String])(using b: SubnetArgs.Builder): Unit =
  azName match
      case n: Output[String] => b.availabilityZone(n)
      case n: String => b.availabilityZone(n)
def gatewayId(id: String | Output[String])(using b: RouteTableRouteArgs.Builder): Unit =
  id match
    case i: Output[String] => b.gatewayId(i)
    case i: String => b.gatewayId(i)
  
def routes(routes: List[RouteTableRouteArgs] | Output[java.util.List[RouteTableRouteArgs]])(using b: RouteTableArgs.Builder): Unit =
  routes match
    case n: Output[java.util.List[RouteTableRouteArgs]] => b.routes(n) // why this warning?
    case n: List[RouteTableRouteArgs] => b.routes(n.asJava) // why this warning?

def subnetId(id: String | Output[String])(using b: RouteTableAssociationArgs.Builder): Unit =
  id match
    case i: Output[String] => b.subnetId(i)
    case i: String => b.subnetId(i)
  
def routeTableId(id: String | Output[String])(using b: RouteTableAssociationArgs.Builder): Unit =
  id match
    case i: Output[String] => b.routeTableId(i)
    case i: String => b.routeTableId(i)
    
def parent(parent: Resource)(using ob: CustomResourceOptions.Builder): Unit =
  ob.parent(parent)
