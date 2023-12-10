package com.skilldistillery.blackjack.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.blackjack.entities.Card;
import com.skilldistillery.blackjack.entities.Dealer;
import com.skilldistillery.blackjack.entities.Player;

public class BlackjackApp {

	private Dealer dealer = new Dealer();
	private Player player = new Player();
	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {

		BlackjackApp bja = new BlackjackApp();
		bja.run();
	}

	public void run() throws InterruptedException {
		startGame();
	}

	public void startGame() throws InterruptedException {
		System.out.println(dealer.greeting());
		dealer.shuffleDeck();
		dealer.dealingFirstCards();
		playersTurn();
		dealersTurn();
		rematch();
	}

	public void playersTurn() {
		int userChoice = 0;
		boolean keepGoing = true;
		while (keepGoing) {
			if (dealer.getPlayerHandValue() == 21 && dealer.getDealerHandValue() == 21) {
				System.out.println();
				System.out.println(dealer.displayDealerCards());
				System.out.println(dealer.displayDealerSecondCard());
				System.out.println("Wow! Its a tie!");
				keepGoing = false;
				break;

			}
			if (dealer.getPlayerHandValue() == 21) {
				System.out.println();
				System.out.println("Blackjack! Player wins!");
				player.playerWins++;
				keepGoing = false;
				break;

			}
			if (dealer.getPlayerHandValue() > 21) {
				System.out.println();
				System.out.println("Player busted! Dealer wins!");
				dealer.dealerWins++;
				keepGoing = false;
				break;
			}
			if (dealer.getDealerHandValue() == 21) {
				System.out.println();
				System.out.println(dealer.displayDealerCards());
				System.out.println(dealer.displayDealerSecondCard());
				System.out.println("Blackjack! Dealer wins!");
				dealer.dealerWins++;
				keepGoing = false;
				break;
			}
			if (dealer.getDealerHandValue() > 21) {
				System.out.println();
				System.out.println(dealer.displayDealerCards());
				System.out.println(dealer.displayDealerSecondCard());
				System.out.println("Dealer busted! Player wins!");
				player.playerWins++;
				keepGoing = false;
				break;
			}
			while (dealer.getPlayerHandValue() < 21) {
				try {
					System.out.println("Please enter (1) for HIT, type (2) for STAND.");
					userChoice = sc.nextInt();
					sc.nextLine();
					if (userChoice == 1) {
						System.out.println();
						Card newCard = dealer.dealCard();
						dealer.addPlayerCard(newCard);
						System.out.println("Player new card: " + newCard + " - Value of " + newCard.getValue());
						System.out.println("Player total: " + dealer.getPlayerHandValue());
						if (dealer.getPlayerHandValue() == 21) {
							System.out.println("Blackjack! Player wins!");
							player.playerWins++;
							keepGoing = false;
							break;

						} else if (dealer.getPlayerHandValue() > 21) {
							System.out.println("Player busted! Dealer wins!");
							dealer.dealerWins++;
							keepGoing = false;
							break;

						}
					}
					if (userChoice == 2) {
						System.out.println();
						System.out.println("You stand.");
						System.out.println();
						System.out.println(dealer.displayDealerCards());
						System.out.println(dealer.displayDealerSecondCard());
						keepGoing = false;
						break;

					}
				} catch (InputMismatchException f) {
					System.err.println("Please enter (1) for HIT, type (2) for STAND:  " + f);
					sc.nextLine();
					userChoice = sc.nextInt();
					sc.nextLine();

				} finally {
					keepGoing = false;
				}
			}
		}

	}

