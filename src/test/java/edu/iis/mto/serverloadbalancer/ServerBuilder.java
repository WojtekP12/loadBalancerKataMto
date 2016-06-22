package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server>{

	private int capacity;
	private double initialLoad;

	public ServerBuilder witchCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() 
	{
		Server server = new Server(capacity);
		addInitialVm(server);
		return server;
	}

	private void addInitialVm(Server server) {
		if(initialLoad > 0)
		{
			int initialVmSize = (int) (initialLoad / (double)server.capacity * Server.MAXCAPACITY);
			Vm initialVm = VmBuilder.vm().ofSize(initialVmSize).build();
			server.addVm(initialVm);
		}
	}
	
	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoadOf(double initialLoad) {
		this.initialLoad = initialLoad;
		return this;
	}

}
