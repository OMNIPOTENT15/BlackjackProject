package com.skilldistillery.blackjack.app;

import com.skilldistillery.blackjack.entities.Dealer;

public class BlackjackApp {

	public static void main(String[] args) throws InterruptedException {

		BlackjackApp bja = new BlackjackApp();
		bja.run();
	}

	public void run() throws InterruptedException {
		Dealer d = new Dealer();
		d.startGame();
	}
}
