package com.personal.old.rmi.helloworld;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * The client program obtains a stub for the registry on the server's host,
 * looks up the remote object's stub by name in the registry, and then invokes
 * the sayHello method on the remote object using the stub.
 * 
 * 
 * This client first obtains the stub for the registry by invoking the static
 * LocateRegistry.getRegistry method with the hostname specified on the command
 * line. If no hostname is specified, then null is used as the hostname
 * indicating that the local host address should be used.
 * 
 * Next, the client invokes the remote method lookup on the registry stub to
 * obtain the stub for the remote object from the server's registry.
 * 
 * Finally, the client invokes the sayHello method on the remote object's stub,
 * which causes the following actions to happen:
 * 
 * <li>The client-side runtime opens a connection to the server using the host
 * and port information in the remote object's stub and then serializes the call
 * data.</li> <li>The server-side runtime accepts the incoming call, dispatches
 * the call to the remote object, and serializes the result (the reply string
 * "Hello, world!") to the client.</li> <li>The client-side runtime receives,
 * deserializes, and returns the result to the caller.</li>
 */
public class Client {
	private Client() {
	}

	public static void main(String[] args) {

		String host = (args.length < 1) ? null : args[0];
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			Hello stub = (Hello) registry.lookup("Hello");
			String response = stub.sayHello();
			System.out.println("response: " + response);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
