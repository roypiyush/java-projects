package com.personal.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.personal.rmi.server.interfaces.Compute;
import com.personal.rmi.server.interfaces.Task;


/* 
 * On same shell window run following commands
 * 
 * Start rmiregistry first 
 * $ rmiregistry
 *  
 *  Along with above you should also run rmid (rmi daemon)
 *  
 *  Your working directory will be target/classes
 *  
 * $ java -Djava.rmi.server.hostname=127.0.0.1 -Djava.security.policy=server.policy com.personal.rmi.server.ComputeEngine
 * 
 */


@SuppressWarnings("rawtypes")
public class ComputeEngine implements Compute {

	public ComputeEngine() {
		super();
	}

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "Compute";
			Compute engine = new ComputeEngine();
			Compute stub = (Compute) UnicastRemoteObject
					.exportObject(engine, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(name, stub);
			System.out.println("ComputeEngine bound");
		} catch (Exception e) {
			System.err.println("ComputeEngine exception:");
			e.printStackTrace();
		}
	}

	public Object executeTask(Task t) throws RemoteException {
		return t.execute();
	}

}
