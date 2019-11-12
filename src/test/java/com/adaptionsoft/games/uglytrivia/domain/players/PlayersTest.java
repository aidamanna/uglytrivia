package com.adaptionsoft.games.uglytrivia.domain.players;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayersTest {

    private static final List<String> playerNames = Arrays.asList("Chet", "Pat");

    @Test
    public void createsPlayers() {
        Players players = Players.create(playerNames);

        assertThat(players.get().size(), is(2));
    }

    @Test
    public void setsNextPlayerAsCurrentPlayer() {
        Players players = Players.create(playerNames);

        players.setNextPlayer();

        assertThat(players.getCurrentPlayer(), is(players.get().get(1)));
    }

    @Test
    public void setsFistPlayerAsCurrentPlayerIfPlayingWithLastPlayer() {
        Players players = Players.create(playerNames);

        for (int i = 0; i < playerNames.size(); i++) {
            players.setNextPlayer();
        }

        assertThat(players.getCurrentPlayer(), is(players.get().get(0)));
    }
}
