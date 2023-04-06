package com.cisotto.myvpc

import com.cisotto.myvpc.function.*
import com.cisotto.myvpc.builder.*
import com.cisotto.myvpc.builder.tupleToMap
import com.cisotto.myvpc.builder.elemToList
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
import com.pulumi.resources.ResourceOptions

class VPC(ty: String, name: String, opts: ComponentResourceOptions = ComponentResourceOptions.builder().build()) extends ComponentResource(ty, name, opts) {

  val pvtSubnetsCidrs: List[String] = List("10.136.0.0/27", "10.136.0.32/27", "10.136.0.64/27")
  val pubSubnetsCidrs: List[String] = List("10.136.0.96/27", "10.136.0.128/27", "10.136.0.160/27")
    

  // the builder is implicit inside init (the { } block)
  // PROBLEM: the scala compiler doesn't know how to unambiguate the call to a cidrBlock with a given builder in return an another with another
  val myVpc = vpc("scala-main") ({
    cidrBlock("10.136.0.0/24")
    tags("Name" -> "myVpcScala")
  },{
    parent(this)
  })

  //val opt = Some(CustomResourceOptions.builder().parent(myVpc).build())
  /*{
    parent(this) // why doesn't work?
  }*/
  
  val myIGW = internetGateway("gw") ({
    vpcId(myVpc.getId())
    tags("Name" -> "myIGWScala")
  },{
    parent(myVpc)
  })

  val pvtSubnets = createAzSubnets(true) // Output[Iterable[Subnet]]

  val pubSubnets = createAzSubnets(false) // Output[Iterable[Subnet]]

  val myRouteTable = routeTable("myRouteTable") ({
    vpcId(myVpc.getId())
    routes(
      routeTableRouteArgs(){
        cidrBlock("0.0.0.0/0")
        gatewayId(myIGW.getId())
      })
    tags("Name" -> "myRouteTableScala")
  },{
    parent(myVpc)
  })

  val routeTableAssociations = attachRouteTableToPubSubnets() // Output[Iterable[RouteTableAssociation]]

  def createAzSubnets(isPvt: Boolean) =
      for
        azRes <- availabilityZonesNames()
        myVpcId <- myVpc.id()
        tuples = azRes.names().zip(if isPvt then pvtSubnetsCidrs else pubSubnetsCidrs)
      yield
        tuples.map((name, cidr) => {
          val fullName = name + "-" + (if isPvt then "pvt" else "pub") + "-subnet-scala"
          subnet(fullName) ({
            vpcId(myVpcId)
            availabilityZone(name)
            cidrBlock(cidr)
            tags("Name" -> fullName)
          },{
            parent(myVpc)
          })
      })

  def attachRouteTableToPubSubnets() = // Output[Iterable[RouteTableAssociation]] 
        for
          subnets <- pubSubnets
          tuples = subnets.zipWithIndex
        yield
          tuples.map((ps, idx) => routeTableAssociation(idx + "-assoc-scala") ({
            subnetId(ps.getId())
            routeTableId(myRouteTable.getId())
          }, {
            parent(myVpc)
          }))
}
