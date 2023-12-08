package com.skilldistillery.blackjack.entities;

public enum Suit {

	HEARTS("Hearts"), SPADES("Spades"), CLUBS("Clubs"), DIAMONDS("Diamonds");

	final private String name;

	Suit(String s) {
		this.name = s;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getString() {
		return name;
	}
}
