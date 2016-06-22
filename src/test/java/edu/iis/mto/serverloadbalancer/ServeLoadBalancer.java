package edu.iis.mto.serverloadbalancer;

public class ServeLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		
		for (Vm vm : vms) {
			Server lessLoadedServer = null;
			for (Server server : servers) {
				if(lessLoadedServer == null || server.currentLoad>lessLoadedServer.currentLoad)
				{
					lessLoadedServer = server;
				}
			}
			lessLoadedServer.addVm(vm);
		}
	
	}

}
