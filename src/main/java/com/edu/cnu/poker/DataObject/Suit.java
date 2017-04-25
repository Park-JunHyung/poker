package com.edu.cnu.poker.DataObject;

/**
 * Created by cse on 2017-04-17.
 */
public enum Suit {
    SPADES(4),
    DIAMONDS(3),
    HEARTS(2),
    CLUBS(1);

    private int rankOfSuit;

    Suit(int rankOfHand) {
        this.rankOfSuit = rankOfHand;
    }
    public int getRankOfSuit() {
        return this.rankOfSuit;
    }
}
