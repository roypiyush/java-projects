package com.personal.old.rmi.server.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute<T> extends Remote {
	T executeTask(Task<T> t) throws RemoteException;
}
