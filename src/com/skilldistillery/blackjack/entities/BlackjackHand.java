package com.skilldistillery.blackjack.entities;

import java.util.List;

public class BlackjackHand extends Hand {

	public static String viewCards(List<Card> currentCards) {
		String thisCard = "";
		for (Card card : currentCards) {
			thisCard = card.toString() + " - Value of " + card.getValue();
		}
		return thisCard;
	}

//	public boolean isBlackJack() {
//		if (getHandValue() == 21) {
//			System.out.println("Blackjack!");
//		}
//		return true;
//	}
//
//	public boolean isBust() {
//		if (getHandValue() > 21) {
//			System.out.println("Bust!");
//		}
//		return true;
//	}

	@Override
	public int getHandValue() {
		int total = 0;
		for (Card card : cardsInHand) {
			total += card.getValue();
		}
		return total;
	}
}
