package com.skilldistillery.blackjack.entities;

import java.util.Scanner;

public class Dealer extends Player {

	Scanner sc = new Scanner(System.in);
	private Deck deck = new Deck();
	BlackjackHand blackjackHand = new BlackjackHand();
	Player player = new Player();

	public void startGame() {
		System.out.println("Welcome to Blackjack!");

		deck.shuffleDeck();
		deck.shuffleDeck();
		System.out.println();

		Card playerFirstCard = dealingPlayersCard(deck.removeCard());
		System.out.print("Your first card: " + playerFirstCard.toString());
		System.out.println(" Value:  " + playerFirstCard.getValue());

		Card playerSecondCard = dealingPlayersCard(deck.removeCard());
		System.out.print("Your second card:  " + playerSecondCard.toString());
		System.out.println(" Value:  " + playerSecondCard.getValue());

		int playerTotal = playerFirstCard.getValue() + playerSecondCard.getValue();
		System.out.println("Your total card value: " + playerTotal);
		System.out.println();

		Card dealersFirstCard = dealingDealersCard(deck.removeCard());
		Card dealersSecondCard = dealingDealersCard(deck.removeCard());
		System.out.println(
				"Dealers second card: " + dealersSecondCard.toString() + " Value: " + dealersSecondCard.getValue());

		int dealerTotal = dealersFirstCard.getValue() + dealersSecondCard.getValue();

		if (playerTotal == 21) {
			System.out.println();
			System.out.println("Blackjack! You win!");
		} else if (dealerTotal == 21) {
			System.out.println();
			System.out.println(
					"Dealers second card: " + dealersSecondCard.toString() + " Value: " + dealersSecondCard.getValue());
			System.out.println("Dealer total card value: " + dealerTotal);
			System.out.println("Blackjack! Dealer wins!");
		}
		while (playerTotal < 21) {
			System.out.println();
			System.out.println();
			System.out.println("Would you like to HIT or STAND? Type (1) for HIT, type (2) for STAND.");
			int choice = sc.nextInt();

			if (choice == 1) {
				Card newCard = hit();
				dealingPlayersCard(newCard);
				System.out.print("Your next card: " + newCard.toString());
				System.out.println(" Value: " + newCard.getValue());
				playerTotal += newCard.getValue();
				System.out.println("Your total card value: " + playerTotal);
				if (playerTotal > 21) {
					System.out.println("You Busted. Dealer wins!");
					break;
				}
				if (playerTotal == 21) {
					System.out.println();
					System.out.println("Blackjack! You win!");
					break;

				}
			} else if (choice == 2) {
				System.out.println("Dealers second card: " + dealersSecondCard.toString() + "Value: "
						+ dealersSecondCard.getValue());
				while (dealerTotal < 21) {
					if (dealerTotal >= 17 && dealerTotal < 21) {
						System.out.println("Dealers Total Card Value: " + dealerTotal);
						if (dealerTotal < playerTotal) {
							System.out.println("Dealers total card value: " + dealerTotal);
							System.out.println();
							System.out.println("Your total card value: " + playerTotal);
							System.out.println("You win!");
							break;

						} else {
							System.out.println("Dealer total card value: " + dealerTotal);
							System.out.println();
							System.out.println("Your total card value: " + playerTotal);
							System.out.println("Dealer wins!");
							break;
						}
					}
					if (dealerTotal < 17) {
						Card newCard = hit();
						dealingDealersCard(newCard);
						System.out
								.println("Dealers next card:  " + newCard.toString() + "Value: " + newCard.getValue());
						dealerTotal += newCard.getValue();
						System.out.println("Dealer total card value: " + dealerTotal);
						if (dealerTotal > 21) {
							System.out.println("Dealer Busted. You win!");
							break;

						}
						if (dealerTotal == 21) {
							System.out.println("Blackjack! Dealer wins!");
							break;
						}
					}

				}
			} else {
				System.out.println("Please enter a valid input.");
			}
		}
	}

	public Card dealingPlayersCard(Card Card) {
		player.CardsInHand(Card);
		return Card;
	}

	public Card dealingDealersCard(Card Card) {
		blackjackHand.CardsInHand(Card);
		return Card;
	}

	public Card hit() {
		Card newCard = deck.removeCard();
		return newCard;
	}

//	public boolean wouldYouLikeToPlayAgain() {
//		System.out.println();
//		System.out.println("Would you like to play again? (1) for yes, and (2) for no.");
//		int choice = sc.nextInt();
//		sc.nextLine();
//		if (choice == 1) {
//			return true;
//		} else if (choice == 2) {
//			System.out.println("Goodbye!");
//			return false;
//		} else {
//			System.out.println("Please choose a valid input.");
//		}
//		return false;
//	}
}
