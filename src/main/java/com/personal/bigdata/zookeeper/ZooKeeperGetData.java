package com.personal.bigdata.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZooKeeperGetData {

	public static void nodeExists(ZooKeeper zooKeeper, String path) {

		try {
			long start = System.currentTimeMillis();
			
			Stat stat = zooKeeper.exists(path, true);

			if (stat != null) {
				byte[] b = zooKeeper.getData(path, new DefaultWatcher(zooKeeper), null);

				String data = new String(b, "UTF-8");
				System.out.println(data);

			} else {
				System.out.println("Node does not exists");
			}
			System.out.println("Fetch data from path " + path + " in " + (System.currentTimeMillis() - start) + "ms");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
