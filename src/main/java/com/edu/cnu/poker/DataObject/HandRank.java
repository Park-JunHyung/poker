package com.edu.cnu.poker.DataObject;

/**
 * Created by JSH on 2017-04-23.
 */
public enum HandRank {
    Top(1),                 //  숫자우선,모양차선
    OnePair(2),             //  숫자우선,모양차선
    TwoPair(3),
    Triple(4),
    Straight(5),
    Mountain(6),
    Flush(7),
    FullHouse(8),
    FourCard(9),
    StraightFlush(10),
    RoyalStraightFlush(11);

    private int rankOfHand;

    HandRank(int rankOfHand) {
        this.rankOfHand = rankOfHand;
    }
    public int getRankOfHand() {
        return this.rankOfHand;
    }
}
