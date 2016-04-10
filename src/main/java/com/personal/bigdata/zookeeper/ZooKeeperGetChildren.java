package com.personal.bigdata.zookeeper;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZooKeeperGetChildren {

	public static void getChildren(ZooKeeper zooKeeper, String path) {
		
		try {
			System.out.println("Fetching children");
			
			long start = System.currentTimeMillis();
			
			Stat stat = zooKeeper.exists(path, true);
			if (stat != null) {

				List<String> children = zooKeeper.getChildren(path, false);
				for (int i = 0; i < children.size(); i++)
					System.out.println(children.get(i));
				
			} else {
				System.out.println("Node does not exists");
			}
			System.out.println("Fetch children from path " + path + " in " + (System.currentTimeMillis() - start) + "ms");
			
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException,
			KeeperException {
		
		String path = "/MyFirstZnode";
		ZooKeeperConnection conn = new ZooKeeperConnection();
		ZooKeeper zk = conn.connect("localhost");
		getChildren(zk, path);
		conn.close();
	}

}
