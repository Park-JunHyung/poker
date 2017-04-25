package com.edu.cnu.poker.DataObject;

/**
 * Created by cse on 2017-04-17.
 */
public enum PokerType {
    FIVE(5),
    SEVEN(7);
    private int numberOfCard;

    PokerType(int numberOfCard) {
        this.numberOfCard = numberOfCard;
    }
    public int getNumberOfCard() {
        return this.numberOfCard;
    }
}
