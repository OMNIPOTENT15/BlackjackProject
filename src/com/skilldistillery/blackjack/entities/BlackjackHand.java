package com.skilldistillery.blackjack.entities;

import java.util.List;

public class BlackjackHand extends Hand {
	public List<Card> CardsInHand(Card Card) {
		myCards.add(Card);
		return myCards;
	}
}
