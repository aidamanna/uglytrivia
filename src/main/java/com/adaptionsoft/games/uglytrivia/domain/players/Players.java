package com.adaptionsoft.games.uglytrivia.domain.players;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private static List<Player> players;
    private Player currentPlayer;

    public Players(List<Player> list) {
        this.players = list;
        this.currentPlayer = players.get(0);
    }

    public static Players create(List<String> playerNames) {
        List<Player> playersList = new ArrayList<Player>();

        for(String playerName : playerNames) {
            Player player = new Player(playerName);
            playersList.add(player);
        }

        return new Players(playersList);
    }

    public List<Player> get() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setNextPlayer() {
        int nextPlayerIndex = (players.indexOf(currentPlayer) + 1) % players.size();
        currentPlayer = players.get(nextPlayerIndex);
    }
}
