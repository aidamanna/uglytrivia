package com.adaptionsoft.games.uglytrivia.domain.questions;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.POP;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GameQuestionsTest {

    private static final int NUMBER_OF_QUESTIONS = 2;

    public GameQuestions questions;

    @Before
    public void setUp() {
        PopQuestions pop = new PopQuestions();
        pop.generate(NUMBER_OF_QUESTIONS);

        Map<Theme, ThemeQuestions> themeQuestionsMap = new HashMap<Theme, ThemeQuestions>();
        themeQuestionsMap.put(pop.getTheme(), pop);

        questions = new GameQuestions(themeQuestionsMap);
    }

    @Test
    public void shouldReturnFistQuestionForTheSpecifiedTheme() {
        String firstQuestion = questions.getBy(POP);
        String secondQuestion = questions.getBy(POP);

        assertThat(firstQuestion, is("Pop Question 0"));
        assertThat(secondQuestion, is("Pop Question 1"));
    }
}