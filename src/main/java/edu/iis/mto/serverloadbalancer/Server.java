package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAXCAPACITY = 100.0d;
	public double currentLoad;
	public int capacity;

	public boolean contains(Vm theVm) {
		
		return vmList.contains(theVm);
	}
	
	List<Vm> vmList = new ArrayList<Vm>();
	
	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}

	public void addVm(Vm vm) {
		currentLoad = vm.size / capacity * MAXCAPACITY;
		vmList.add(vm);
	}

	public int vmsCount() {
		// TODO Auto-generated method stub
		return vmList.size();
	}
	
	

}
