package com.personal.bigdata.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.KeeperException;

public class ZooKeeperDelete {

	public static void delete(ZooKeeper zooKeeper, String path) {
		try {
			
			zooKeeper.delete(path, zooKeeper.exists(path, true).getVersion());
			
		} catch (InterruptedException | KeeperException e) {
			e.printStackTrace();
		}
	}
}