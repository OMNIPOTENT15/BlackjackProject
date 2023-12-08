package com.skilldistillery.blackjack.app;

import com.skilldistillery.blackjack.entities.Dealer;
import com.skilldistillery.blackjack.entities.Player;

public class BlackjackApp {

	public static void main(String[] args) {

		BlackjackApp bja = new BlackjackApp();
		bja.run();
	}

	public void run() {
		Player p = new Player();
		Dealer d = new Dealer();
	}
}
