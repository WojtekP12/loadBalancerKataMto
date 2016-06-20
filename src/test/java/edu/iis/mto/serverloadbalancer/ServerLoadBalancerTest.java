package edu.iis.mto.serverloadbalancer;

import static org.hamcrest.MatcherAssert.assertThat;
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
		Vm theVm = a(vm().ofSize(1));
		balancing(aServerListWith(theServer), aVmListWith(theVm));
		
		assertThat(theServer, ServerLoadBalancerMatcher.hasCurrentLoadOf(100.0d));
		assertThat("a server has the vm ", theServer.contains(theVm));
	}
	
	private Vm[] aVmListWith(Vm... vms) {
		// TODO Auto-generated method stub
		return vms;
	}

	private Vm a(VmBuilder builder) {
		// TODO Auto-generated method stub
		return builder.build();
	}

	private VmBuilder vm() {
		// TODO Auto-generated method stub
		return new VmBuilder();
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServeLoadBalancer().balance(servers,vms);
		
	}

	private Vm[] anEmptyListOfVms() {
		// TODO Auto-generated method stub
		return new Vm[0];
	}

	private Server[] aServerListWith(Server... servers) {
		// TODO Auto-generated method stub
		return servers;
	}

	private Server a(ServerBuilder builder) {
		// TODO Auto-generated method stub
		return builder.build();
	}

	
}
