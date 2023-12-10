package com.skilldistillery.blackjack.entities;

import java.util.List;

public class Player {

	protected Hand hand = new BlackjackHand();
	public int playerWins;

	public Player() {
	};

	public Card addPlayerCard(Card card) {
		hand.addCard(card);
		return card;
	}

	public int getPlayerWins() {
		return playerWins;
	}

	public void setPlayerWins(int playerWins) {
		this.playerWins = playerWins;
	}

	public List<Card> displayPlayerCards() {
		List<Card> currentCards = hand.getCardsInHand();
		return currentCards;
	}

	public int getPlayerHandValue() {
		return hand.getHandValue();
	}

	public void resetPlayerHand() {
		hand = new BlackjackHand();
	}

}
