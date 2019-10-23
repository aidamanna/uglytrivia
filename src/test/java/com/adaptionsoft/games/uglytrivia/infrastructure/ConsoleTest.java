package com.adaptionsoft.games.uglytrivia.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
    public void printsMessage() {
        console.print("message");

        assertThat(output.toString(), is("message\n"));
    }

    @Test
    public void printsObject() {
        LinkedList list = new LinkedList();
        list.addLast("message");

        console.print(list.removeFirst());

        assertThat(output.toString(), is("message\n"));
    }
}
