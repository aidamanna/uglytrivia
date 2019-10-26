package com.adaptionsoft.games.uglytrivia.domain.questions;

import java.util.ArrayList;
import java.util.List;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.SCIENCE;

public class ScienceQuestions implements ThemeQuestions {

    private List<String> questions;
    private Theme theme;

    public ScienceQuestions() {
        questions = new ArrayList<String>();
        theme = SCIENCE;
    }

    public List<String> list() {
        return questions;
    }

    public Theme getTheme() {
        return theme;
    }

    public void generate(int numberOfQuestions) {
        for (int i = 0; i < numberOfQuestions; i++) {
            questions.add(theme.getDescription() + " Question " + i);
        }
    }
}
