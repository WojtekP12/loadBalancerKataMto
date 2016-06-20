package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerLoadBalancerMatcher extends TypeSafeMatcher<Server> {

	private double expectedLoad;

	public ServerLoadBalancerMatcher(double expectedLoad) {
		this.expectedLoad = expectedLoad;
		// TODO Auto-generated constructor stub
	}

	public void describeTo(Description description) {
		description.appendText("server has current load of ").appendValue(expectedLoad);
		
	}
	
	@Override
	protected void describeMismatchSafely(Server item, Description description)
	{
		description.appendText("server has current load of ").appendValue(item.currentLoad);
	}

	@Override
	protected boolean matchesSafely(Server server) {
		return doublesAreEqual(expectedLoad, server.currentLoad);
	}

	private boolean doublesAreEqual(double d1, double d2) {
		return d1 == d2 || Math.abs(d1 - d2)<0.01d;
	}
	
	public static ServerLoadBalancerMatcher hasCurrentLoadOf(double expectedLoad) {
		// TODO Auto-generated method stub
		return new ServerLoadBalancerMatcher(expectedLoad);
	}
	
	

}
