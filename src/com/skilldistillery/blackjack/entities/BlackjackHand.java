package com.skilldistillery.blackjack.entities;

import java.util.List;

public class BlackjackHand extends Hand {

	protected int dealerWins = 0;
	List<Card> dealerCurrentCards = myCards;

	public List<Card> CardsInHand(Card Card) {
		myCards.add(Card);
		return myCards;
	}

	public String viewCards(List<Card> dealerCurrentCards) {
		String name = "";
		for (Card card : dealerCurrentCards) {
			name = card.toString();
		}
		return name;

	}
}
