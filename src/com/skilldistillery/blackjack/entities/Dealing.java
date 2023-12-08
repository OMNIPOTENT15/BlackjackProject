package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Dealing {
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Dealing dlr = new Dealing();
		dlr.run();
	}

	public void run() {
		Deck deck = new Deck();
		deck.shuffleDeck();

		System.out.println("How many card would you like? Please choose from 1 to 52.");
		boolean keepGoing = true;
		int numCards = 0;
		int total = 0;

		do {
			try {
				numCards = sc.nextInt();
				if (numCards <= 0 || numCards > 52) {
					throw new InputMismatchException();
				}
				List<Card> hand = new ArrayList<>(numCards);
				for (int i = 0; i < numCards; i++) {
					Card c = deck.removeCard();
					total += c.getValue();
					hand.add(c);
				}
				keepGoing = false;
				printHandAndValue(hand, total);

			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number 1 through 52.");
				keepGoing = false;
			} finally {
				sc.close();
			}
		} while (keepGoing);
	}

	private void printHandAndValue(List<Card> hand, int value) {
		for (Card card : hand) {
			System.out.println(card);
		}
		System.out.println("Total value: " + value);
	}
}
