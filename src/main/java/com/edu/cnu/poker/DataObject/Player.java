package com.edu.cnu.poker.DataObject;

import lombok.Data;

import java.util.List;

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

    public int betting(int money) {
        //Game에 판돈+i
        this.money-=money;
        return money;
    }

    public void won(int prize){
        this.money+=prize;
    }

    public int die() {
        return money;
    }
}
