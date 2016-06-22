package edu.iis.mto.serverloadbalancer;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest 
{
	@Test
	public void balancingServer_noVm_ServerStaysEmpty()
	{
		Server theServer = a(ServerBuilder.server().witchCapacity(1));
		balancing(aServerListWith(theServer), anEmptyListOfVms());
		
		assertThat(theServer, ServerLoadBalancerMatcher.hasCurrentLoadOf(0.0d));
	}

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm()
	{
		Server theServer = a(ServerBuilder.server().witchCapacity(1));
		Vm theVm = a(VmBuilder.vm().ofSize(1));
		balancing(aServerListWith(theServer), aVmListWith(theVm));
		
		assertThat(theServer, ServerLoadBalancerMatcher.hasCurrentLoadOf(100.0d));
		assertThat("a server has the vm ", theServer.contains(theVm));
	}
	
	@Test
	public void balancingOneServerWithTenSlotCapacity_AndOneSlotVm_fillTheServerWithTenPercent()
	{
		Server theServer = a(ServerBuilder.server().witchCapacity(10));
		Vm theVm = a(VmBuilder.vm().ofSize(1));
		balancing(aServerListWith(theServer), aVmListWith(theVm));
		
		assertThat(theServer, ServerLoadBalancerMatcher.hasCurrentLoadOf(10.0d));
		assertThat("a server has the vm ", theServer.contains(theVm));
	}
	
	@Test
	public void balancingAServerWithEnoughRoom_getsFilledWithAllVms()
	{
		Server theServer = a(ServerBuilder.server().witchCapacity(100));
		Vm theFirstVm = a(VmBuilder.vm().ofSize(1));
		Vm theSecondVm = a(VmBuilder.vm().ofSize(1));
		
		balancing(aServerListWith(theServer), aVmListWith(theFirstVm,theSecondVm));
		
		assertThat(theServer, VmCountPercentageMatcher.hasVmCountOf(2));
		assertThat("a server has the first vm ", theServer.contains(theFirstVm));
		assertThat("a server has the second vm ", theServer.contains(theSecondVm));
	}
	
	@Test
	public void aVm_ShouldBeBalanced_OnLessLoadedServerFirst()
	{
		Server lessLoadedServer = a(ServerBuilder.server().witchCapacity(100).withCurrentLoadOf(45.0d));
		Server moreLoadedServer = a(ServerBuilder.server().witchCapacity(100).withCurrentLoadOf(45.0d));
		Vm theVm = a(VmBuilder.vm().ofSize(1));
		
		balancing(aServerListWith(lessLoadedServer,moreLoadedServer), aVmListWith(theVm));
		assertThat("less loaded server should contain the vm ", lessLoadedServer.contains(theVm));
		assertThat("more loaded server should not contain the vm ", !moreLoadedServer.contains(theVm));
	}
	
	@Test
	public void balance_serversANdVms()
	{
		Server theServer = a(ServerBuilder.server().witchCapacity(10).withCurrentLoadOf(90.0d));
		Vm theVm = a(VmBuilder.vm().ofSize(2));
		balancing(aServerListWith(theServer), aVmListWith(theVm));
		
		assertThat("the server should not contain the vm ", !theServer.contains(theVm));
		
	}
	
	private void balancing(Server[] servers, Vm[] vms) {
		new ServeLoadBalancer().balance(servers,vms);
		
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}
	
	private Vm[] aVmListWith(Vm... vms) {
		return vms;
	}
	
	private Server[] aServerListWith(Server... servers) {
		return servers;
	}
	
	private <T> T a(Builder<T> builder)
	{
		return builder.build();
	}
	
}
