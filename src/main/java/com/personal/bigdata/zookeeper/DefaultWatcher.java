package com.personal.bigdata.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class DefaultWatcher implements Watcher {
	
	private ZooKeeper zooKeeper;
	public DefaultWatcher(ZooKeeper zooKeeper) {
		this.zooKeeper = zooKeeper;
	}
	
	public void process(WatchedEvent event) {
		
		String path = "/MyFirstZnode";
		
		try {
			byte[] bn = zooKeeper.getData(path, false, null);
			String data = new String(bn, "UTF-8");
			System.out.println(data);
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
