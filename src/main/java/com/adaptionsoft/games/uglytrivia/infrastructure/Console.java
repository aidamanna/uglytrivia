package com.adaptionsoft.games.uglytrivia.infrastructure;

import static java.lang.String.format;

import com.adaptionsoft.games.uglytrivia.domain.Printer;
import com.adaptionsoft.games.uglytrivia.domain.players.Players;

public class Console implements Printer {

    private static final String PLAYER_ADDED_MESSAGE = "%s was added\nThey are player number %s";
    private static final String ROLL_MESSAGE = "%s is the current player\nThey have rolled a %s";
    private static final String CORRECT_ANSWER_MESSAGE = "Answer was correct!!!!\n%s now has %s Gold Coins.";
    private static final String INCORRECT_ANSWER_MESSAGE = "Question was incorrectly answered\n%s was sent to the penalty box";
    private static final String OUT_OF_PENALTY_BOX_MESSAGE = "%s is getting out of the penalty box";
    private static final String IN_PENALTY_BOX_MESSAGE = "%s is not getting out of the penalty box";
    private static final String QUESTION_MESSAGE = "The category is %s\n%s";
    private static final String ADVANCE_PLAYER_MESSAGE = "%s's new location is %s";

    @Override
    public void printPlayers(Players players) {
        for (int player = 0; player < players.getNumberOfPlayers(); player++) {
            print(format(
                PLAYER_ADDED_MESSAGE, players.getName(player), (player + 1)));
        }
    }

    @Override
    public void printRoll(String name, int roll) {
        print(format(ROLL_MESSAGE, name, roll));
    }

    @Override
    public void printCorrectAnswer(String name, int purses) {
        print(format(CORRECT_ANSWER_MESSAGE, name, purses));
    }

    @Override
    public void printIncorrectAnswer(String name) {
        print(format(INCORRECT_ANSWER_MESSAGE, name));
    }

    @Override
    public void printOutOfPenaltyBox(String name) {
        print(format(OUT_OF_PENALTY_BOX_MESSAGE, name));
    }

    @Override
    public void printInPenaltyBox(String name) {
        print(format(IN_PENALTY_BOX_MESSAGE, name));
    }

    @Override
    public void printQuestion(String theme, String question) {
        print(format(QUESTION_MESSAGE, theme, question));
    }

    @Override
    public void printAdvancePlayer(String name, int position) {
        print(format(ADVANCE_PLAYER_MESSAGE, name, position));

    }

    private void print(String message) {
        System.out.println(message);
    }
}
