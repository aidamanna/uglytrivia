package com.adaptionsoft.games.uglytrivia.domain.players;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player("Maria");
    }

    @Test
    public void advancesPlayerRollPositions() {
        player.advance(4);

        assertThat(player.getPosition(), is(4));
    }

    @Test
    public void restartsPositionIfPositionHigherThanMaximum() {
        player.advance(15);

        assertThat(player.getPosition(), is(3));
    }

    @Test
    public void increasesPurseByOne() {
        player.increasePurse();

        assertThat(player.getPurses(), is(1));
    }

    @Test
    public void setsInPenaltyBox() {
        player.setInPenaltyBox();

        assertTrue(player.isInPenaltyBox());
    }

    @Test
    public void setsOutOfPenaltyBox() {
        player.setOutOfPenaltyBox();

        assertFalse(player.isInPenaltyBox());
    }

    @Test
    public void returnsFalseIfPlayerPursesAreNotSix() {
        assertFalse(player.didPlayerWin());
    }

    @Test
    public void returnsTrueIfPlayerPursesAreSix() {
        for(int i = 0; i < 6; i++) player.increasePurse();

        assertTrue(player.didPlayerWin());
    }
}
