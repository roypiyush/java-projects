package com.personal.old.rmi.helloworld;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * A "server" class, in this context, is the class which has a main method that
 * creates an instance of the remote object implementation, exports the remote
 * object, and then binds that instance to a name in a Java RMI registry. The
 * class that contains this main method could be the implementation class
 * itself, or another class entirely.
 * 
 * You are required to start rmid and then rmiregistry before running this server
 * 
 * @author piyush
 * 
 */
public class Server implements Hello {

	public Server() {
	}

	public String sayHello() {
		return "Hello, world!";
	}

	public static void main(String args[]) {

		try {
			Server obj = new Server();
			Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Hello", stub);

			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}