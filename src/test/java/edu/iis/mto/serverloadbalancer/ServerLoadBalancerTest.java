package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ServerLoadBalancerTest 
{
	@Test
	public void balancingServer_noVm_ServerStaysEmpty()
	{
		Server theServer = a(server().witchCapacity(1));
		balancing(aServerListWith(theServer), anEmptyListOfVms());
		
		assertThat(theServer, hasCurrentLoadOf(0.0d));
	}

	private Matcher<? super Server> hasCurrentLoadOf(double expectedLoad) {
		// TODO Auto-generated method stub
		return new ServerLoadBalancerMatcher(expectedLoad);
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

	private ServerBuilder server() {
		// TODO Auto-generated method stub
		return new ServerBuilder();
	}
}
