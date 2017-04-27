package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.PokerType;

/**
 * Created by cse on 2017-04-17.
 * CARD - rank, suit
 * DECK
 * HAND
 * EVALUATOR
 * PLAYER
 * GAME
 */
public class PokerApplication {

    public static void main(String[] args) {
        //testApplication
        Game game=new Game(10000, PokerType.SEVEN);
        game.sevenPokerGame(100);
    }
}
