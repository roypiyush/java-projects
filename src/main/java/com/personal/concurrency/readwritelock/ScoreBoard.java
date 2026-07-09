package com.personal.concurrency.readwritelock;

import java.util.Calendar;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ScoreBoard {

	private boolean scoreUpdated = false;
	private int score = 0;
	String health = "Not Available";
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public String getMatchHealth() {
		
		readWriteLock.readLock().lock();
		
		if (scoreUpdated) {
			readWriteLock.readLock().unlock();
			readWriteLock.writeLock().lock();
			try {
				if (scoreUpdated) {
					score = fetchScore();
					scoreUpdated = false;
				}
				readWriteLock.readLock().lock();
			} finally {
				readWriteLock.writeLock().unlock();
			}
		}
		
		// Rate score
		try {
			if (score % 2 == 0) {
				health = "Bad Score";
			} else {
				health = "Good Score";
			}
		} finally {
			readWriteLock.readLock().unlock();
		}
		return health;
	}

	public void updateScore() {
		try {
			readWriteLock.writeLock().lock();
			// perform more task here
			scoreUpdated = true;
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	private int fetchScore() {
		Calendar calender = Calendar.getInstance();
		return calender.get(Calendar.MILLISECOND);
	}

}
