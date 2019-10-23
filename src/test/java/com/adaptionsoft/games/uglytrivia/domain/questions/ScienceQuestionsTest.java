package com.adaptionsoft.games.uglytrivia.domain.questions;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ScienceQuestionsTest {

    public static final int NUMBER_OF_QUESTIONS = 50;
    private ScienceQuestions questions;

    @Before
    public void setUp() {
        questions = new ScienceQuestions();
    }

    @Test
    public void generatesPopQuestions() {
        questions.generate(NUMBER_OF_QUESTIONS);

        assertThat(questions.list().size(), is(NUMBER_OF_QUESTIONS));
        assertThat(questions.list().get(0), is("Science Question 0"));
    }
}