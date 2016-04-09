/**
 * 
 */
package com.personal.caching;

import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

/**
 * @author piyush
 *
 */
public class MemcacheMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// Connecting to Memcached server on localhost
			InetSocketAddress inetSocketAddress = new InetSocketAddress(
					"127.0.0.1", 11211);

			String key = "name";
			MemcachedClient mcc = new MemcachedClient(inetSocketAddress);

			mcc.set(key, 1, inetSocketAddress);

			Object object = null;
			while ((object = mcc.get(key)) != null) {
				System.out.println("Object still alive. " + object.toString().substring(1));
			}
			// Shutdowns the memcached client
			mcc.shutdown();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
