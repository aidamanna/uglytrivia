package com.adaptionsoft.games.uglytrivia.domain.questions;

import java.util.ArrayList;
import java.util.List;

public class PopQuestions implements ThemeQuestions {

    private List<String> questions;

    public PopQuestions() {
        questions = new ArrayList<String>();
    }

    public List<String> list() {
        return questions;
    }

    public void generate(int numberOfQuestions) {
        for (int i = 0; i < numberOfQuestions; i++) {
            questions.add("Pop Question " + i);
        }
    }
}
