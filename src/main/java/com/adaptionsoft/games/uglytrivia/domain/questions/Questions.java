package com.adaptionsoft.games.uglytrivia.domain.questions;

import java.util.ArrayList;
import java.util.List;

public abstract class Questions {

    private List<String> questions;
    protected Theme theme;

    public Questions() {
        questions = new ArrayList<String>();
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
