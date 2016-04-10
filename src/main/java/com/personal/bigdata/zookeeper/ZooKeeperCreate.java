package com.personal.bigdata.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperCreate {

	public static void create(ZooKeeper zooKeeper, String path) {
		
		byte[] data = "My first zookeeper app".getBytes();
		
		try {
			String actualPath = zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
			System.out.println("Created at actual path : " + actualPath);
			
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}