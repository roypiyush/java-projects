package com.personal.jndildap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;

public class Lookup {

	public static void main(String[] args) {

		// Set up the environment for creating the initial context
		Hashtable<String, Object> env = new Hashtable<String, Object>(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:389/dc=nodomain");

		Context ctx = null;
		try {
			// Create the initial context
			 ctx = new InitialContext(env);

			// Perform lookup and cast to target type
			LdapContext b = (LdapContext) ctx
					.lookup("ou=People");

			System.out.println(b.lookup("uid=john"));

		} catch (NamingException e) {
			System.out.println("Lookup failed: " + e);
		} finally {
			if(ctx != null)
				try { ctx.close(); } catch (NamingException e) { e.printStackTrace(); }
		}

	}

}