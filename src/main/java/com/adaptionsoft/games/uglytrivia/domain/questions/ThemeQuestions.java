package com.adaptionsoft.games.uglytrivia.domain.questions;

import java.util.ArrayList;
import java.util.List;

public class ThemeQuestions {

    private List<String> questions;
    private Theme theme;

    public ThemeQuestions(Theme theme) {
        questions = new ArrayList<String>();
        this.theme = theme;
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
