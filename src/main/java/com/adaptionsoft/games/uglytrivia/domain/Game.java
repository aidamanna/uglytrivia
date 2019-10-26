package com.adaptionsoft.games.uglytrivia.domain;

import com.adaptionsoft.games.uglytrivia.domain.questions.Theme;
import com.adaptionsoft.games.uglytrivia.domain.questions.ThemeQuestions;

import java.util.ArrayList;
import java.util.Map;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.*;

public class Game {

	private Printer printer;
	private Map<Theme, ThemeQuestions> questions;

    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public  Game(Printer printer, Map<Theme, ThemeQuestions> questions) {
		this.printer = printer;
		this.questions = questions;
    }

	public void add(String playerName) {
	    players.add(playerName);
    	int playerNumber = players.size();
	    places[playerNumber] = 0;
	    purses[playerNumber] = 0;
	    inPenaltyBox[playerNumber] = false;

	    printer.print(playerName + " was added");
		printer.print("They are player number " + playerNumber);
	}

	public void roll(int roll) {
		printer.print(players.get(currentPlayer) + " is the current player");
		printer.print("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				printer.print(players.get(currentPlayer) + " is getting out of the penalty box");

				advancePlayer(roll);
				askQuestion();
			} else {
				isGettingOutOfPenaltyBox = false;
				printer.print(players.get(currentPlayer) + " is not getting out of the penalty box");
				}

		} else {
			advancePlayer(roll);
			askQuestion();
		}

	}

	private void advancePlayer(int roll) {
		places[currentPlayer] = places[currentPlayer] + roll;
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

		printer.print(players.get(currentPlayer)
				+ "'s new location is "
				+ places[currentPlayer]);
		printer.print("The category is " + currentCategory().getDescription());
	}

	private void askQuestion() {
		if (currentCategory() == POP)
			printer.print(questions.get(POP).list().remove(0));
		if (currentCategory() == SCIENCE)
			printer.print(questions.get(SCIENCE).list().remove(0));
		if (currentCategory() == SPORTS)
			printer.print(questions.get(SPORTS).list().remove(0));
		if (currentCategory() == ROCK)
			printer.print(questions.get(ROCK).list().remove(0));
	}

	private Theme currentCategory() {
		int position = places[currentPlayer];
		if (position == 0 || position == 4 || position == 8) return POP;
		if (position == 1 || position == 5 || position == 9) return SCIENCE;
		if (position == 2 || position == 6 || position == 10) return SPORTS;
		return ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
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
		purses[currentPlayer]++;
		printer.print(players.get(currentPlayer)
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");

		boolean winner = didPlayerWin();
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;

		return winner;
	}

	public boolean wrongAnswer(){
		printer.print("Question was incorrectly answered");
		printer.print(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;

		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
