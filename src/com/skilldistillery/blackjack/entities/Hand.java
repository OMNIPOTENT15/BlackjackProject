package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Hand {
	protected List<Card> cardsInHand = new ArrayList<>();

	public Hand() {
	}

	public List<Card> getCardsInHand() {
		return cardsInHand;
	}

	public void addCard(Card card) {
		cardsInHand.add(card);
	}

	public void clearHand() {
		cardsInHand.removeAll(cardsInHand);
	}

	public abstract int getHandValue();

	public void setCardsInHand(List<Card> cardsInHand) {
		this.cardsInHand = cardsInHand;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardsInHand);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hand other = (Hand) obj;
		return Objects.equals(cardsInHand, other.cardsInHand);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hand [cardsInHand=").append(cardsInHand).append("]");
		return builder.toString();
	}

}
