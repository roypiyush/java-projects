package com.personal.jndildap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;

public class Lookup {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		// Set up the environment for creating the initial context
		Hashtable<String, Object> env = new Hashtable<String, Object>(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:389/dc=example,dc=com");

		Context ctx = null;
		try {
			// Create the initial context
			ctx = new InitialContext(env);

			NamingEnumeration<NameClassPair> list = ctx.list("ou=People");
			while (list.hasMoreElements()) {
				NameClassPair nameClassPair = list.next();
				System.out.println(String.format(
						"Name: %s, ClassName: %s, NameInNamespace:%s",
						nameClassPair.getName(), nameClassPair.getClassName(),
						nameClassPair.getNameInNamespace()));
			}

			DirContext dirContext = (DirContext) ctx.lookup("ou=People");
			Attributes attributes = dirContext.getAttributes("uid=john");

			NamingEnumeration<String> enumerations = attributes.getIDs();
			while (enumerations.hasMoreElements()) {
				String id = enumerations.next();
				System.out.println(attributes.get(id));
			}

		} catch (NamingException e) {
			System.out.println("Lookup failed: " + e.getCause().getMessage());
		} finally {
			if (ctx != null)
				try {
					ctx.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}
		}
		
		System.out.println(String.format("Completed in %sms", System.currentTimeMillis() - start));

	}

}
