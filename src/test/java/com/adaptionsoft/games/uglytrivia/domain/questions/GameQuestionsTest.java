package com.adaptionsoft.games.uglytrivia.domain.questions;

import org.junit.Before;
import org.junit.Test;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.POP;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameQuestionsTest {

    private static final int QUESTIONS_PER_THEME = 2;

    private GameQuestions gameQuestions;

    @Before
    public void setUp() {
        gameQuestions = GameQuestions.create(QUESTIONS_PER_THEME);
    }

    @Test
    public void returnsFistQuestionForTheSpecifiedTheme() {
        String firstQuestion = gameQuestions.getBy(POP);
        String secondQuestion = gameQuestions.getBy(POP);

        assertThat(gameQuestions.get().size(), is(4));
        assertThat(firstQuestion, is("Pop Question 0"));
        assertThat(secondQuestion, is("Pop Question 1"));
    }
}