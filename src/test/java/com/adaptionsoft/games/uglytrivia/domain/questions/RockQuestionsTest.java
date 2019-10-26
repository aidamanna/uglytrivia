package com.adaptionsoft.games.uglytrivia.domain.questions;

import org.junit.Before;
import org.junit.Test;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.ROCK;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RockQuestionsTest {

    public static final int NUMBER_OF_QUESTIONS = 50;
    private RockQuestions questions;

    @Before
    public void setUp() {
        questions = new RockQuestions();
    }

    @Test
    public void generatesPopQuestions() {
        questions.generate(NUMBER_OF_QUESTIONS);

        assertThat(questions.list().size(), is(NUMBER_OF_QUESTIONS));
        assertThat(questions.list().get(0), is("Rock Question 0"));
        assertThat(questions.getTheme(), is(ROCK));
    }
}
