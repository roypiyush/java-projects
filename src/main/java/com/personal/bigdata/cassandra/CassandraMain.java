/**
 * 
 */
package com.personal.bigdata.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * @author piyush
 *
 */
public class CassandraMain {

	private Cluster cluster;
	
	
	public void connect(String node) {
		cluster = Cluster.builder().addContactPoint(node).build();

		Metadata metadata = cluster.getMetadata();

		System.out.printf("Connected to cluster: %s\n",
				metadata.getClusterName());

		for (Host host : metadata.getAllHosts()) {
			System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
					host.getDatacenter(), host.getAddress(), host.getRack());
		}
	}

	public void close() {
		cluster.close();
	}
	
	public void execute(String query) {
		Session session = cluster.connect();
		ResultSet results = session.execute(query);
		System.out
				.println(String
						.format("%-30s\t%-20s\t%-20s\n%s", "user_id", "fname",
								"lname",
								"-------------------------------+-----------------------+--------------------"));
		for (Row row : results) {
			System.out.println(String.format("%-30s\t%-20s\t%-20s",
					row.getInt("user_id"), row.getString("fname"),
					row.getString("lname")));
		}
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CassandraMain client = new CassandraMain();
		client.connect("127.0.0.1");
		
		long start = 0;
		
		for (int i = 0; i < 5; i++) {
			start = System.currentTimeMillis();
			System.out.println("\n\nOn " + i + "run");
			client.execute("SELECT * FROM mykeyspace.users");
			System.out.println("Execution time " + (System.currentTimeMillis() - start));
			System.out.println("##################################\n\n");
		}
		
		
	    client.close();
		
	}
	
	

}
