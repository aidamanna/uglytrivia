package com.adaptionsoft.games.uglytrivia.domain.questions;

import java.util.Map;

public class GameQuestions {

    private Map<Theme, ThemeQuestions> questions;

    public GameQuestions(Map<Theme, ThemeQuestions> questions) {
        this.questions = questions;
    }

    public String getBy(Theme theme) {
        return questions.get(theme).list().remove(0);
    }
}
