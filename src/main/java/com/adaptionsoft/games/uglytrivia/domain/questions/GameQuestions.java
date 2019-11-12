package com.adaptionsoft.games.uglytrivia.domain.questions;

import java.util.HashMap;
import java.util.Map;

public class GameQuestions {

    private Map<Theme, ThemeQuestions> questions;

    private GameQuestions(Map<Theme, ThemeQuestions> questions) {
        this.questions = questions;
    }

    public static GameQuestions create(int questionsPerTheme) {
        Map<Theme, ThemeQuestions> themeQuestionsMap = new HashMap<Theme, ThemeQuestions>();

        for (Theme theme: Theme.values()) {
            ThemeQuestions questions = new ThemeQuestions(theme);
            questions.generate(questionsPerTheme);
            themeQuestionsMap.put(questions.getTheme(), questions);
        }

        return new GameQuestions(themeQuestionsMap);
    }

    protected Map<Theme, ThemeQuestions> get() {
        return questions;
    }

    public String getBy(Theme theme) {
        ThemeQuestions themeQuestions = questions.get(theme);

        return themeQuestions.getQuestion();
    }
}
