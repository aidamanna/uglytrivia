package com.adaptionsoft.games.uglytrivia.domain;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.POP;
import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.ROCK;
import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.SCIENCE;
import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.SPORTS;
import static java.lang.String.format;

import com.adaptionsoft.games.uglytrivia.domain.players.Players;
import com.adaptionsoft.games.uglytrivia.domain.questions.GameQuestions;
import com.adaptionsoft.games.uglytrivia.domain.questions.Theme;

public class Game {

    public static final String PLAYER_ADDED_MESSAGE = "%s was added\nThey are player number %s";
    public static final String OUT_OF_PENALTY_BOX_MESSAGE = "%s is getting out of the penalty box";
    public static final String IN_PENALTY_BOX_MESSAGE = "%s is not getting out of the penalty box";
    public static final String CORRECT_ANSWER_MESSAGE = "Answer was correct!!!!\n%s now has %s Gold Coins.";
    public static final String INCORRECT_ANSWER_MESSAGE = "Question was incorrectly answered\n%s was sent to the penalty box";
    public static final String ROLL_MESSAGE = "%s is the current player\nThey have rolled a %s";

    private Printer printer;
    private GameQuestions questions;
    private Players players;

    private int currentPlayer;
    private boolean gameFinished;

    public Game(Printer printer, GameQuestions questions, Players players) {
        this.printer = printer;
        this.questions = questions;
        this.players = players;
        this.currentPlayer = 0;
        this.gameFinished = false;
    }

    public void start() {
        for (int player = 0; player < players.getNumberOfPlayers(); player++) {
            printer.print(format(
                    PLAYER_ADDED_MESSAGE, players.getName(player), (player + 1)));
        }
    }

    public void roll(int roll) {
        printer.print(format(ROLL_MESSAGE, players.getName(currentPlayer), roll));

        updatePlayerPenaltyBoxStatus(roll);

        if (!players.isInPenaltyBox(currentPlayer)) {
            advancePlayer(roll);
            askQuestion();
        }

    }

    public void correctAnswer() {
        if (players.isInPenaltyBox(currentPlayer)) {
            assignNextPlayer();
            return;
        }

        players.increasePurse(currentPlayer);

        printer.print(
                format(
                        CORRECT_ANSWER_MESSAGE,
                        players.getName(currentPlayer),
                        players.getPurses(currentPlayer)));

        gameFinished = players.didPlayerWin(currentPlayer);

        assignNextPlayer();
    }

    public void wrongAnswer() {
        players.setInPenaltyBox(currentPlayer);

        printer.print(format(INCORRECT_ANSWER_MESSAGE, players.getName(currentPlayer)));
        assignNextPlayer();
    }

    public boolean isNotFinished() {
        return gameFinished;
    }

    private void updatePlayerPenaltyBoxStatus(int roll) {
        if (!players.isInPenaltyBox(currentPlayer)) {
            return;
        }

        if (isGettingOutOfPenaltyBox(roll)) {
            printer.print(format(OUT_OF_PENALTY_BOX_MESSAGE, players.getName(currentPlayer)));

            players.setOutOfPenaltyBox(currentPlayer);

            return;
        }

        printer.print(format(IN_PENALTY_BOX_MESSAGE, players.getName(currentPlayer)));
    }

    private boolean isGettingOutOfPenaltyBox(int roll) {
        return roll % 2 != 0;
    }

    private void advancePlayer(int roll) {
        players.advance(currentPlayer, roll);

        printer.print(players.getName(currentPlayer)
                + "'s new location is "
                + players.getPosition(currentPlayer));

        printer.print("The category is " + currentTheme().getDescription());
    }

    private void askQuestion() {
        printer.print(questions.getBy(currentTheme()));
    }

    private Theme currentTheme() {
        int position = players.getPosition(currentPlayer);
        if (position % 4 == 0) return POP;
        if (position % 4 == 1) return SCIENCE;
        if (position % 4 == 2) return SPORTS;
        return ROCK;
    }

    private void assignNextPlayer() {
        currentPlayer = (currentPlayer+1)%players.getNumberOfPlayers();
    }
}
