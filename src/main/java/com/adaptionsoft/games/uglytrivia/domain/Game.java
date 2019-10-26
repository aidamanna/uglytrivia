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
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

				printer.print(players.get(currentPlayer)
						+ "'s new location is "
						+ places[currentPlayer]);
				printer.print("The category is " + currentCategory());
				askQuestion();
			} else {
				printer.print(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}

		} else {

			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

			printer.print(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
			printer.print("The category is " + currentCategory());
			askQuestion();
		}

	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			printer.print(questions.get(POP).list().remove(0));
		if (currentCategory() == "Science")
			printer.print(questions.get(SCIENCE).list().remove(0));
		if (currentCategory() == "Sports")
			printer.print(questions.get(SPORTS).list().remove(0));
		if (currentCategory() == "Rock")
			printer.print(questions.get(ROCK).list().remove(0));
	}


	private String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
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
