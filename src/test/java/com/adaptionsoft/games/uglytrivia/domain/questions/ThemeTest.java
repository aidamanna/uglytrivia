package com.adaptionsoft.games.uglytrivia.domain.questions;

import org.junit.Test;

import static com.adaptionsoft.games.uglytrivia.domain.questions.Theme.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ThemeTest {

    @Test
    public void returnsPopThemeWhenPositionForPop() {
        assertThat(Theme.getByPosition(0), is(POP));
    }

    @Test
    public void returnsScienceThemeWhenPositionForScience() {
        assertThat(Theme.getByPosition(1), is(SCIENCE));
    }

    @Test
    public void returnsSportsThemeWhenPositionForSports() {
        assertThat(Theme.getByPosition(2), is(SPORTS));
    }

    @Test
    public void returnsRockThemeWhenPositionForRock() {
        assertThat(Theme.getByPosition(3), is(ROCK));
    }
}