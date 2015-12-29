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
		
		try{
	         // Connecting to Memcached server on localhost
	         MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
	         System.out.println("Connection to server sucessful.");
	         mcc.set("name", 1, "Piyush");
	         
	         Object object = null;
	         while((object = mcc.get("name")) != null) {
	        	 System.out.println("Object still alive." + object);
	         }
	         // Shutdowns the memcached client
	         mcc.shutdown();
	         
	      }catch(Exception ex){
	         System.out.println( ex.getMessage() );
	      }

	}

}
