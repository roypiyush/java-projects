package com.personal.rmi.client;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.personal.rmi.server.interfaces.Compute;

public class ComputePi {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "Compute";
			Registry registry = LocateRegistry.getRegistry(args[0]);
			Compute comp = (Compute) registry.lookup(name);
			Pi task = new Pi(Integer.parseInt(args[1]));
			BigDecimal pi = (BigDecimal) comp.executeTask(task);
			System.out.println(pi);
		} catch (Exception e) {
			System.err.println("ComputePi exception:");
			e.printStackTrace();
		}
	}
}