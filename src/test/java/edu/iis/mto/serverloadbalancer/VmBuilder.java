package edu.iis.mto.serverloadbalancer;

public class VmBuilder implements Builder<Vm>{

	private int size;

	public VmBuilder ofSize(int size) {
		this.size = size;
		// TODO Auto-generated method stub
		return this;
	}

	public Vm build() {
		// TODO Auto-generated method stub
		return new Vm();
	}
	
	public static VmBuilder vm() {
		// TODO Auto-generated method stub
		return new VmBuilder();
	}

}
