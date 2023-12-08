package com.skilldistillery.blackjack.entities;

import java.util.Scanner;

public class Dealer extends Player {

	Scanner sc = new Scanner(System.in);
	private Deck deck = new Deck();

	public void startGame() {
		System.out.println("Welcome to Blackjack!");
		System.out.println();
		deck.shuffleDeck();

		Card playerFirstCard = dealingPlayersCard(deck.removeCard());
		System.out.print("You drew a " + playerFirstCard.toString());
		System.out.println(" Value is " + playerFirstCard.getValue());
		Card playerSecondCard = dealingPlayersCard(deck.removeCard());
		System.out.print("You drew a " + playerSecondCard.toString());
		System.out.println(" Value is " + playerSecondCard.getValue());
		int playerTotal = playerFirstCard.getValue() + playerSecondCard.getValue();
		System.out.println("Current total card value " + playerTotal);
		System.out.println();

		Card dealersFirstCard = dealDealersCard(deck.removeCard());
//		System.out.println("Dealer drew a " + dealersFirstCard.toString());
//		System.out.println("Value " + dealersFirstCard.getValue());
		Card dealersSecondCard = dealDealersCard(deck.removeCard());
		System.out.print("Dealer drew a " + dealersFirstCard.toString());

		int dealerTotal = dealersFirstCard.getValue() + dealersSecondCard.getValue();
//		System.out.println("Total card value " + dealerTotal);
//		System.out.println();

		while (playerTotal <= 21 || dealerTotal <= 21)

		{
			if (playerTotal == 21) {
				System.out.println();
				System.out.println("Blackjack! Player 1 wins!");
				break;
			} else if (dealerTotal == 21) {
				System.out.println("Blackjack! Player 2 wins!");
				break;

			}
			if (playerTotal > 21) {
				System.out.println("Bust. Dealer wins!");
			}

			if (dealerTotal > 21) {
				System.out.println("Bust. You win!");
			}
			if (playerTotal < 21) {
				System.out.println();
				System.out.println();
				System.out.println("Would you like to hit or stand? Type (1) for HIT, type (2) for STAND.");
				int choice = sc.nextInt();

				if (choice == 1) {
					Card newCard = hit();
					System.out.println("You drew a " + newCard.toString());
					System.out.println("Card Value is " + newCard.getValue());
					playerTotal += newCard.getValue();
					System.out.println("Total card value is : " + playerTotal);
				} else if (choice == 2) {
					if (dealerTotal < 17) {
						Card newCard = hit();
						System.out.println("Dealer drew a " + dealersSecondCard.toString());
						System.out.println("Dealer drew a " + newCard.toString());
						dealerTotal += newCard.getValue();
						System.out.println("Total Card Value is " + dealerTotal);
						System.out.println(dealerTotal);
					} else if (dealerTotal < playerTotal) {
						System.out.println("You win!.");
					} else {
						System.out.println("Dealer wins!.");

					}
				} else {
					System.out.println("Please enter a valid input.");
				}
				if (dealerTotal < 17) {
					Card newCard = hit();
					System.out.println("Dealer drew a " + dealersSecondCard.toString());
					System.out.println("Dealer drew a " + newCard.toString());
					System.out.println("Card Value is " + newCard.getValue());
					dealerTotal += newCard.getValue();
					System.out.println(dealerTotal);
				} else {
				}

			}
		}
	}

	public Card dealingPlayersCard(Card Card) {
		CardsInHand(Card);
		return Card;
	}

	public Card dealDealersCard(Card Card) {
		CardsInHand(Card);
		return Card;
	}

	public Card hit() {
		Card newCard = deck.removeCard();
		return newCard;
	}
}
