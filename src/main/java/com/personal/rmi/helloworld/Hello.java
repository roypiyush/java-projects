package com.personal.rmi.helloworld;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * A remote object is an instance of a class that implements a remote interface.
 * A remote interface extends the interface java.rmi.Remote and declares a set
 * of remote methods. Each remote method must declare java.rmi.RemoteException
 * (or a superclass of RemoteException) in its throws clause, in addition to any
 * application-specific exceptions.
 * 
 * @throws java.rmi.RemoteException
 *             Remote method invocations can fail in many additional ways
 *             compared to local method invocations (such as network-related
 *             communication problems and server problems), and remote methods
 *             will report such failures by throwing a java.rmi.RemoteException.
 * 
 * @author piyush
 * 
 */
public interface Hello extends Remote {
	String sayHello() throws RemoteException;
}
