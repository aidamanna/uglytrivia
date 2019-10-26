package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.domain.Game;
import com.adaptionsoft.games.uglytrivia.domain.questions.*;
import com.adaptionsoft.games.uglytrivia.infrastructure.Console;
import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

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

        Map<Theme, ThemeQuestions> questions = generateGameQuestions();

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

    private  Map<Theme, ThemeQuestions> generateGameQuestions() {
        PopQuestions pop = new PopQuestions();
        pop.generate(NUMBER_OF_QUESTIONS);
        ScienceQuestions science = new ScienceQuestions();
        science.generate(NUMBER_OF_QUESTIONS);
        SportsQuestions sports = new SportsQuestions();
        sports.generate(NUMBER_OF_QUESTIONS);
        RockQuestions rock = new RockQuestions();
        rock.generate(NUMBER_OF_QUESTIONS);

        Map<Theme, ThemeQuestions> gameQuestions = new HashMap<Theme, ThemeQuestions>();
        gameQuestions.put(pop.getTheme(), pop);
        gameQuestions.put(science.getTheme(), science);
        gameQuestions.put(sports.getTheme(), sports);
        gameQuestions.put(rock.getTheme(), rock);

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
