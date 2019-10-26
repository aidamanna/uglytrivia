package com.adaptionsoft.games.uglytrivia.domain.questions;

import java.util.ArrayList;
import java.util.List;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.SPORTS;

public class SportsQuestions implements ThemeQuestions {

    private List<String> questions;
    private Theme theme;

    public SportsQuestions() {
        questions = new ArrayList<String>();
        theme = SPORTS;
    }

    public List<String> list() {
        return questions;
    }

    public Theme getTheme() {
        return theme;
    }

    public void generate(int numberOfQuestions) {
        for (int i = 0; i < numberOfQuestions; i++) {
            questions.add("Sports Question " + i);
        }
    }
}
