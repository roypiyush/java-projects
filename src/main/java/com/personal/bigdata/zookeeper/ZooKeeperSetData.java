package com.personal.bigdata.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperSetData {

	public static void update(ZooKeeper zooKeeper, String path, String newData) {
		try {
			long start = System.currentTimeMillis();
			zooKeeper.setData(path, newData.getBytes(), zooKeeper.exists(path, true).getVersion());
			System.out.println("Set data at path " + path + " in " + (System.currentTimeMillis() - start) + "ms");
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
