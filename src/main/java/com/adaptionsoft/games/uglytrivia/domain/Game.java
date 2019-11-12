package com.adaptionsoft.games.uglytrivia.domain;

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

    public boolean isFinished() {
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
        Theme currentTheme = Theme.getByPosition(currentPlayer().getPosition());

        printer.printQuestion(currentTheme.getDescription(), questions.getBy(currentTheme));
    }

    private Player currentPlayer() {
        return players.getCurrentPlayer();
    }
}
