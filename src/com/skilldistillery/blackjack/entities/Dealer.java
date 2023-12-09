package com.skilldistillery.blackjack.entities;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Dealer extends Player {

	Scanner sc = new Scanner(System.in);
	private Deck deck = new Deck();
	BlackjackHand blackjackHand = new BlackjackHand();
	Player player = new Player();

	public void startGame() throws InterruptedException {

		System.out.println("------------- Welcome to Blackjack! -------------");
		System.out.println();

		for (int i = 0; i <= 7; i++) {
			deck.shuffleDeck();
		}
		System.out.println("Dealer is shuffling the deck...");
		TimeUnit.SECONDS.sleep(1);

		TimeUnit.SECONDS.sleep(2);
		System.out.println("Dealer is dealing the cards...");

		TimeUnit.SECONDS.sleep(3);
		System.out.println();
		Card playerFirstCard = hit();
		dealingPlayersCard(playerFirstCard);
		System.out.print("Your first card: " + playerFirstCard.toString());
		System.out.println(" Value: " + playerFirstCard.getValue());

		Card playerSecondCard = hit();
		dealingPlayersCard(playerSecondCard);
		System.out.print("Your second card: " + playerSecondCard.toString());
		System.out.println(" Value: " + playerSecondCard.getValue());

		int playerTotal = playerFirstCard.getValue() + playerSecondCard.getValue();
		System.out.println("Your current total card value: " + playerTotal);
		System.out.println();

		Card dealersFirstCard = hit();
		dealingDealersCard(dealersFirstCard);
		Card dealersSecondCard = hit();
		dealingDealersCard(dealersSecondCard);
		System.out.println(
				"Dealers second card: " + dealersSecondCard.toString() + " Value: " + dealersSecondCard.getValue());

		int dealerTotal = dealersFirstCard.getValue() + dealersSecondCard.getValue();
		System.out.println();

		boolean keepGoing = true;
		while (keepGoing) {
			if (playerTotal == 21) {
				System.out.println("Blackjack! You win!");
				player.playerWins++;
				keepGoing = false;
				rematch();

			}

			if (dealerTotal == 21) {
				System.out.println("Dealers first card: " + dealersFirstCard.toString() + " Value: "
						+ dealersFirstCard.getValue());
				System.out.println("Dealers second card: " + dealersSecondCard.toString() + " Value: "
						+ dealersSecondCard.getValue());
				System.out.println("Dealer new total card value: " + dealerTotal);
				System.out.println("Blackjack! Dealer wins!");
				blackjackHand.dealerWins++;
				keepGoing = false;
				rematch();

			}

			int userChoice = 0;
			try {
				while (playerTotal < 21) {
					System.out.println("Please enter (1) for HIT, type (2) for STAND.");
					userChoice = sc.nextInt();
					if (userChoice == 1) {
						Card newCard = hit();
						dealingPlayersCard(newCard);
						System.out.print("Your next card: " + newCard.toString());
						System.out.println(" Value: " + newCard.getValue());
						playerTotal += newCard.getValue();
						System.out.println("Your new total card value: " + playerTotal);
						if (playerTotal > 21) {
							System.out.println("You Busted. Dealer wins!");
							blackjackHand.dealerWins++;
							keepGoing = false;
							rematch();
						}
					}
					if (userChoice == 2) {
						break;
					}
				}

				if (userChoice == 2) {
					System.out.println("Dealers first card: " + dealersFirstCard.toString() + " Value: "
							+ dealersFirstCard.getValue());
					System.out.println("Dealers second card: " + dealersSecondCard.toString() + " Value: "
							+ dealersSecondCard.getValue());

					while (dealerTotal < 21) {
						if (dealerTotal >= 17 && dealerTotal < 21) {
							if (dealerTotal < playerTotal) {
								System.out.println();
								System.out.println("Dealer final card value: " + dealerTotal);
								System.out.println("Your final card value: " + playerTotal);
								System.out.println("You win!");
								player.playerWins++;
								keepGoing = false;
								rematch();

							} else if (dealerTotal > playerTotal) {
								System.out.println();
								System.out.println("Dealer final card value: " + dealerTotal);
								System.out.println("Your final card value: " + playerTotal);
								System.out.println("Dealer wins!");
								blackjackHand.dealerWins++;
								keepGoing = false;
								rematch();

							} else if (dealerTotal == playerTotal) {
								System.out.println();
								System.out.println("Dealer final card value: " + dealerTotal);
								System.out.println("Your final card value: " + playerTotal);
								System.out.println("Wow...Its a tie!");
								keepGoing = false;
								rematch();
							}
						}
						if (dealerTotal < 17) {
							Card newCard = hit();
							dealingDealersCard(newCard);
							System.out.println(
									"Dealers next card: " + newCard.toString() + " Value: " + newCard.getValue());
							dealerTotal += newCard.getValue();
							System.out.println("Dealer new total card value: " + dealerTotal);
							if (dealerTotal > 21) {
								System.out.println("Dealer Busts. You win!");
								player.playerWins++;
								keepGoing = false;
								rematch();

							}
							if (dealerTotal == 21) {
								blackjackHand.dealerWins++;
								System.out.println("Blackjack! Dealer wins!");
								keepGoing = false;
							}
						}
					}
				}
			}

			catch (InputMismatchException f) {
				System.err.println(f);
				keepGoing = false;
				break;

			} finally {
				keepGoing = false;
			}
		}
	}

	public void rematch() throws InterruptedException {
		int choice = 0;
		try {
			System.out.println();
			System.out.println("Total score:\n " + "\n[  " + player.playerWins + " player  ]\n[  "
					+ blackjackHand.dealerWins + " dealer  ]");
			System.out.println();
			System.out.println("Play Again? (1) for yes, (2) for no.");
			choice = sc.nextInt();
			if (choice == 1) {
				startGame();
			}
			if (choice == 2) {
				System.out.println("Goodbye!");
				sc.close();
				System.exit(0);
			}
		} catch (InputMismatchException e) {
			System.err.println(e);
		}
	}

	public List<Card> dealingPlayersCard(Card Card) {
		player.CardsInHand(Card);
		return myCurrentCards;
	}

	public List<Card> dealingDealersCard(Card Card) {
		blackjackHand.CardsInHand(Card);
		return blackjackHand.dealerCurrentCards;
	}

	public Card hit() {
		Card newCard = deck.removeCard();
		return newCard;
	}
}
