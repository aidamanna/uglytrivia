package com.adaptionsoft.games.uglytrivia.domain.questions;

import java.util.ArrayList;
import java.util.List;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.ROCK;

public class RockQuestions implements ThemeQuestions {

    private List<String> questions;
    private Theme theme;

    public RockQuestions() {
        questions = new ArrayList<String>();
        theme = ROCK;
    }

    public List<String> list() {
        return questions;
    }

    public Theme getTheme() {
        return theme;
    }

    public void generate(int numberOfQuestions) {
        for (int i = 0; i < numberOfQuestions; i++) {
            questions.add("Rock Question " + i);
        }
    }
}
