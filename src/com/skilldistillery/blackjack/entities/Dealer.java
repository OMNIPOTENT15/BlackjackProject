package com.skilldistillery.blackjack.entities;

import java.util.Scanner;

public class Dealer extends Player {

	Scanner sc = new Scanner(System.in);
	private Deck deck = new Deck();
	BlackjackHand blackjackHand = new BlackjackHand();
	Player player = new Player();

	public void startGame() {
		System.out.println("Welcome to Blackjack!");
		boolean playAgain = true;

		Card playerFirstCard = null;
		Card playerSecondCard = null;
		Card dealersFirstCard = null;
		Card dealersSecondCard = null;
		int playerTotal = 0;
		while (playAgain) {
			deck.shuffleDeck();
			deck.shuffleDeck();
			System.out.println();

			playerFirstCard = dealingPlayersCard(deck.removeCard());
			System.out.print("You drew a " + playerFirstCard.toString());
			System.out.println(" Value is " + playerFirstCard.getValue());
			playerSecondCard = dealingPlayersCard(deck.removeCard());
			System.out.print("You drew a " + playerSecondCard.toString());
			System.out.println(" Value is " + playerSecondCard.getValue());
			playerTotal = playerFirstCard.getValue() + playerSecondCard.getValue();
			System.out.println("Your total card value " + playerTotal);
			System.out.println();

			dealersFirstCard = dealingDealersCard(deck.removeCard());
			dealersSecondCard = dealingDealersCard(deck.removeCard());
			System.out.println("Dealer drew a " + dealersFirstCard.toString());

			int dealerTotal = dealersFirstCard.getValue() + dealersSecondCard.getValue();

			if (playerTotal == 21) {
				System.out.println();
				System.out.println("Blackjack! You win!");
				playAgain = wouldYouLikeToPlayAgain();
			} else if (dealerTotal == 21) {
				System.out.println();
				System.out.println("Blackjack! Dealer wins!");
				playAgain = wouldYouLikeToPlayAgain();
			}

			while (playerTotal < 21) {
				System.out.println();
				System.out.println();
				System.out.println("Would you like to hit or stand? Type (1) for HIT, type (2) for STAND.");
				int choice = sc.nextInt();

				if (choice == 1) {
					Card newCard = hit();
					dealingPlayersCard(newCard);
					System.out.println("You drew a " + newCard.toString());
					System.out.println("Card Value is " + newCard.getValue());
					playerTotal += newCard.getValue();
					System.out.println("Your total card value is : " + playerTotal);
					if (playerTotal > 21) {
						System.out.println("You Busted. Dealer wins!");
						playAgain = wouldYouLikeToPlayAgain();
					}
					if (playerTotal == 21) {
						System.out.println();
						System.out.println("Blackjack! You win!");
						playAgain = wouldYouLikeToPlayAgain();
					}
				} else if (choice == 2) {

					if (dealerTotal < 17) {
						Card newCard = hit();
						dealingDealersCard(newCard);
						System.out.println("Dealer drew a " + dealersSecondCard.toString());
						System.out.println("Dealer drew a " + newCard.toString());
						dealerTotal += newCard.getValue();
						System.out.println("Dealer total card value is " + dealerTotal);
						if (dealerTotal > 21) {
							System.out.println("Dealer Busted. You win!");
							playAgain = wouldYouLikeToPlayAgain();
						}
						if (dealerTotal == 21) {
							System.out.println("Blackjack! Dealer wins!");
							playAgain = wouldYouLikeToPlayAgain();
						}
						if (dealerTotal >= 17 && dealerTotal < 21) {
							System.out.println("Dealer drew a " + dealersSecondCard.toString());
							System.out.println("Dealer Total Card is " + dealerTotal);
							if (dealerTotal < playerTotal) {
								System.out.println("Dealer total card value is " + dealerTotal);
								System.out.println();
								System.out.println("Your total card value is " + playerTotal);
								System.out.println("You win!");
								playAgain = wouldYouLikeToPlayAgain();
							} else {
								System.out.println("Dealer total card value is " + dealerTotal);
								System.out.println();
								System.out.println("Your total card value is " + playerTotal);
								System.out.println("Dealer wins!");
								playAgain = wouldYouLikeToPlayAgain();
							}
						}

					}

				} else {
					System.out.println("Please enter a valid input.");
				}
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

	public boolean wouldYouLikeToPlayAgain() {
		System.out.println();
		System.out.println("Would you like to play again? (1) for yes, and (2) for no.");
		int choice = sc.nextInt();
		sc.nextLine();
		if (choice == 1) {
			return true;
		} else if (choice == 2) {
			System.out.println("Goodbye!");
		} else {
			System.out.println("Please choose a valid input.");
		}
		return true;
	}
}
