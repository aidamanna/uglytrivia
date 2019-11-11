package com.adaptionsoft.games.uglytrivia.infrastructure;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.adaptionsoft.games.uglytrivia.domain.players.Players;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class ConsoleTest {

    private ByteArrayOutputStream output;
    private Console console;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        console = new Console();
    }

    @Test
    public void printsPlayersAddedMessage() {
        Players players = Players.create(Arrays.asList("Chet", "Pat"));
        console.printPlayers(players);

        String expectedOutput = "Chet was added\n"
            + "They are player number 1\n"
            + "Pat was added\n"
            + "They are player number 2\n";

        assertThat(output.toString(), is(expectedOutput));
    }

    @Test
    public void printsRollMessage() {
        console.printRoll("Chet", 1);

        String expectedOutput = "Chet is the current player\n"
            + "They have rolled a 1\n";

        assertThat(output.toString(), is(expectedOutput));
    }

    @Test
    public void printsCorrectAnswerMessage() {
        console.printCorrectAnswer("Chet", 1);

        String expectedOutput = "Answer was correct!!!!\n"
            + "Chet now has 1 Gold Coins.\n";

        assertThat(output.toString(), is(expectedOutput));
    }

    @Test
    public void printsIncorrectAnswerMessage() {
        console.printIncorrectAnswer("Chet");

        String expectedOutput = "Question was incorrectly answered\n"
            + "Chet was sent to the penalty box\n";

        assertThat(output.toString(), is(expectedOutput));
    }

    @Test
    public void printsOutOfPenaltyBoxMessage() {
        console.printOutOfPenaltyBox("Chet");

        String expectedOutput = "Chet is getting out of the penalty box\n";

        assertThat(output.toString(), is(expectedOutput));
    }

    @Test
    public void printsInPenaltyBoxMessage() {
        console.printInPenaltyBox("Chet");

        String expectedOutput = "Chet is not getting out of the penalty box\n";

        assertThat(output.toString(), is(expectedOutput));
    }

    @Test
    public void printsQuestionMessage() {
        console.printQuestion("Science", "Science Question 0");

        String expectedOutput = "The category is Science\n"
            + "Science Question 0\n";

        assertThat(output.toString(), is(expectedOutput));
    }

    @Test
    public void printsAdvancePlayerMessage() {
        console.printAdvancePlayer("Pat", 5);

        String expectedOutput = "Pat's new location is 5\n";

        assertThat(output.toString(), is(expectedOutput));
    }
}
