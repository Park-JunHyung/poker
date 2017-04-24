package com.edu.cnu.poker.DataObject;

import lombok.Data;

/**
 * Created by Schwa on 2017-04-24.
 */
@Data
public class Player {
    private int money;
    private Hand hand;

    public Player(int givenMoney,Hand playerHand){
        this.money=givenMoney;
        this.hand=playerHand;
    }

    public int betting(int i) {
        //Game에 판돈+i

        return this.money-i;
    }

    public int die() {
        return money;
    }
}
