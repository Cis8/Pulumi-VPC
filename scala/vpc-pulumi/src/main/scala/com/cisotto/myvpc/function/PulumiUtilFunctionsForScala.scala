package com.cisotto.myvpc.function

/*import com.cisotto.myvpc.monad.given_Monad_Output
import com.pulumi.Context
import com.pulumi.Pulumi
import com.pulumi.core.Output
import com.pulumi.resources
import com.pulumi.resources.ComponentResource
import com.pulumi.resources.ComponentResourceOptions
import com.pulumi.resources.CustomResourceOptions
import com.pulumi.resources.Resource
import scala.collection.JavaConverters._
import collection.convert.ImplicitConversionsToScala.`collection AsScalaIterable`
import scala.compiletime.ops.boolean
import scala.compiletime.ops.string
import com.pulumi.aws


import com.pulumi.aws.AwsFunctions
import com.pulumi.aws.ec2.Vpc
import com.pulumi.aws.ec2.VpcArgs
import com.pulumi.aws.ec2.InternetGateway
import com.pulumi.aws.ec2.InternetGatewayArgs
//import com.pulumi.aws.ec2.AvailabilityZoneGroup
//import java.util.concurrent.CompletableFuture
import com.pulumi.aws.inputs.GetAvailabilityZonesArgs
//import com.pulumi.aws.inputs.GetAvailabilityZonesPlainArgs
import com.pulumi.aws.outputs.GetAvailabilityZonesResult
import com.pulumi.aws.ec2.Subnet
import com.pulumi.aws.ec2.SubnetArgs
import com.pulumi.aws.ec2.RouteTableArgs
import com.pulumi.aws.ec2.RouteTable
//import com.pulumi.aws.ec2.Route
import com.pulumi.aws.ec2.inputs.RouteTableRouteArgs;
import com.pulumi.aws.ec2.outputs.RouteTableRoute
import com.pulumi.aws.ec2.RouteTableAssociationArgs
import com.pulumi.aws.ec2.RouteTableAssociation


// implicit builder b is used inside init
def vpc(name: String, optOptions: Option[CustomResourceOptions] = None)(init: VpcArgs.Builder ?=> Unit): Vpc =
  given b: VpcArgs.Builder = VpcArgs.builder()
  init
  optOptions.map(o => 
    new Vpc(name, b.build(), o)).getOrElse(new Vpc(name, b.build()))
  
def igw(name: String, optOptions: Option[CustomResourceOptions] = None)(init: InternetGatewayArgs.Builder ?=> Unit): InternetGateway = // rename igw
  given b: InternetGatewayArgs.Builder = InternetGatewayArgs.builder()
  init
  optOptions.map( o => 
    new InternetGateway(name, b.build(), o)).getOrElse(new InternetGateway(name, b.build()))
  

def subnet(name: String)(init: SubnetArgs.Builder ?=> Unit): Subnet =
  given b: SubnetArgs.Builder = SubnetArgs.builder()
  init
  Subnet(name, b.build())
def routeTableRouteArgs()(init: RouteTableRouteArgs.Builder ?=> Unit): RouteTableRouteArgs =
  given b: RouteTableRouteArgs.Builder = RouteTableRouteArgs.builder()
  init
  b.build()

def routeTableAssociation(assocName: String)(init: RouteTableAssociationArgs.Builder ?=> Unit): RouteTableAssociation =
  given b: RouteTableAssociationArgs.Builder = RouteTableAssociationArgs.builder()
  init
  RouteTableAssociation(assocName, b.build())

def routeTable(name: String)(init: RouteTableArgs.Builder ?=> Unit): RouteTable =
  given b: RouteTableArgs.Builder = RouteTableArgs.builder()
  init
  RouteTable(name, b.build())

def azNames(): Output[GetAvailabilityZonesResult] = AwsFunctions.getAvailabilityZones(GetAvailabilityZonesArgs.builder()
  .state("available")
  .build())
*/

import com.pulumi.Context
import com.pulumi.Pulumi
import com.pulumi.core.Output
import com.pulumi.resources.{CustomResourceOptions, Resource}
import scala.collection.JavaConverters._
import collection.convert.ImplicitConversionsToScala.`collection AsScalaIterable`
import scala.compiletime.ops.boolean
import scala.compiletime.ops.string
import scala.language.implicitConversions

def baseOpts(using o: CustomResourceOptions.Builder) : Unit = {} 