	public void dealersTurn() {

		boolean keepGoing = true;
		while (keepGoing) {
			System.out.println();
			if (dealer.getPlayerHandValue() == 21) {
				keepGoing = false;
				break;
			}
			if (dealer.getPlayerHandValue() > 21) {
				keepGoing = false;
				break;
			}
			if (dealer.getDealerHandValue() == 21) {
				keepGoing = false;
				break;
			}
			if (dealer.getDealerHandValue() > 21) {
				keepGoing = false;
				break;
			}
			while (dealer.getDealerHandValue() < 21 && keepGoing) {
				if (dealer.getDealerHandValue() < 17) {
					System.out.println();
					Card newCard = dealer.dealCard();
					dealer.addDealerCard(newCard);
					System.out.println("Dealers new card: " + newCard + " - Value of " + newCard.getValue());
					System.out.println("Dealers total: " + dealer.getDealerHandValue());
					if (dealer.getDealerHandValue() == 21) {
						System.out.println("Blackjack! Dealer wins!");
						dealer.dealerWins++;
						keepGoing = false;
						break;

					} else if (dealer.getDealerHandValue() > 21) {
						System.out.println("Dealer busted! Player wins!");
						player.playerWins++;
						keepGoing = false;
						break;

					}
				}
				if (dealer.getDealerHandValue() >= 17 && dealer.getDealerHandValue() < 21) {
					if (dealer.getPlayerHandValue() == dealer.getDealerHandValue()) {
						System.out.println("Players total: \t" + dealer.getPlayerHandValue());
						System.out.println("Wow! Its a tie!");
						keepGoing = false;
						break;

					} else if (dealer.getDealerHandValue() < dealer.getPlayerHandValue()) {
						System.out.println();
						System.out.println("Dealer stands.");
						System.out.println();
						System.out.println("Dealers total: " + dealer.getDealerHandValue());
						System.out.println("Players total: " + dealer.getPlayerHandValue());
						System.out.println("You win!");
						player.playerWins++;
						keepGoing = false;
						break;

					} else if (dealer.getDealerHandValue() > dealer.getPlayerHandValue()) {
						System.out.println();
						System.out.println("Dealer stands.");
						System.out.println();
						System.out.println("Dealers total: \t" + dealer.getDealerHandValue());
						System.out.println("Players total: \t" + dealer.getPlayerHandValue());
						System.out.println("Dealer wins!");
						dealer.dealerWins++;
						keepGoing = false;
						break;

					} else if (dealer.getDealerHandValue() == dealer.getPlayerHandValue()) {
						System.out.println();
						System.out.println("Dealer stands.");
						System.out.println();
						System.out.println("Dealers total: \t" + dealer.getDealerHandValue());
						System.out.println("Players total: \t" + dealer.getPlayerHandValue());
						System.out.println("\nWow...Its a tie!");
						keepGoing = false;
						break;

					} else {
						System.out.println();
						Card newCard = dealer.dealCard();
						dealer.addDealerCard(newCard);
						System.out.println("Dealers new card: " + newCard + " - Value of " + newCard.getValue());
						System.out.println("Dealers total: " + dealer.getDealerHandValue());
						if (dealer.getDealerHandValue() == 21) {
							System.out.println("Blackjack! Dealer wins!");
							dealer.dealerWins++;
							keepGoing = false;
							break;

						}
						if (dealer.getDealerHandValue() > 21) {
							System.out.println("\nDealer busted! Player wins!");
							player.playerWins++;
							keepGoing = false;
							break;

						}
					}
				}
			}

		}
	}

	public void rematch() throws InterruptedException {
		int userChoice = 1;
		try {
			System.out.println();
			System.out.println("Total wins:\n " + "\n[  " + player.getPlayerWins() + " player  ]\n[  "
					+ dealer.dealerWins + " dealer  ]");
			System.out.println();
			System.out.println("Play Again? (1) for YES, (2) for NO.");
			userChoice = sc.nextInt();
			sc.nextLine();
			if (userChoice == 1) {
				dealer.resetPlayerHand();
				dealer.resetDealerHand();
				dealer.createNewDeck();
				startGame();
			}
			if (userChoice == 2) {
				System.out.println("Goodbye!");
				sc.close();
				System.exit(0);
			}
		} catch (InputMismatchException e) {
			System.err.println("Play Again? (1) for YES, (2) for NO:  " + e);
			userChoice = sc.nextInt();
			sc.nextLine();
		}
	}
}