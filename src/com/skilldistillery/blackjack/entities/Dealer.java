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
		deck = new Deck();
		for (int i = 0; i <= 7; i++) {
			deck.shuffleDeck();
		}
		System.out.println("Dealer is shuffling the deck...");
		TimeUnit.SECONDS.sleep(1);

		TimeUnit.SECONDS.sleep(2);
		System.out.println("Dealer is dealing the cards...");

		TimeUnit.SECONDS.sleep(2);
		System.out.println();
		Card playerFirstCard = hit();
		dealingPlayersCard(playerFirstCard);
		System.out.print("Your first card: \t" + playerFirstCard.toString());
		System.out.println(" Value: " + playerFirstCard.getValue());

		Card playerSecondCard = hit();
		dealingPlayersCard(playerSecondCard);
		System.out.print("Your second card: \t" + playerSecondCard.toString());
		System.out.println(" Value: " + playerSecondCard.getValue());

		int playerTotal = playerFirstCard.getValue() + playerSecondCard.getValue();
		System.out.println("Total card value: \t" + playerTotal);
		System.out.println();

		Card dealersFirstCard = hit();
		dealingDealersCard(dealersFirstCard);
		Card dealersSecondCard = hit();
		dealingDealersCard(dealersSecondCard);
		System.out.println(
				"Dealers second card: \t" + dealersSecondCard.toString() + " Value: " + dealersSecondCard.getValue());

		int dealerTotal = dealersFirstCard.getValue() + dealersSecondCard.getValue();
		System.out.println();

		boolean keepGoing = true;
		while (keepGoing) {

			if (playerTotal > 21) {
				System.out.println("You busted! Dealer wins!");
				blackjackHand.dealerWins++;
				keepGoing = false;
				rematch();
			}

			if (dealerTotal > 21) {
				System.out.println("Dealers first card: \t" + dealersFirstCard.toString() + " Value: "
						+ dealersFirstCard.getValue());
				System.out.println("Dealers second card: \t" + dealersSecondCard.toString() + " Value: "
						+ dealersSecondCard.getValue());
				System.out.println("New total value: \t" + dealerTotal);
				System.out.println("Dealer busted! You win!");
				keepGoing = false;
				player.playerWins++;
				rematch();
			}

			if (playerTotal == 21 && dealerTotal == 21) {
				System.out.println("Dealers first card: \t" + dealersFirstCard.toString() + " Value: "
						+ dealersFirstCard.getValue());
				System.out.println("Dealers second card: \t" + dealersSecondCard.toString() + " Value: "
						+ dealersSecondCard.getValue());
				System.out.println("New total value: \t" + dealerTotal);
				System.out.println("Wow... Its a tie.");
				keepGoing = false;
				rematch();
			}

			if (playerTotal == 21) {
				System.out.println("Blackjack! You win!");
				player.playerWins++;
				keepGoing = false;
				rematch();
			} else if (dealerTotal == 21) {
				System.out.println("Dealers first card: \t" + dealersFirstCard.toString() + " Value: "
						+ dealersFirstCard.getValue());
				System.out.println("Dealers second card: \t" + dealersSecondCard.toString() + " Value: "
						+ dealersSecondCard.getValue());
				System.out.println("New total value: \t" + dealerTotal);
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
						System.out.print("You hit. Next card: \t" + newCard.toString());
						System.out.println(" Value: " + newCard.getValue());
						playerTotal += newCard.getValue();
						System.out.println("New total value:\t" + playerTotal);
						if (playerTotal == 21) {
							System.out.println("Blackjack! You win!");
							player.playerWins++;
							keepGoing = false;
							rematch();
						}
						if (playerTotal > 21) {
							System.out.println("\nYou busted! Dealer wins!");
							blackjackHand.dealerWins++;
							keepGoing = false;
							rematch();
						}
					}
					if (userChoice == 2) {
						System.out.println();
						System.out.println("You stand.");
						System.out.println();
						break;
					}
				}

				if (userChoice == 2) {
					System.out.println("Dealers first card: \t" + dealersFirstCard.toString() + " Value: "
							+ dealersFirstCard.getValue());
					System.out.println("Dealers second card: \t" + dealersSecondCard.toString() + " Value: "
							+ dealersSecondCard.getValue());

					while (dealerTotal < 21) {
						if (dealerTotal >= 17 && dealerTotal < 21) {
							if (dealerTotal < playerTotal) {
								System.out.println();
								System.out.println("Dealer stands.");
								System.out.println();
								System.out.println("Dealer total value: " + dealerTotal);
								System.out.println("Your total value: " + playerTotal);
								System.out.println("\nYou win!");
								player.playerWins++;
								keepGoing = false;
								rematch();

							} else if (dealerTotal > playerTotal) {
								System.out.println();
								System.out.println("Dealer stands.");
								System.out.println();
								System.out.println("Dealer total value: \t" + dealerTotal);
								System.out.println("Your total value: \t" + playerTotal);
								System.out.println("\nDealer wins!");
								blackjackHand.dealerWins++;
								keepGoing = false;
								rematch();

							} else if (dealerTotal == playerTotal) {
								System.out.println();
								System.out.println();
								System.out.println("Dealer stands.");
								System.out.println();
								System.out.println("Dealer total value: \t" + dealerTotal);
								System.out.println("Your total value: \t" + playerTotal);
								System.out.println("\nWow...Its a tie!");
								keepGoing = false;
								rematch();
							}
						}
						if (dealerTotal < 17) {
							Card newCard = hit();
							dealingDealersCard(newCard);
							System.out.println(
									"Dealer hits. Next card: " + newCard.toString() + " Value: " + newCard.getValue());
							dealerTotal += newCard.getValue();
							System.out.println("New total value: \t" + dealerTotal);
							if (dealerTotal > 21) {
								System.out.println("\nDealer busted! You win!");
								player.playerWins++;
								keepGoing = false;
								rematch();

							}
							if (dealerTotal == 21) {
								blackjackHand.dealerWins++;
								System.out.println("\nBlackjack! Dealer wins!");
								keepGoing = false;
								rematch();
							}
						}
					}
				}
			}

			catch (InputMismatchException f) {
				System.err.println("Please enter (1) for HIT, type (2) for STAND:  " + f);
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
			System.out.println("Play Again? (1) for YES, (2) for NO.");
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
			System.err.println("Play Again? (1) for YES, (2) for NO:  " + e);
			System.exit(0);
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
