package com.personal.bigdata.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperConnection {

	private ZooKeeper zooKeeper;
	final CountDownLatch connectedSignal = new CountDownLatch(1);

	public ZooKeeper connect(String host) {

		try {
			zooKeeper = new ZooKeeper(host, 5000, new Watcher() {

				public void process(WatchedEvent event) {

					if (event.getState() == KeeperState.SyncConnected) {
						connectedSignal.countDown();
					}
				}
			});

			connectedSignal.await();
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return zooKeeper;
	}

	public void close() {
		try {
			zooKeeper.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}