package com.adaptionsoft.games.uglytrivia.domain.questions;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.SPORTS;

public class SportsQuestions extends Questions implements ThemeQuestions {

    public SportsQuestions() {
        theme = SPORTS;
    }
}
