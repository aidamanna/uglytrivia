package com.adaptionsoft.games.uglytrivia.domain.questions;

import org.junit.Before;
import org.junit.Test;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.POP;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ThemeQuestionsTest {

    private static final int NUMBER_OF_QUESTIONS = 2;
    private static final Theme theme = POP;
    private ThemeQuestions questions;

    @Before
    public void setUp() {
        questions = new ThemeQuestions(theme);
    }

    @Test
    public void generatesPopQuestions() {
        questions.generate(NUMBER_OF_QUESTIONS);

        assertThat(questions.list().size(), is(NUMBER_OF_QUESTIONS));
        assertThat(questions.list().get(0), is("Pop Question 0"));
        assertThat(questions.list().get(1), is("Pop Question 1"));
        assertThat(questions.getTheme(), is(POP));
    }

    @Test
    public void getsNextQuestion() {
        questions.generate(NUMBER_OF_QUESTIONS);

        String question = questions.getQuestion();

        assertThat(question, is("Pop Question 0"));
    }

    @Test
    public void getsFistQuestionIfLastQuestionWasTheLastOne() {
        questions.generate(NUMBER_OF_QUESTIONS);

        questions.getQuestion();
        questions.getQuestion();
        String question = questions.getQuestion();

        assertThat(question, is("Pop Question 0"));
    }
}
