package com.adaptionsoft.games.uglytrivia.domain.players;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayersTest {

    private static final List<String> playerNames = Arrays.asList("Chet", "Pat");
    public static final int PLAYER = 0;
    public static final int ROLL = 3;

    @Test
    public void shouldCreatePlayers() {
        Players players = Players.create(playerNames);

        assertThat(players.get().size(), is(2));
    }

    @Test
    public void shouldReturnPlayerName() {
        Players players = Players.create(playerNames);

        assertThat(players.getName(PLAYER), is("Chet"));
    }

    @Test
    public void shouldReturnPlayerPosition() {
        Players players = Players.create(playerNames);

        assertThat(players.getPosition(PLAYER), is(0));
    }

    @Test
    public void shouldReturnPlayerPurses() {
        Players players = Players.create(playerNames);

        assertThat(players.getPurses(PLAYER), is(0));
    }

    @Test
    public void shouldReturnIfPlayerInPenaltyBox() {
        Players players = Players.create(playerNames);

        assertThat(players.isInPenaltyBox(PLAYER), is(false));
    }

    @Test
    public void shouldAdvancePosition() {
        Players players = Players.create(playerNames);
        players.advance(PLAYER, ROLL);

        assertThat(players.getPosition(PLAYER), is(ROLL));
    }

    @Test
    public void shouldReturnNumberOfPlayers() {
        Players players = Players.create(playerNames);

        assertThat(players.getNumberOfPlayers(), is(2));
    }

    @Test
    public void shouldIncreasePurses() {
        Players players = Players.create(playerNames);
        players.increasePurse(PLAYER);

        assertThat(players.getPurses(PLAYER), is(1));
    }

    @Test
    public void shouldReturnIfPlayerWon() {
        Players players = Players.create(playerNames);

        assertThat(players.didPlayerWin(PLAYER), is(true));
    }

    @Test
    public void shouldSetPlayerInPenaltyBox() {
        Players players = Players.create(playerNames);
        players.setInPenaltyBox(PLAYER);

        assertThat(players.isInPenaltyBox(PLAYER), is(true));
    }
}