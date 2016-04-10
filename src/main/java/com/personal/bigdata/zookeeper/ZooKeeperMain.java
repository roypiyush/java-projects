/**
 * 
 */
package com.personal.bigdata.zookeeper;

import org.apache.zookeeper.ZooKeeper;

/**
 * @author piyush
 *
 */
public class ZooKeeperMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String path = "/MyFirstZnode";
		ZooKeeperConnection conn = new ZooKeeperConnection();
		ZooKeeper zooKeeper = conn.connect("localhost");
		ZooKeeperCreate.create(zooKeeper, path);
		ZooKeeperGetData.nodeExists(zooKeeper, path);
		ZooKeeperSetData.update(zooKeeper, path, "Success");
		ZooKeeperDelete.delete(zooKeeper, path);
		conn.close();

	}

}
