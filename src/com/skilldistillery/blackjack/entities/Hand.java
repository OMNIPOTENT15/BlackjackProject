package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	protected List<Card> myCards = new ArrayList<>();

	public List<Card> CardsInHand(Card Card) {
		myCards.add(Card);
		return myCards;
	}
}
