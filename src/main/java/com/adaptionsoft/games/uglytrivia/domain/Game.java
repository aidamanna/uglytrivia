package com.adaptionsoft.games.uglytrivia.domain;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.POP;
import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.ROCK;
import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.SCIENCE;
import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.SPORTS;

import com.adaptionsoft.games.uglytrivia.domain.players.Players;
import com.adaptionsoft.games.uglytrivia.domain.questions.GameQuestions;
import com.adaptionsoft.games.uglytrivia.domain.questions.Theme;

public class Game {

		private Printer printer;
		private GameQuestions questions;
		private Players players;

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

  	public Game(Printer printer, GameQuestions questions, Players players) {
			this.printer = printer;
			this.questions = questions;
			this.players = players;
    }

	public void start() {
		for(int playerNumber = 0; playerNumber < players.getNumberOfPlayers(); playerNumber++) {
				printer.print(players.getName(playerNumber) + " was added");
			  printer.print("They are player number " + (playerNumber + 1));
		}
	}

	public void roll(int roll) {
		printer.print(players.getName(currentPlayer) + " is the current player");
		printer.print("They have rolled a " + roll);

		if (players.isInPenaltyBox(currentPlayer)) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				printer.print(players.getName(currentPlayer) + " is getting out of the penalty box");

				advancePlayer(roll);
				printer.print(questions.getBy(currentTheme()));
			} else {
				isGettingOutOfPenaltyBox = false;
				printer.print(players.getName(currentPlayer) + " is not getting out of the penalty box");
				}

		} else {
			advancePlayer(roll);
			printer.print(questions.getBy(currentTheme()));
		}

	}

	private void advancePlayer(int roll) {
		players.advance(currentPlayer, roll);

		printer.print(players.getName(currentPlayer)
				+ "'s new location is "
				+ players.getPosition(currentPlayer));
		printer.print("The category is " + currentTheme().getDescription());
	}

	private Theme currentTheme() {
		int position = players.getPosition(currentPlayer);
		if (position == 0 || position == 4 || position == 8) return POP;
		if (position == 1 || position == 5 || position == 9) return SCIENCE;
		if (position == 2 || position == 6 || position == 10) return SPORTS;
		return ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		if (players.isInPenaltyBox(currentPlayer)){
			if (isGettingOutOfPenaltyBox) {
				return correctAnswer();
			} else {
				currentPlayer++;
				if (currentPlayer == players.getNumberOfPlayers()) currentPlayer = 0;
				return true;
			}
		} else {
			return correctAnswer();
		}
	}

	private boolean correctAnswer() {
		printer.print("Answer was correct!!!!");
		players.increasePurse(currentPlayer);
		printer.print(players.getName(currentPlayer)
				+ " now has "
				+ players.getPurses(currentPlayer)
				+ " Gold Coins.");

		boolean winner = players.didPlayerWin(currentPlayer);
		currentPlayer++;
		if (currentPlayer == players.getNumberOfPlayers()) currentPlayer = 0;

		return winner;
	}

	public boolean wrongAnswer(){
		printer.print("Question was incorrectly answered");
		printer.print(players.getName(currentPlayer) + " was sent to the penalty box");
		players.setInPenaltyBox(currentPlayer);

		currentPlayer++;
		if (currentPlayer == players.getNumberOfPlayers()) currentPlayer = 0;
		return true;
	}
}
