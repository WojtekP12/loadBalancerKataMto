package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;
import static org.junit.Assert.assertThat;
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
