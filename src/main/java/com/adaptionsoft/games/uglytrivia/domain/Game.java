package com.adaptionsoft.games.uglytrivia.domain;

import com.adaptionsoft.games.uglytrivia.domain.questions.GameQuestions;
import com.adaptionsoft.games.uglytrivia.domain.questions.Theme;

import java.util.List;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.*;

public class Game {

	private Printer printer;
	private GameQuestions questions;
	private List<Player> players;

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public  Game(Printer printer, GameQuestions questions, List<Player> players) {
		this.printer = printer;
		this.questions = questions;
		this.players = players;
    }

	public void add(List<Player> players) {
    	for(int playerNumber = 0; playerNumber < players.size(); playerNumber++) {
			printer.print(players.get(playerNumber).getName() + " was added");
			printer.print("They are player number " + (playerNumber + 1));
		}
	}

	public void roll(int roll) {
		printer.print(players.get(currentPlayer).getName() + " is the current player");
		printer.print("They have rolled a " + roll);

		if (players.get(currentPlayer).isInPenaltyBox()) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				printer.print(players.get(currentPlayer).getName() + " is getting out of the penalty box");

				advancePlayer(roll);
				printer.print(questions.getBy(currentTheme()));
			} else {
				isGettingOutOfPenaltyBox = false;
				printer.print(players.get(currentPlayer).getName() + " is not getting out of the penalty box");
				}

		} else {
			advancePlayer(roll);
			printer.print(questions.getBy(currentTheme()));
		}

	}

	private void advancePlayer(int roll) {
		players.get(currentPlayer).advance(roll);

		printer.print(players.get(currentPlayer).getName()
				+ "'s new location is "
				+ players.get(currentPlayer).getPosition());
		printer.print("The category is " + currentTheme().getDescription());
	}

	private Theme currentTheme() {
		int position = players.get(currentPlayer).getPosition();
		if (position == 0 || position == 4 || position == 8) return POP;
		if (position == 1 || position == 5 || position == 9) return SCIENCE;
		if (position == 2 || position == 6 || position == 10) return SPORTS;
		return ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		if (players.get(currentPlayer).isInPenaltyBox()){
			if (isGettingOutOfPenaltyBox) {
				return correctAnswer();
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
		} else {
			return correctAnswer();
		}
	}

	private boolean correctAnswer() {
		printer.print("Answer was correct!!!!");
		players.get(currentPlayer).increasePurse();
		printer.print(players.get(currentPlayer).getName()
				+ " now has "
				+ players.get(currentPlayer).getPurses()
				+ " Gold Coins.");

		boolean winner = players.get(currentPlayer).didPlayerWin();
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;

		return winner;
	}

	public boolean wrongAnswer(){
		printer.print("Question was incorrectly answered");
		printer.print(players.get(currentPlayer).getName()+ " was sent to the penalty box");
		players.get(currentPlayer).setInPenaltyBox();

		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}
}
