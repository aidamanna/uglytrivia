package com.adaptionsoft.games.uglytrivia.domain.questions;

public enum Theme {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private String description;

    Theme(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Theme getByPosition(int position) {
        if (position % 4 == 0) return POP;
        if (position % 4 == 1) return SCIENCE;
        if (position % 4 == 2) return SPORTS;
        return ROCK;
    }
}
