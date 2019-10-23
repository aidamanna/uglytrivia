package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.domain.Game;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.infrastructure.Console;
import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {

    private ByteArrayOutputStream triviaOutput;
    private static List<String> players = Arrays.asList("Chet", "Pat", "Sue");
    private static Random randomNumber;
    private static boolean notAWinner;

    private Game game;

    @Before
    public void setUp() {
        triviaOutput = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(triviaOutput);
        System.setOut(printStream);

        game = new Game(new Console());
        randomNumber = new Random(100);
    }

    @Test
    public void playUglyTriviaGame() {
        addPlayersTo(game);

        do {
            game.roll(dice());
            notAWinner = answerQuestion(game);
        } while (notAWinner);

        Approvals.verify(triviaOutput.toString());
    }

    private void addPlayersTo(Game game) {
        for (String player : players) {
            game.add(player);
        }
    }

    private int dice() {
        return randomNumber.nextInt(5) + 1;
    }

    private Boolean answerQuestion(Game game) {
        if (randomNumber.nextInt(9) == 7) {
            return game.wrongAnswer();
        }

        return game.wasCorrectlyAnswered();
    }
}
