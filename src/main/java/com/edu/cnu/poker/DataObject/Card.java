package com.edu.cnu.poker.DataObject;

import com.edu.cnu.poker.UserException.NoSuchRankException;
import lombok.Data;

/**
 * Created by cse on 2017-04-17.
 */
@Data
public class Card implements Comparable<Card>{
    private int rank;
    private Suit suit;
    private boolean used = false;

    public Card(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        if (rank > 13) {
            throw new NoSuchRankException();
        }
    }
    public Card(int rank, Suit suit, boolean used) {
        this(rank,suit);
        this.used = used;
    }

    public int compareTo(Card o) {
        return this.getRank() - o.getRank();
    }

    public boolean getUsed() {
        return used;
    }
}
