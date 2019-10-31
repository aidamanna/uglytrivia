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
    public void shouldAdvancePlayerRollPositions() {
        player.advance(4);

        assertThat(player.getPosition(), is(4));
    }

    @Test
    public void shouldRestartPositionIfPositionHigherThanMaximum() {
        player.advance(15);

        assertThat(player.getPosition(), is(3));
    }

    @Test
    public void shouldIncreasePurseByOne() {
        player.increasePurse();

        assertThat(player.getPurses(), is(1));
    }

    @Test
    public void shouldSetInPenaltyBox() {
        player.setInPenaltyBox();

        assertTrue(player.isInPenaltyBox());
    }

    @Test
    public void shouldReturnTrueIfPlayerWon() {
        assertTrue(player.didPlayerWin());
    }

    @Test
    public void shouldReturnFalseIfPlayerDidNotWin() {
        for(int i = 0; i < 6; i++) player.increasePurse();

        assertFalse(player.didPlayerWin());
    }
}