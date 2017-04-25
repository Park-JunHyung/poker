package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.Deck;
import com.edu.cnu.poker.DataObject.Hand;
import com.edu.cnu.poker.DataObject.Player;
import com.edu.cnu.poker.DataObject.PokerType;
import lombok.Data;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Schwa on 2017-04-24.
 */
@Data
public class Game {
    private Player player;
    private Player computer;
    private Evaluator evaluator;
    private int SumOfMoney=0;
    public Game(){
        Deck deck=new Deck(1);
        this.player=new Player(10000,new Hand(deck, PokerType.SEVEN));
        this.computer=new Player(10000,new Hand(deck,PokerType.SEVEN));
    }
    public void enterNewGame(int startMoney) {
        System.out.println("New Game Start");
        SumOfMoney+=player.betting(startMoney);
        SumOfMoney+=computer.betting(startMoney);

    }

    public void turn() {
        boolean evaluate=true;//오픈된 카드 순위__구현안됨
        int turn=(evaluate)?1:0;//테이블에 오픈된 카드에 따른 우선순위 설정 1(유저) 0(컴퓨터)
        Scanner bet=new Scanner(System.in);

        while (true){

            if(turn==1){//유저가 선
                int firstBet=bet.nextInt();
                player.betting(firstBet);
                //AI미구현
                computer.betting(firstBet);
            }else{//컴퓨터가 선
                int firstBet= (int) Math.random()*1000; //컴퓨터 베팅 임시 설정
                computer.betting(firstBet);
                int nextBet=bet.nextInt();

                while (nextBet<firstBet){
                    System.out.println(firstBet+"이상 베팅을 해야 합니다.");
                    nextBet=bet.nextInt();
                }
                player.betting(nextBet);

                if (nextBet>firstBet){
                    computer.betting(nextBet-firstBet);// 컴퓨터 추가 베팅 -- 콜
                    //컴퓨터 추가 베팅 -- 레이즈 추가구현 예정
                }
            }
        }
    }
}
