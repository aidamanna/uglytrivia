package com.adaptionsoft.games.uglytrivia.domain.questions;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SportsQuestionsTest {

    public static final int NUMBER_OF_QUESTIONS = 50;
    private SportsQuestions questions;

    @Before
    public void setUp() {
        questions = new SportsQuestions();
    }

    @Test
    public void generatesPopQuestions() {
        questions.generate(NUMBER_OF_QUESTIONS);

        assertThat(questions.list().size(), is(NUMBER_OF_QUESTIONS));
        assertThat(questions.list().get(0), is("Sports Question 0"));
    }
}
