package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server>{

	private int capacity;

	public ServerBuilder witchCapacity(int capacity) {
		this.capacity = capacity;
		// TODO Auto-generated method stub
		return this;
	}

	public Server build() {
		// TODO Auto-generated method stub
		return new Server();
	}
	
	public static ServerBuilder server() {
		// TODO Auto-generated method stub
		return new ServerBuilder();
	}

}
