package com.personal.bigdata.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperCreate {

	public static void create(ZooKeeper zooKeeper, String path) {
		
		byte[] data = "My first zookeeper app".getBytes();
		
		try {
			long start = System.currentTimeMillis();
			String actualPath = zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
			System.out.println("Created path " + actualPath + " in " + (System.currentTimeMillis() - start) + "ms");
			
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}