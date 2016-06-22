package edu.iis.mto.serverloadbalancer;

public class ServeLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		
		for (Vm vm : vms) {
			addVmToServer(servers, vm);
		}
	
	}

	private void addVmToServer(Server[] servers, Vm vm) {
		Server lessLoadedServer = findLessLoadedServer(servers);
		lessLoadedServer.addVm(vm);
	}

	private Server findLessLoadedServer(Server[] servers) {
		Server lessLoadedServer = null;
		for (Server server : servers) {
			if(lessLoadedServer == null || server.currentLoad>lessLoadedServer.currentLoad)
			{
				lessLoadedServer = server;
			}
		}
		return lessLoadedServer;
	}

}
