package com.adaptionsoft.games.uglytrivia.domain.questions;

import org.junit.Before;
import org.junit.Test;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.POP;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PopQuestionsTest {

    public static final int NUMBER_OF_QUESTIONS = 50;
    private PopQuestions questions;

    @Before
    public void setUp() {
        questions = new PopQuestions();
    }

    @Test
    public void generatesPopQuestions() {
        questions.generate(NUMBER_OF_QUESTIONS);

        assertThat(questions.list().size(), is(NUMBER_OF_QUESTIONS));
        assertThat(questions.list().get(0), is("Pop Question 0"));
        assertThat(questions.getTheme(), is(POP));
    }
}