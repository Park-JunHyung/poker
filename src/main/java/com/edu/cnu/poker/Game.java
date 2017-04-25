package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.Player;

/**
 * Created by Schwa on 2017-04-24.
 */
public class Game {
    private Player player1;
    private Player player2;
    private int SumOfMoney=0;

    public void enterNewGame(int i) {
        System.out.println("New Game Start");
        SumOfMoney+=player1.betting(i);
        SumOfMoney+=player2.betting(i);

    }

    public void turn() {

    }
}
