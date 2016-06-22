package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class VmCountPercentageMatcher extends TypeSafeMatcher<Server> {

	private int count;

	public VmCountPercentageMatcher(int count) {
		this.count = count;
		// TODO Auto-generated constructor stub
	}

	public void describeTo(Description description) {
		description.appendText("a server has vm count of ").appendValue(count);
	}
	
	@Override
	protected void describeMismatchSafely(Server item, Description description)
	{
		description.appendText("a server has vm count of ").appendValue(item.vmsCount());
	};

	@Override
	protected boolean matchesSafely(Server item) {
		
		return count == item.vmsCount();
	}
	
	public static VmCountPercentageMatcher hasVmCountOf(int count) {
		return new VmCountPercentageMatcher(count);
	}

}
