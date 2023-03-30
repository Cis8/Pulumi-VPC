package com.cisotto.myvpc

import com.cisotto.myvpc.function.*
import com.cisotto.myvpc.builder.*
import com.pulumi.aws.ec2.{InternetGateway, RouteTableAssociation, Vpc}
import com.pulumi.core.Output
import com.pulumi.aws
import com.pulumi.aws.outputs.GetAvailabilityZonesResult
import com.cisotto.myvpc.monad.*

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
import com.cisotto.myvpc.monad.given_Monad_Output
import com.cisotto.myvpc.functor.given_Functor_Output

class VPC(name: String, ty: String = "VPC") {

  val pvtSubnetsCidrs: List[String] = List("10.136.0.0/27", "10.136.0.32/27", "10.136.0.64/27")
  val pubSubnetsCidrs: List[String] = List("10.136.0.96/27", "10.136.0.128/27", "10.136.0.160/27")
    

  // the builder is implicit inside init (the { } block)
  // PROBLEM: the scala compiler doesn't know how to unambiguate the call to a cidrBlock with a given builder in return an another with another
  val myVpc: Vpc = vpc("scala-main") {
    cidrBlock("10.0.0.0/24")
  }
  /*{
    parent(this) // why doesn't work?
  }*/
  
  val myIGW: InternetGateway = internetGateway("gw") {
    vpcId(myVpc.getId())
    //tags(java.util.Map.of("Name", "myIGW")) // can't use Scala map?
  }

  val myRouteTable = routeTable("myRouteTable"){
    vpcId(myVpc.getId())
    routes(List(
      routeTableRouteArgs(){
        cidrBlock("0.0.0.0/0")
        gatewayId(myIGW.getId())
      }
    ))
  }

  val pvtSubnets: Output[Iterable[aws.ec2.Subnet]] = createAzSubnets(true)

  val pubSubnets: Output[Iterable[aws.ec2.Subnet]] = createAzSubnets(false)

  val routeTableAssociations: Output[Iterable[RouteTableAssociation]] = attachRouteTableToPubSubnets()

  def createAzSubnets(isPvt: Boolean) =
    availabilityZonesNames().map((az: GetAvailabilityZonesResult) =>
      for
        (name, cidr) <- az.names().zip(if isPvt then pvtSubnetsCidrs else pubSubnetsCidrs)
      yield
        subnet(name + "-" + (if isPvt then "pvt" else "pub") + "-subnet"){
          vpcId(myVpc.getId())
          availabilityZone(name)
          cidrBlock(cidr)
        }
    )



  def attachRouteTableToPubSubnets(): Output[Iterable[RouteTableAssociation]] =
    pubSubnets.map((subnets: Iterable[Subnet]) =>
        for
          (ps, idx) <- subnets.zipWithIndex
        yield
          routeTableAssociation(idx + "-assoc"){
            subnetId(ps.getId())
            routeTableId(myRouteTable.getId())
          }
      )
}
