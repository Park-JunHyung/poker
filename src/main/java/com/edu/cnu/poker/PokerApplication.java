package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.PokerType;

import java.util.Scanner;

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
        int round=1;
        Scanner regame=new Scanner(System.in);
        Game game=new Game(10000, PokerType.SEVEN);
        while (game.getPlayer().getMoney()>0&&game.getComputer().getMoney()>0){
            System.out.println("계속하시겠습니까 (1)네 (2)아니오");
            if (regame.nextInt()==1){
                System.out.println("Round "+round++);
                game.runSevenPokerGame(100);
            }else
                System.out.println("종료");


        }

    }
}
