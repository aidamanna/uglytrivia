package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.domain.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.domain.questions.*;
import com.adaptionsoft.games.uglytrivia.infrastructure.Console;
import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {

    public static final int NUMBER_OF_QUESTIONS = 50;
    private static final List<String> players = Arrays.asList("Chet", "Pat", "Sue");

    private ByteArrayOutputStream triviaOutput;
    private static Random randomNumber;
    private static boolean notAWinner;
    private Game game;

    @Before
    public void setUp() {
        setOutputStreamToString();

        List<ThemeQuestions> questions = generateGameQuestions();

        game = new Game(new Console(), questions);
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

    private void setOutputStreamToString() {
        triviaOutput = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(triviaOutput);
        System.setOut(printStream);
    }

    private List<ThemeQuestions> generateGameQuestions() {
        PopQuestions pop = new PopQuestions();
        ScienceQuestions science = new ScienceQuestions();
        SportsQuestions sports = new SportsQuestions();
        RockQuestions rock = new RockQuestions();

        List<ThemeQuestions> gameQuestions = Arrays.asList(pop, science, sports, rock);

        for(ThemeQuestions themeQuestions : gameQuestions) {
            themeQuestions.generate(NUMBER_OF_QUESTIONS);
        }

        return gameQuestions;
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
