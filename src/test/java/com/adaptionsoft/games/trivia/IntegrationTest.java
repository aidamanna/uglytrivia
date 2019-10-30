package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.domain.Game;
import com.adaptionsoft.games.uglytrivia.domain.questions.GameQuestions;
import com.adaptionsoft.games.uglytrivia.domain.Player;
import com.adaptionsoft.games.uglytrivia.infrastructure.Console;
import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IntegrationTest {

    private static final int QUESTIONS_PER_THEME = 50;
    //private static final List<String> players = Arrays.asList("Chet", "Pat", "Sue");
    private List<Player> players;

    private ByteArrayOutputStream triviaOutput;
    private static Random randomNumber;
    private static boolean notAWinner;
    private Game game;

    @Before
    public void setUp() {
        setOutputStreamToString();

        GameQuestions questions = GameQuestions.create(QUESTIONS_PER_THEME);

        players = Arrays.asList(
                new Player("Chet"),
                new Player("Pat"),
                new Player("Sue")
        );


        game = new Game(new Console(), questions, players);
        randomNumber = new Random(100);
    }

    @Test
    public void playUglyTriviaGame() {
        game.add(players);

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
