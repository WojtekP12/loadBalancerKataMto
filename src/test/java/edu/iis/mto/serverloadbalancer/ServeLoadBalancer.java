package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServeLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		
		for (Vm vm : vms) {
			addVmToServer(servers, vm);
		}
	
	}

	private void addVmToServer(Server[] servers, Vm vm) {
		List<Server> capableServers = findServerWithEnoughCapacity(servers, vm);
		Server lessLoadedServer = findLessLoadedServer(capableServers);
		addToCapableServer(vm, lessLoadedServer);
		
	}

	private void addToCapableServer(Vm vm, Server lessLoadedServer) {
		if(lessLoadedServer != null)
		{
			lessLoadedServer.addVm(vm);
		}
	}

	private List<Server> findServerWithEnoughCapacity(Server[] servers, Vm vm) {
		List<Server> capableServers = new ArrayList<Server>();
		for(Server server : servers)
		{
			if(server.canFit(vm))
			{
				capableServers.add(server);
			}
		}
		return capableServers;
	}

	private Server findLessLoadedServer(List<Server> capableServers) {
		Server lessLoadedServer = null;
		for (Server server : capableServers) {
			if(lessLoadedServer == null || server.currentLoad>lessLoadedServer.currentLoad)
			{
				lessLoadedServer = server;
			}
		}
		return lessLoadedServer;
	}

}
