package com.adaptionsoft.games.uglytrivia.domain;

import com.adaptionsoft.games.uglytrivia.domain.players.Players;

public interface Printer {

    void printPlayers(Players players);

    void printRoll(String name, int roll);

    void printCorrectAnswer(String name, int purses);

    void printIncorrectAnswer(String name);

    void printOutOfPenaltyBox(String name);

    void printInPenaltyBox(String name);

    void printQuestion(String theme, String question);

    void printAdvancePlayer(String name, int position);
}
