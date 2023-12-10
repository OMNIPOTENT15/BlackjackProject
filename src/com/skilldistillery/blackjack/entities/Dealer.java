package com.skilldistillery.blackjack.entities;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dealer extends Player {

	protected Hand dealerHand = new BlackjackHand();
	private Deck deck = new Deck();
	public int dealerWins;

	public Card dealCard() {
		Card newCard = deck.removeCard();
		return newCard;
	}

	public void shuffleDeck() throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			deck.shuffleDeck();
			System.out.println("Dealer is shuffling the deck...");
			TimeUnit.SECONDS.sleep(1);
		}
	}

	public String greeting() {
		return "------------- Welcome to Blackjack -------------\n";
	}

	public void dealingFirstCards() throws InterruptedException {
		System.out.println("Dealer is dealing the cards...");
		TimeUnit.SECONDS.sleep(2);
		System.out.println();
		Card card1 = dealCard();
		Card card2 = dealCard();
		addPlayerCard(card1);
		addPlayerCard(card2);
		System.out.println("Players first card: " + card1 + " - Value of " + card1.getValue());
		System.out.println("Players second card: " + card2 + " - Value of " + card2.getValue());
		System.out.println("Players total: " + getPlayerHandValue());

		Card card3 = dealCard();
		Card card4 = dealCard();
		addDealerCard(card3);
		addDealerCard(card4);
		System.out.println();

		System.out.println(displayDealerSecondCard());
		System.out.println();
	}

	public Card addDealerCard(Card card) {
		dealerHand.addCard(card);
		return card;
	}

	public int getDealerHandValue() {
		return dealerHand.getHandValue();
	}

	public String displayDealerCards() {
		List<Card> currentCards = dealerHand.getCardsInHand();
		for (Card card : currentCards) {
			return "Dealers first card: " + card.toString() + " - Value of " + card.getValue();
		}
		return "";
	}

	public String displayDealerSecondCard() {
		List<Card> currentCards = dealerHand.getCardsInHand();
		return "Dealers second Card: " + currentCards.get(1).toString() + " - Value of "
				+ currentCards.get(1).getValue();
	}

	public void resetDealerHand() {
		dealerHand = new BlackjackHand();
	}
}
