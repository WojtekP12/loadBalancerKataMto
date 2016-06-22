package edu.iis.mto.serverloadbalancer;

public class ServeLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		if(vms.length>0)
		{
			servers[0].currentLoad = vms[0].size / servers[0].capacity * 100.0d;
		}
	
		
	}

}
