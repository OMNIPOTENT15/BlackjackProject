package com.skilldistillery.blackjack.entities;

import java.util.List;

public class Player extends Hand {

	List<Card> myCurrentCards = myCards;

	public String countCards() {
		int currentVal = 0;
		for (Card card : myCurrentCards) {
			currentVal += card.getValue();
		}
		return "Your card count is " + currentVal;
	}

	
}