def ami(param: String)(init: com.pulumi.aws.ec2.AmiArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.Ami =
	given b: com.pulumi.aws.ec2.AmiArgs.Builder= com.pulumi.aws.ec2.AmiArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.Ami(param, b.build(), bo.build())

def amiCopy(param: String)(init: com.pulumi.aws.ec2.AmiCopyArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.AmiCopy =
	given b: com.pulumi.aws.ec2.AmiCopyArgs.Builder= com.pulumi.aws.ec2.AmiCopyArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.AmiCopy(param, b.build(), bo.build())

def amiFromInstance(param: String)(init: com.pulumi.aws.ec2.AmiFromInstanceArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.AmiFromInstance =
	given b: com.pulumi.aws.ec2.AmiFromInstanceArgs.Builder= com.pulumi.aws.ec2.AmiFromInstanceArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.AmiFromInstance(param, b.build(), bo.build())

def amiLaunchPermission(param: String)(init: com.pulumi.aws.ec2.AmiLaunchPermissionArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.AmiLaunchPermission =
	given b: com.pulumi.aws.ec2.AmiLaunchPermissionArgs.Builder= com.pulumi.aws.ec2.AmiLaunchPermissionArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.AmiLaunchPermission(param, b.build(), bo.build())

def availabilityZoneGroup(param: String)(init: com.pulumi.aws.ec2.AvailabilityZoneGroupArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.AvailabilityZoneGroup =
	given b: com.pulumi.aws.ec2.AvailabilityZoneGroupArgs.Builder= com.pulumi.aws.ec2.AvailabilityZoneGroupArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.AvailabilityZoneGroup(param, b.build(), bo.build())

def capacityReservation(param: String)(init: com.pulumi.aws.ec2.CapacityReservationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.CapacityReservation =
	given b: com.pulumi.aws.ec2.CapacityReservationArgs.Builder= com.pulumi.aws.ec2.CapacityReservationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.CapacityReservation(param, b.build(), bo.build())

def carrierGateway(param: String)(init: com.pulumi.aws.ec2.CarrierGatewayArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.CarrierGateway =
	given b: com.pulumi.aws.ec2.CarrierGatewayArgs.Builder= com.pulumi.aws.ec2.CarrierGatewayArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.CarrierGateway(param, b.build(), bo.build())

def customerGateway(param: String)(init: com.pulumi.aws.ec2.CustomerGatewayArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.CustomerGateway =
	given b: com.pulumi.aws.ec2.CustomerGatewayArgs.Builder= com.pulumi.aws.ec2.CustomerGatewayArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.CustomerGateway(param, b.build(), bo.build())

def dedicatedHost(param: String)(init: com.pulumi.aws.ec2.DedicatedHostArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.DedicatedHost =
	given b: com.pulumi.aws.ec2.DedicatedHostArgs.Builder= com.pulumi.aws.ec2.DedicatedHostArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.DedicatedHost(param, b.build(), bo.build())

def defaultNetworkAcl(param: String)(init: com.pulumi.aws.ec2.DefaultNetworkAclArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.DefaultNetworkAcl =
	given b: com.pulumi.aws.ec2.DefaultNetworkAclArgs.Builder= com.pulumi.aws.ec2.DefaultNetworkAclArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.DefaultNetworkAcl(param, b.build(), bo.build())

def defaultRouteTable(param: String)(init: com.pulumi.aws.ec2.DefaultRouteTableArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.DefaultRouteTable =
	given b: com.pulumi.aws.ec2.DefaultRouteTableArgs.Builder= com.pulumi.aws.ec2.DefaultRouteTableArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.DefaultRouteTable(param, b.build(), bo.build())

def defaultSecurityGroup(param: String)(init: com.pulumi.aws.ec2.DefaultSecurityGroupArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.DefaultSecurityGroup =
	given b: com.pulumi.aws.ec2.DefaultSecurityGroupArgs.Builder= com.pulumi.aws.ec2.DefaultSecurityGroupArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.DefaultSecurityGroup(param, b.build(), bo.build())

def defaultSubnet(param: String)(init: com.pulumi.aws.ec2.DefaultSubnetArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.DefaultSubnet =
	given b: com.pulumi.aws.ec2.DefaultSubnetArgs.Builder= com.pulumi.aws.ec2.DefaultSubnetArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.DefaultSubnet(param, b.build(), bo.build())

def defaultVpc(param: String)(init: com.pulumi.aws.ec2.DefaultVpcArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.DefaultVpc =
	given b: com.pulumi.aws.ec2.DefaultVpcArgs.Builder= com.pulumi.aws.ec2.DefaultVpcArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.DefaultVpc(param, b.build(), bo.build())

def defaultVpcDhcpOptions(param: String)(init: com.pulumi.aws.ec2.DefaultVpcDhcpOptionsArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.DefaultVpcDhcpOptions =
	given b: com.pulumi.aws.ec2.DefaultVpcDhcpOptionsArgs.Builder= com.pulumi.aws.ec2.DefaultVpcDhcpOptionsArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.DefaultVpcDhcpOptions(param, b.build(), bo.build())

def egressOnlyInternetGateway(param: String)(init: com.pulumi.aws.ec2.EgressOnlyInternetGatewayArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.EgressOnlyInternetGateway =
	given b: com.pulumi.aws.ec2.EgressOnlyInternetGatewayArgs.Builder= com.pulumi.aws.ec2.EgressOnlyInternetGatewayArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.EgressOnlyInternetGateway(param, b.build(), bo.build())

def eip(param: String)(init: com.pulumi.aws.ec2.EipArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.Eip =
	given b: com.pulumi.aws.ec2.EipArgs.Builder= com.pulumi.aws.ec2.EipArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.Eip(param, b.build(), bo.build())

def eipAssociation(param: String)(init: com.pulumi.aws.ec2.EipAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.EipAssociation =
	given b: com.pulumi.aws.ec2.EipAssociationArgs.Builder= com.pulumi.aws.ec2.EipAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.EipAssociation(param, b.build(), bo.build())

def fleet(param: String)(init: com.pulumi.aws.ec2.FleetArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.Fleet =
	given b: com.pulumi.aws.ec2.FleetArgs.Builder= com.pulumi.aws.ec2.FleetArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.Fleet(param, b.build(), bo.build())

def flowLog(param: String)(init: com.pulumi.aws.ec2.FlowLogArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.FlowLog =
	given b: com.pulumi.aws.ec2.FlowLogArgs.Builder= com.pulumi.aws.ec2.FlowLogArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.FlowLog(param, b.build(), bo.build())

def instance(param: String)(init: com.pulumi.aws.ec2.InstanceArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.Instance =
	given b: com.pulumi.aws.ec2.InstanceArgs.Builder= com.pulumi.aws.ec2.InstanceArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.Instance(param, b.build(), bo.build())

def internetGateway(param: String)(init: com.pulumi.aws.ec2.InternetGatewayArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.InternetGateway =
	given b: com.pulumi.aws.ec2.InternetGatewayArgs.Builder= com.pulumi.aws.ec2.InternetGatewayArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.InternetGateway(param, b.build(), bo.build())

def internetGatewayAttachment(param: String)(init: com.pulumi.aws.ec2.InternetGatewayAttachmentArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.InternetGatewayAttachment =
	given b: com.pulumi.aws.ec2.InternetGatewayAttachmentArgs.Builder= com.pulumi.aws.ec2.InternetGatewayAttachmentArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.InternetGatewayAttachment(param, b.build(), bo.build())

def keyPair(param: String)(init: com.pulumi.aws.ec2.KeyPairArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.KeyPair =
	given b: com.pulumi.aws.ec2.KeyPairArgs.Builder= com.pulumi.aws.ec2.KeyPairArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.KeyPair(param, b.build(), bo.build())

def launchConfiguration(param: String)(init: com.pulumi.aws.ec2.LaunchConfigurationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.LaunchConfiguration =
	given b: com.pulumi.aws.ec2.LaunchConfigurationArgs.Builder= com.pulumi.aws.ec2.LaunchConfigurationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.LaunchConfiguration(param, b.build(), bo.build())

def launchTemplate(param: String)(init: com.pulumi.aws.ec2.LaunchTemplateArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.LaunchTemplate =
	given b: com.pulumi.aws.ec2.LaunchTemplateArgs.Builder= com.pulumi.aws.ec2.LaunchTemplateArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.LaunchTemplate(param, b.build(), bo.build())

def localGatewayRoute(param: String)(init: com.pulumi.aws.ec2.LocalGatewayRouteArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.LocalGatewayRoute =
	given b: com.pulumi.aws.ec2.LocalGatewayRouteArgs.Builder= com.pulumi.aws.ec2.LocalGatewayRouteArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.LocalGatewayRoute(param, b.build(), bo.build())

def localGatewayRouteTableVpcAssociation(param: String)(init: com.pulumi.aws.ec2.LocalGatewayRouteTableVpcAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.LocalGatewayRouteTableVpcAssociation =
	given b: com.pulumi.aws.ec2.LocalGatewayRouteTableVpcAssociationArgs.Builder= com.pulumi.aws.ec2.LocalGatewayRouteTableVpcAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.LocalGatewayRouteTableVpcAssociation(param, b.build(), bo.build())

def mainRouteTableAssociation(param: String)(init: com.pulumi.aws.ec2.MainRouteTableAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.MainRouteTableAssociation =
	given b: com.pulumi.aws.ec2.MainRouteTableAssociationArgs.Builder= com.pulumi.aws.ec2.MainRouteTableAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.MainRouteTableAssociation(param, b.build(), bo.build())

def managedPrefixList(param: String)(init: com.pulumi.aws.ec2.ManagedPrefixListArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.ManagedPrefixList =
	given b: com.pulumi.aws.ec2.ManagedPrefixListArgs.Builder= com.pulumi.aws.ec2.ManagedPrefixListArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.ManagedPrefixList(param, b.build(), bo.build())

def managedPrefixListEntry(param: String)(init: com.pulumi.aws.ec2.ManagedPrefixListEntryArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.ManagedPrefixListEntry =
	given b: com.pulumi.aws.ec2.ManagedPrefixListEntryArgs.Builder= com.pulumi.aws.ec2.ManagedPrefixListEntryArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.ManagedPrefixListEntry(param, b.build(), bo.build())

def natGateway(param: String)(init: com.pulumi.aws.ec2.NatGatewayArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.NatGateway =
	given b: com.pulumi.aws.ec2.NatGatewayArgs.Builder= com.pulumi.aws.ec2.NatGatewayArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.NatGateway(param, b.build(), bo.build())

def networkAcl(param: String)(init: com.pulumi.aws.ec2.NetworkAclArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.NetworkAcl =
	given b: com.pulumi.aws.ec2.NetworkAclArgs.Builder= com.pulumi.aws.ec2.NetworkAclArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.NetworkAcl(param, b.build(), bo.build())

def networkAclAssociation(param: String)(init: com.pulumi.aws.ec2.NetworkAclAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.NetworkAclAssociation =
	given b: com.pulumi.aws.ec2.NetworkAclAssociationArgs.Builder= com.pulumi.aws.ec2.NetworkAclAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.NetworkAclAssociation(param, b.build(), bo.build())

def networkAclRule(param: String)(init: com.pulumi.aws.ec2.NetworkAclRuleArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.NetworkAclRule =
	given b: com.pulumi.aws.ec2.NetworkAclRuleArgs.Builder= com.pulumi.aws.ec2.NetworkAclRuleArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.NetworkAclRule(param, b.build(), bo.build())

def networkInsightsAnalysis(param: String)(init: com.pulumi.aws.ec2.NetworkInsightsAnalysisArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.NetworkInsightsAnalysis =
	given b: com.pulumi.aws.ec2.NetworkInsightsAnalysisArgs.Builder= com.pulumi.aws.ec2.NetworkInsightsAnalysisArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.NetworkInsightsAnalysis(param, b.build(), bo.build())

def networkInsightsPath(param: String)(init: com.pulumi.aws.ec2.NetworkInsightsPathArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.NetworkInsightsPath =
	given b: com.pulumi.aws.ec2.NetworkInsightsPathArgs.Builder= com.pulumi.aws.ec2.NetworkInsightsPathArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.NetworkInsightsPath(param, b.build(), bo.build())

def networkInterface(param: String)(init: com.pulumi.aws.ec2.NetworkInterfaceArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.NetworkInterface =
	given b: com.pulumi.aws.ec2.NetworkInterfaceArgs.Builder= com.pulumi.aws.ec2.NetworkInterfaceArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.NetworkInterface(param, b.build(), bo.build())

def networkInterfaceAttachment(param: String)(init: com.pulumi.aws.ec2.NetworkInterfaceAttachmentArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.NetworkInterfaceAttachment =
	given b: com.pulumi.aws.ec2.NetworkInterfaceAttachmentArgs.Builder= com.pulumi.aws.ec2.NetworkInterfaceAttachmentArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.NetworkInterfaceAttachment(param, b.build(), bo.build())

def networkInterfaceSecurityGroupAttachment(param: String)(init: com.pulumi.aws.ec2.NetworkInterfaceSecurityGroupAttachmentArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.NetworkInterfaceSecurityGroupAttachment =
	given b: com.pulumi.aws.ec2.NetworkInterfaceSecurityGroupAttachmentArgs.Builder= com.pulumi.aws.ec2.NetworkInterfaceSecurityGroupAttachmentArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.NetworkInterfaceSecurityGroupAttachment(param, b.build(), bo.build())

def peeringConnectionOptions(param: String)(init: com.pulumi.aws.ec2.PeeringConnectionOptionsArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.PeeringConnectionOptions =
	given b: com.pulumi.aws.ec2.PeeringConnectionOptionsArgs.Builder= com.pulumi.aws.ec2.PeeringConnectionOptionsArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.PeeringConnectionOptions(param, b.build(), bo.build())

def placementGroup(param: String)(init: com.pulumi.aws.ec2.PlacementGroupArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.PlacementGroup =
	given b: com.pulumi.aws.ec2.PlacementGroupArgs.Builder= com.pulumi.aws.ec2.PlacementGroupArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.PlacementGroup(param, b.build(), bo.build())

def proxyProtocolPolicy(param: String)(init: com.pulumi.aws.ec2.ProxyProtocolPolicyArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.ProxyProtocolPolicy =
	given b: com.pulumi.aws.ec2.ProxyProtocolPolicyArgs.Builder= com.pulumi.aws.ec2.ProxyProtocolPolicyArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.ProxyProtocolPolicy(param, b.build(), bo.build())

def route(param: String)(init: com.pulumi.aws.ec2.RouteArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.Route =
	given b: com.pulumi.aws.ec2.RouteArgs.Builder= com.pulumi.aws.ec2.RouteArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.Route(param, b.build(), bo.build())

def routeTable(param: String)(init: com.pulumi.aws.ec2.RouteTableArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.RouteTable =
	given b: com.pulumi.aws.ec2.RouteTableArgs.Builder= com.pulumi.aws.ec2.RouteTableArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.RouteTable(param, b.build(), bo.build())

def routeTableAssociation(param: String)(init: com.pulumi.aws.ec2.RouteTableAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.RouteTableAssociation =
	given b: com.pulumi.aws.ec2.RouteTableAssociationArgs.Builder= com.pulumi.aws.ec2.RouteTableAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.RouteTableAssociation(param, b.build(), bo.build())

def securityGroup(param: String)(init: com.pulumi.aws.ec2.SecurityGroupArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.SecurityGroup =
	given b: com.pulumi.aws.ec2.SecurityGroupArgs.Builder= com.pulumi.aws.ec2.SecurityGroupArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.SecurityGroup(param, b.build(), bo.build())

def securityGroupAssociation(param: String)(init: com.pulumi.aws.ec2.SecurityGroupAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.SecurityGroupAssociation =
	given b: com.pulumi.aws.ec2.SecurityGroupAssociationArgs.Builder= com.pulumi.aws.ec2.SecurityGroupAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.SecurityGroupAssociation(param, b.build(), bo.build())

def securityGroupRule(param: String)(init: com.pulumi.aws.ec2.SecurityGroupRuleArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.SecurityGroupRule =
	given b: com.pulumi.aws.ec2.SecurityGroupRuleArgs.Builder= com.pulumi.aws.ec2.SecurityGroupRuleArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.SecurityGroupRule(param, b.build(), bo.build())

def serialConsoleAccess(param: String)(init: com.pulumi.aws.ec2.SerialConsoleAccessArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.SerialConsoleAccess =
	given b: com.pulumi.aws.ec2.SerialConsoleAccessArgs.Builder= com.pulumi.aws.ec2.SerialConsoleAccessArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.SerialConsoleAccess(param, b.build(), bo.build())

def snapshotCreateVolumePermission(param: String)(init: com.pulumi.aws.ec2.SnapshotCreateVolumePermissionArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.SnapshotCreateVolumePermission =
	given b: com.pulumi.aws.ec2.SnapshotCreateVolumePermissionArgs.Builder= com.pulumi.aws.ec2.SnapshotCreateVolumePermissionArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.SnapshotCreateVolumePermission(param, b.build(), bo.build())

def spotDatafeedSubscription(param: String)(init: com.pulumi.aws.ec2.SpotDatafeedSubscriptionArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.SpotDatafeedSubscription =
	given b: com.pulumi.aws.ec2.SpotDatafeedSubscriptionArgs.Builder= com.pulumi.aws.ec2.SpotDatafeedSubscriptionArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.SpotDatafeedSubscription(param, b.build(), bo.build())

def spotFleetRequest(param: String)(init: com.pulumi.aws.ec2.SpotFleetRequestArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.SpotFleetRequest =
	given b: com.pulumi.aws.ec2.SpotFleetRequestArgs.Builder= com.pulumi.aws.ec2.SpotFleetRequestArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.SpotFleetRequest(param, b.build(), bo.build())

def spotInstanceRequest(param: String)(init: com.pulumi.aws.ec2.SpotInstanceRequestArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.SpotInstanceRequest =
	given b: com.pulumi.aws.ec2.SpotInstanceRequestArgs.Builder= com.pulumi.aws.ec2.SpotInstanceRequestArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.SpotInstanceRequest(param, b.build(), bo.build())

def subnet(param: String)(init: com.pulumi.aws.ec2.SubnetArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.Subnet =
	given b: com.pulumi.aws.ec2.SubnetArgs.Builder= com.pulumi.aws.ec2.SubnetArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.Subnet(param, b.build(), bo.build())

def subnetCidrReservation(param: String)(init: com.pulumi.aws.ec2.SubnetCidrReservationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.SubnetCidrReservation =
	given b: com.pulumi.aws.ec2.SubnetCidrReservationArgs.Builder= com.pulumi.aws.ec2.SubnetCidrReservationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.SubnetCidrReservation(param, b.build(), bo.build())

def tag(param: String)(init: com.pulumi.aws.ec2.TagArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.Tag =
	given b: com.pulumi.aws.ec2.TagArgs.Builder= com.pulumi.aws.ec2.TagArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.Tag(param, b.build(), bo.build())

def trafficMirrorFilter(param: String)(init: com.pulumi.aws.ec2.TrafficMirrorFilterArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.TrafficMirrorFilter =
	given b: com.pulumi.aws.ec2.TrafficMirrorFilterArgs.Builder= com.pulumi.aws.ec2.TrafficMirrorFilterArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.TrafficMirrorFilter(param, b.build(), bo.build())

def trafficMirrorFilterRule(param: String)(init: com.pulumi.aws.ec2.TrafficMirrorFilterRuleArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.TrafficMirrorFilterRule =
	given b: com.pulumi.aws.ec2.TrafficMirrorFilterRuleArgs.Builder= com.pulumi.aws.ec2.TrafficMirrorFilterRuleArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.TrafficMirrorFilterRule(param, b.build(), bo.build())

def trafficMirrorSession(param: String)(init: com.pulumi.aws.ec2.TrafficMirrorSessionArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.TrafficMirrorSession =
	given b: com.pulumi.aws.ec2.TrafficMirrorSessionArgs.Builder= com.pulumi.aws.ec2.TrafficMirrorSessionArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.TrafficMirrorSession(param, b.build(), bo.build())

def trafficMirrorTarget(param: String)(init: com.pulumi.aws.ec2.TrafficMirrorTargetArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.TrafficMirrorTarget =
	given b: com.pulumi.aws.ec2.TrafficMirrorTargetArgs.Builder= com.pulumi.aws.ec2.TrafficMirrorTargetArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.TrafficMirrorTarget(param, b.build(), bo.build())

def transitGatewayPeeringAttachmentAccepter(param: String)(init: com.pulumi.aws.ec2.TransitGatewayPeeringAttachmentAccepterArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.TransitGatewayPeeringAttachmentAccepter =
	given b: com.pulumi.aws.ec2.TransitGatewayPeeringAttachmentAccepterArgs.Builder= com.pulumi.aws.ec2.TransitGatewayPeeringAttachmentAccepterArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.TransitGatewayPeeringAttachmentAccepter(param, b.build(), bo.build())

def volumeAttachment(param: String)(init: com.pulumi.aws.ec2.VolumeAttachmentArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VolumeAttachment =
	given b: com.pulumi.aws.ec2.VolumeAttachmentArgs.Builder= com.pulumi.aws.ec2.VolumeAttachmentArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VolumeAttachment(param, b.build(), bo.build())

def vpc(param: String)(init: com.pulumi.aws.ec2.VpcArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.Vpc =
	given b: com.pulumi.aws.ec2.VpcArgs.Builder= com.pulumi.aws.ec2.VpcArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.Vpc(param, b.build(), bo.build())

def vpcDhcpOptions(param: String)(init: com.pulumi.aws.ec2.VpcDhcpOptionsArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcDhcpOptions =
	given b: com.pulumi.aws.ec2.VpcDhcpOptionsArgs.Builder= com.pulumi.aws.ec2.VpcDhcpOptionsArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcDhcpOptions(param, b.build(), bo.build())

def vpcDhcpOptionsAssociation(param: String)(init: com.pulumi.aws.ec2.VpcDhcpOptionsAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcDhcpOptionsAssociation =
	given b: com.pulumi.aws.ec2.VpcDhcpOptionsAssociationArgs.Builder= com.pulumi.aws.ec2.VpcDhcpOptionsAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcDhcpOptionsAssociation(param, b.build(), bo.build())

def vpcEndpoint(param: String)(init: com.pulumi.aws.ec2.VpcEndpointArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcEndpoint =
	given b: com.pulumi.aws.ec2.VpcEndpointArgs.Builder= com.pulumi.aws.ec2.VpcEndpointArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcEndpoint(param, b.build(), bo.build())

def vpcEndpointConnectionAccepter(param: String)(init: com.pulumi.aws.ec2.VpcEndpointConnectionAccepterArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcEndpointConnectionAccepter =
	given b: com.pulumi.aws.ec2.VpcEndpointConnectionAccepterArgs.Builder= com.pulumi.aws.ec2.VpcEndpointConnectionAccepterArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcEndpointConnectionAccepter(param, b.build(), bo.build())

def vpcEndpointConnectionNotification(param: String)(init: com.pulumi.aws.ec2.VpcEndpointConnectionNotificationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcEndpointConnectionNotification =
	given b: com.pulumi.aws.ec2.VpcEndpointConnectionNotificationArgs.Builder= com.pulumi.aws.ec2.VpcEndpointConnectionNotificationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcEndpointConnectionNotification(param, b.build(), bo.build())

def vpcEndpointPolicy(param: String)(init: com.pulumi.aws.ec2.VpcEndpointPolicyArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcEndpointPolicy =
	given b: com.pulumi.aws.ec2.VpcEndpointPolicyArgs.Builder= com.pulumi.aws.ec2.VpcEndpointPolicyArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcEndpointPolicy(param, b.build(), bo.build())

def vpcEndpointRouteTableAssociation(param: String)(init: com.pulumi.aws.ec2.VpcEndpointRouteTableAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcEndpointRouteTableAssociation =
	given b: com.pulumi.aws.ec2.VpcEndpointRouteTableAssociationArgs.Builder= com.pulumi.aws.ec2.VpcEndpointRouteTableAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcEndpointRouteTableAssociation(param, b.build(), bo.build())

def vpcEndpointService(param: String)(init: com.pulumi.aws.ec2.VpcEndpointServiceArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcEndpointService =
	given b: com.pulumi.aws.ec2.VpcEndpointServiceArgs.Builder= com.pulumi.aws.ec2.VpcEndpointServiceArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcEndpointService(param, b.build(), bo.build())

def vpcEndpointServiceAllowedPrinciple(param: String)(init: com.pulumi.aws.ec2.VpcEndpointServiceAllowedPrincipleArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcEndpointServiceAllowedPrinciple =
	given b: com.pulumi.aws.ec2.VpcEndpointServiceAllowedPrincipleArgs.Builder= com.pulumi.aws.ec2.VpcEndpointServiceAllowedPrincipleArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcEndpointServiceAllowedPrinciple(param, b.build(), bo.build())

def vpcEndpointSubnetAssociation(param: String)(init: com.pulumi.aws.ec2.VpcEndpointSubnetAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcEndpointSubnetAssociation =
	given b: com.pulumi.aws.ec2.VpcEndpointSubnetAssociationArgs.Builder= com.pulumi.aws.ec2.VpcEndpointSubnetAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcEndpointSubnetAssociation(param, b.build(), bo.build())

def vpcIpam(param: String)(init: com.pulumi.aws.ec2.VpcIpamArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcIpam =
	given b: com.pulumi.aws.ec2.VpcIpamArgs.Builder= com.pulumi.aws.ec2.VpcIpamArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcIpam(param, b.build(), bo.build())

def vpcIpamOrganizationAdminAccount(param: String)(init: com.pulumi.aws.ec2.VpcIpamOrganizationAdminAccountArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcIpamOrganizationAdminAccount =
	given b: com.pulumi.aws.ec2.VpcIpamOrganizationAdminAccountArgs.Builder= com.pulumi.aws.ec2.VpcIpamOrganizationAdminAccountArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcIpamOrganizationAdminAccount(param, b.build(), bo.build())

def vpcIpamPool(param: String)(init: com.pulumi.aws.ec2.VpcIpamPoolArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcIpamPool =
	given b: com.pulumi.aws.ec2.VpcIpamPoolArgs.Builder= com.pulumi.aws.ec2.VpcIpamPoolArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcIpamPool(param, b.build(), bo.build())

def vpcIpamPoolCidr(param: String)(init: com.pulumi.aws.ec2.VpcIpamPoolCidrArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcIpamPoolCidr =
	given b: com.pulumi.aws.ec2.VpcIpamPoolCidrArgs.Builder= com.pulumi.aws.ec2.VpcIpamPoolCidrArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcIpamPoolCidr(param, b.build(), bo.build())

def vpcIpamPoolCidrAllocation(param: String)(init: com.pulumi.aws.ec2.VpcIpamPoolCidrAllocationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcIpamPoolCidrAllocation =
	given b: com.pulumi.aws.ec2.VpcIpamPoolCidrAllocationArgs.Builder= com.pulumi.aws.ec2.VpcIpamPoolCidrAllocationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcIpamPoolCidrAllocation(param, b.build(), bo.build())

def vpcIpamPreviewNextCidr(param: String)(init: com.pulumi.aws.ec2.VpcIpamPreviewNextCidrArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcIpamPreviewNextCidr =
	given b: com.pulumi.aws.ec2.VpcIpamPreviewNextCidrArgs.Builder= com.pulumi.aws.ec2.VpcIpamPreviewNextCidrArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcIpamPreviewNextCidr(param, b.build(), bo.build())

def vpcIpamResourceDiscovery(param: String)(init: com.pulumi.aws.ec2.VpcIpamResourceDiscoveryArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcIpamResourceDiscovery =
	given b: com.pulumi.aws.ec2.VpcIpamResourceDiscoveryArgs.Builder= com.pulumi.aws.ec2.VpcIpamResourceDiscoveryArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcIpamResourceDiscovery(param, b.build(), bo.build())

def vpcIpamResourceDiscoveryAssociation(param: String)(init: com.pulumi.aws.ec2.VpcIpamResourceDiscoveryAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcIpamResourceDiscoveryAssociation =
	given b: com.pulumi.aws.ec2.VpcIpamResourceDiscoveryAssociationArgs.Builder= com.pulumi.aws.ec2.VpcIpamResourceDiscoveryAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcIpamResourceDiscoveryAssociation(param, b.build(), bo.build())

def vpcIpamScope(param: String)(init: com.pulumi.aws.ec2.VpcIpamScopeArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcIpamScope =
	given b: com.pulumi.aws.ec2.VpcIpamScopeArgs.Builder= com.pulumi.aws.ec2.VpcIpamScopeArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcIpamScope(param, b.build(), bo.build())

def vpcIpv4CidrBlockAssociation(param: String)(init: com.pulumi.aws.ec2.VpcIpv4CidrBlockAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcIpv4CidrBlockAssociation =
	given b: com.pulumi.aws.ec2.VpcIpv4CidrBlockAssociationArgs.Builder= com.pulumi.aws.ec2.VpcIpv4CidrBlockAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcIpv4CidrBlockAssociation(param, b.build(), bo.build())

def vpcIpv6CidrBlockAssociation(param: String)(init: com.pulumi.aws.ec2.VpcIpv6CidrBlockAssociationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcIpv6CidrBlockAssociation =
	given b: com.pulumi.aws.ec2.VpcIpv6CidrBlockAssociationArgs.Builder= com.pulumi.aws.ec2.VpcIpv6CidrBlockAssociationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcIpv6CidrBlockAssociation(param, b.build(), bo.build())

def vpcNetworkPerformanceMetricSubscription(param: String)(init: com.pulumi.aws.ec2.VpcNetworkPerformanceMetricSubscriptionArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcNetworkPerformanceMetricSubscription =
	given b: com.pulumi.aws.ec2.VpcNetworkPerformanceMetricSubscriptionArgs.Builder= com.pulumi.aws.ec2.VpcNetworkPerformanceMetricSubscriptionArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcNetworkPerformanceMetricSubscription(param, b.build(), bo.build())

def vpcPeeringConnection(param: String)(init: com.pulumi.aws.ec2.VpcPeeringConnectionArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcPeeringConnection =
	given b: com.pulumi.aws.ec2.VpcPeeringConnectionArgs.Builder= com.pulumi.aws.ec2.VpcPeeringConnectionArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcPeeringConnection(param, b.build(), bo.build())

def vpcPeeringConnectionAccepter(param: String)(init: com.pulumi.aws.ec2.VpcPeeringConnectionAccepterArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpcPeeringConnectionAccepter =
	given b: com.pulumi.aws.ec2.VpcPeeringConnectionAccepterArgs.Builder= com.pulumi.aws.ec2.VpcPeeringConnectionAccepterArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpcPeeringConnectionAccepter(param, b.build(), bo.build())

def vpnConnection(param: String)(init: com.pulumi.aws.ec2.VpnConnectionArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpnConnection =
	given b: com.pulumi.aws.ec2.VpnConnectionArgs.Builder= com.pulumi.aws.ec2.VpnConnectionArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpnConnection(param, b.build(), bo.build())

def vpnConnectionRoute(param: String)(init: com.pulumi.aws.ec2.VpnConnectionRouteArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpnConnectionRoute =
	given b: com.pulumi.aws.ec2.VpnConnectionRouteArgs.Builder= com.pulumi.aws.ec2.VpnConnectionRouteArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpnConnectionRoute(param, b.build(), bo.build())

def vpnGateway(param: String)(init: com.pulumi.aws.ec2.VpnGatewayArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpnGateway =
	given b: com.pulumi.aws.ec2.VpnGatewayArgs.Builder= com.pulumi.aws.ec2.VpnGatewayArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpnGateway(param, b.build(), bo.build())

def vpnGatewayAttachment(param: String)(init: com.pulumi.aws.ec2.VpnGatewayAttachmentArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpnGatewayAttachment =
	given b: com.pulumi.aws.ec2.VpnGatewayAttachmentArgs.Builder= com.pulumi.aws.ec2.VpnGatewayAttachmentArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpnGatewayAttachment(param, b.build(), bo.build())

def vpnGatewayRoutePropagation(param: String)(init: com.pulumi.aws.ec2.VpnGatewayRoutePropagationArgs.Builder ?=> Unit, initOpt: (CustomResourceOptions.Builder ?=> Unit) = baseOpts): com.pulumi.aws.ec2.VpnGatewayRoutePropagation =
	given b: com.pulumi.aws.ec2.VpnGatewayRoutePropagationArgs.Builder= com.pulumi.aws.ec2.VpnGatewayRoutePropagationArgs.builder()
	init
	given bo: CustomResourceOptions.Builder = CustomResourceOptions.builder()
	initOpt
	new com.pulumi.aws.ec2.VpnGatewayRoutePropagation(param, b.build(), bo.build())

def availabilityZonesNames(): Output[com.pulumi.aws.outputs.GetAvailabilityZonesResult] = com.pulumi.aws.AwsFunctions.getAvailabilityZones(com.pulumi.aws.inputs.GetAvailabilityZonesArgs.builder()
  .state("available")
  .build())

def routeTableRouteArgs()(init: com.pulumi.aws.ec2.inputs.RouteTableRouteArgs.Builder ?=> Unit): com.pulumi.aws.ec2.inputs.RouteTableRouteArgs =
  given b: com.pulumi.aws.ec2.inputs.RouteTableRouteArgs.Builder = com.pulumi.aws.ec2.inputs.RouteTableRouteArgs.builder()
  init
  b.build()
