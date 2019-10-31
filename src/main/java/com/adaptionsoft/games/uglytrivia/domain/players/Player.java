package com.adaptionsoft.games.uglytrivia.domain.players;

public class Player {

    public static final int LAST_POSITION = 11;
    public static final int NUMBER_OF_POSITIONS = 12;
    public static final int INCORRECT_PURSES = 6;
    private String name;
    private int position;
    private int purses;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        position = 0;
        purses = 0;
        inPenaltyBox = false;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getPurses() {
        return purses;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void advance(int roll) {
        position += roll;

        if(isPositionHigherThanLast()) restartPosition();
    }

    public void increasePurse() {
        purses++;
    }

    public void setInPenaltyBox() {
        inPenaltyBox = true;
    }

    public boolean didPlayerWin() {
        return purses != INCORRECT_PURSES;
    }

    private boolean isPositionHigherThanLast() {
        return position > LAST_POSITION;
    }

    private void restartPosition() {
        position -= NUMBER_OF_POSITIONS;
    }
}
