package com.personal.bigdata.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.KeeperException;

public class ZooKeeperDelete {

	public static void delete(ZooKeeper zooKeeper, String path) {
		try {
			long start = System.currentTimeMillis();
			zooKeeper.delete(path, zooKeeper.exists(path, true).getVersion());
			System.out.println("Delete path " + path + " in " + (System.currentTimeMillis() - start) + "ms");
		} catch (InterruptedException | KeeperException e) {
			e.printStackTrace();
		}
	}
}