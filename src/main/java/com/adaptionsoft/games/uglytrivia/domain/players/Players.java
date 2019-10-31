package com.adaptionsoft.games.uglytrivia.domain.players;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private static List<Player> players;

    public Players(List<Player> list) {
        this.players = list;
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

    public String getName(int player) {
        return players.get(player).getName();
    }

    public int getPosition(int player) {
        return players.get(player).getPosition();
    }

    public int getPurses(int player) {
        return players.get(player).getPurses();
    }

    public boolean isInPenaltyBox(int player) {
        return players.get(player).isInPenaltyBox();
    }

    public Integer getNumberOfPlayers() {
        return players.size();
    }

    public void advance(int player, int roll) {
        players.get(player).advance(roll);
    }

    public void increasePurse(int player) {
        players.get(player).increasePurse();
    }

    public boolean didPlayerWin(int player) {
        return players.get(player).didPlayerWin();
    }

    public void setInPenaltyBox(int player) {
        players.get(player).setInPenaltyBox();
    }
}
