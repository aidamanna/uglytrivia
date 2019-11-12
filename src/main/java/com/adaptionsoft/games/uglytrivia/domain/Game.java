package com.adaptionsoft.games.uglytrivia.domain;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.POP;
import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.ROCK;
import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.SCIENCE;
import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.SPORTS;

import com.adaptionsoft.games.uglytrivia.domain.players.Player;
import com.adaptionsoft.games.uglytrivia.domain.players.Players;
import com.adaptionsoft.games.uglytrivia.domain.questions.GameQuestions;
import com.adaptionsoft.games.uglytrivia.domain.questions.Theme;

public class Game {

    private Printer printer;
    private GameQuestions questions;
    private Players players;

    private boolean gameFinished;

    public Game(Printer printer, GameQuestions questions, Players players) {
        this.printer = printer;
        this.questions = questions;
        this.players = players;
        this.gameFinished = false;
    }

    public void start() {
            printer.printPlayers(players);
    }

    public void roll(int roll) {
        printer.printRoll(currentPlayer().getName(), roll);

        updatePlayerPenaltyBoxStatus(roll);

        if (!currentPlayer().isInPenaltyBox()) {
            advancePlayer(roll);
            askQuestion();
        }

    }

    public void correctAnswer() {
        if (currentPlayer().isInPenaltyBox()) {
            players.setNextPlayer();
            return;
        }

        currentPlayer().increasePurse();

        printer.printCorrectAnswer(currentPlayer().getName(), currentPlayer().getPurses());

        gameFinished = currentPlayer().didPlayerWin();

        players.setNextPlayer();
    }

    public void wrongAnswer() {
        currentPlayer().setInPenaltyBox();

        printer.printIncorrectAnswer(currentPlayer().getName());

        players.setNextPlayer();
    }

    public boolean isNotFinished() {
        return gameFinished;
    }

    private void updatePlayerPenaltyBoxStatus(int roll) {
        if (!currentPlayer().isInPenaltyBox()) {
            return;
        }

        if (isGettingOutOfPenaltyBox(roll)) {
            printer.printOutOfPenaltyBox(currentPlayer().getName());

            currentPlayer().setOutOfPenaltyBox();

            return;
        }

        printer.printInPenaltyBox(currentPlayer().getName());
    }

    private boolean isGettingOutOfPenaltyBox(int roll) {
        return roll % 2 != 0;
    }

    private void advancePlayer(int roll) {
        currentPlayer().advance(roll);

        printer.printAdvancePlayer(currentPlayer().getName(), currentPlayer().getPosition());
    }

    private void askQuestion() {
        printer.printQuestion(currentTheme().getDescription(), questions.getBy(currentTheme()));
    }

    private Theme currentTheme() {
        int position = currentPlayer().getPosition();
        if (position % 4 == 0) return POP;
        if (position % 4 == 1) return SCIENCE;
        if (position % 4 == 2) return SPORTS;
        return ROCK;
    }

    private Player currentPlayer() {
        return players.getCurrentPlayer();
    }
}
