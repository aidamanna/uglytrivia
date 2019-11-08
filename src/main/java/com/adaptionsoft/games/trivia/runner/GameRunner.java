package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.domain.Game;
import com.adaptionsoft.games.uglytrivia.domain.players.Players;
import com.adaptionsoft.games.uglytrivia.domain.questions.GameQuestions;
import com.adaptionsoft.games.uglytrivia.infrastructure.Console;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class GameRunner {

    private static final int QUESTIONS_PER_THEME = 50;
    private static final List<String> playerNames = Arrays.asList("Chet", "Pat", "Sue");

    public static void main(String[] args) {
        GameQuestions questions = GameQuestions.create(QUESTIONS_PER_THEME);
        Players players = Players.create(playerNames);

        Game game = new Game(new Console(), questions, players);

        Random randomNumber = new Random();

        game.start();

        do {
            game.roll(dice(randomNumber));
            answerQuestion(game, randomNumber);
        } while (game.isNotFinished());

    }

    private static int dice(Random randomNumber) {
        return randomNumber.nextInt(5) + 1;
    }

    private static void answerQuestion(Game game, Random randomNumber) {
        boolean isWrongAnswer = randomNumber.nextInt(9) == 7;

        if (isWrongAnswer) {
            game.wrongAnswer();
            return;
        }

        game.correctAnswer();
    }
}
