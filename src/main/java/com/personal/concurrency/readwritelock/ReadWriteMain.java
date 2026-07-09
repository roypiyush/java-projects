package com.personal.concurrency.readwritelock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadWriteMain {

	public static void main(String[] args) {
		final int threadCount = 2;
		final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		final ScoreBoard scoreBoard = new ScoreBoard();
		executorService.execute(new ScoreUpdateThread(scoreBoard));
		executorService.execute(new ScoreHealthThread(scoreBoard));
		executorService.shutdown();
	}

}
