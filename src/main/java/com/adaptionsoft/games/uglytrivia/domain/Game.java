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
            printer.printPlayers(players);
    }

    public void roll(int roll) {
        printer.printRoll(players.getName(currentPlayer), roll);

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

        printer.printCorrectAnswer(players.getName(currentPlayer), players.getPurses(currentPlayer));

        gameFinished = players.didPlayerWin(currentPlayer);

        assignNextPlayer();
    }

    public void wrongAnswer() {
        players.setInPenaltyBox(currentPlayer);

        printer.printIncorrectAnswer(players.getName(currentPlayer));

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
            printer.printOutOfPenaltyBox(players.getName(currentPlayer));

            players.setOutOfPenaltyBox(currentPlayer);

            return;
        }

        printer.printInPenaltyBox(players.getName(currentPlayer));
    }

    private boolean isGettingOutOfPenaltyBox(int roll) {
        return roll % 2 != 0;
    }

    private void advancePlayer(int roll) {
        players.advance(currentPlayer, roll);

        printer.printAdvancePlayer(players.getName(currentPlayer), players.getPosition(currentPlayer));
    }

    private void askQuestion() {
        printer.printQuestion(currentTheme().getDescription(), questions.getBy(currentTheme()));
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
