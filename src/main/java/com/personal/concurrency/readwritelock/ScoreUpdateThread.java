package com.personal.concurrency.readwritelock;

public class ScoreUpdateThread implements Runnable {

	private ScoreBoard scoreBoard;

	public ScoreUpdateThread(ScoreBoard scoreTable) {
		this.scoreBoard = scoreTable;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Score Updated.");
			scoreBoard.updateScore();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
