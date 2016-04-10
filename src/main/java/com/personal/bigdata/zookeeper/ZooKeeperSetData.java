package com.personal.bigdata.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperSetData {

	public static void update(ZooKeeper zooKeeper, String path, String newData) {
		try {
			
			zooKeeper.setData(path, newData.getBytes(), zooKeeper.exists(path, true).getVersion());
			
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
