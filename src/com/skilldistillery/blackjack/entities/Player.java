package com.skilldistillery.blackjack.entities;

import java.util.List;

public class Player extends Hand {

	List<Card> myCurrentCards = myCards;
	protected int playerWins;

	public String viewCards(List<Card> myCurrentCards) {
		String name = "";
		for (Card card : myCurrentCards) {
			name = card.toString();
		}
		return name;
	}

}
