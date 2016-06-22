package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

public class Server {

	public static final double MAXCAPACITY = 100.0d;
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
		currentLoad = loadOfVm(vm);
		vmList.add(vm);
	}

	private double loadOfVm(Vm vm) {
		return vm.size / capacity * MAXCAPACITY;
	}

	public int vmsCount() {
		return vmList.size();
	}

	public boolean canFit(Vm vm) {
		return currentLoad + loadOfVm(vm) <= MAXCAPACITY;
	}
	
	

}
