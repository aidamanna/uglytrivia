package com.adaptionsoft.games.uglytrivia.domain.questions;

import java.util.ArrayList;
import java.util.List;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.POP;

public class PopQuestions implements ThemeQuestions {

    private List<String> questions;
    private Theme theme;

    public PopQuestions() {
        questions = new ArrayList<String>();
        theme = POP;
    }

    public List<String> list() {
        return questions;
    }

    public Theme getTheme() {
        return theme;
    }

    public void generate(int numberOfQuestions) {
        for (int i = 0; i < numberOfQuestions; i++) {
            questions.add("Pop Question " + i);
        }
    }

}
