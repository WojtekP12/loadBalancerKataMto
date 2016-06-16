package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ServerLoadBalancerParametrizedTest extends ServerLoadBalancerBaseTest{
	
	private double capacity;
	private double size;
	private double load;
	
	
	
	
	public ServerLoadBalancerParametrizedTest(double capacity, double size, double load) 
	{
		this.capacity = capacity;
		this.size = size;
		this.load = load;
	}


	//capacity,size,load
	@Parameters
	public static Collection<Double[]> balancingParams() 
	{
		return Arrays.asList(new Double[][] { { 1.0d, 1.0d, 100.0d }, { 2.0d, 1.0d, 50.0d },
			{ 4.0d, 1.0d, 25.0d }, });
	}
	
	
	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm() 
	{
		Server theServer = a(server().withCapacity((int)capacity));
		Vm theVm = a(vm().ofSize((int)size));
		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat(theServer, hasLoadPercentageOf(load));
		assertThat("the server should contain vm", theServer.contains(theVm));
	}
	
	
}
