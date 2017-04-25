package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.*;
import lombok.Data;

import java.util.List;
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
    public Game(int money,PokerType pokerType){
        Deck deck=new Deck(1);
        this.player=new Player(money,new Hand(deck, pokerType));
        this.computer=new Player(money,new Hand(deck,pokerType));
    }
    public void enterNewGame(int startMoney,int PokerType) {
        System.out.println("New Game Start");
        SumOfMoney+=player.betting(startMoney);
        SumOfMoney+=computer.betting(startMoney);
        System.out.println("[ 현재 판돈 : "+SumOfMoney+"만원 ] [ 유저 소지금 : "+player.getMoney()+"만원] [ 컴퓨터 소지금 : "+computer.getMoney()+"만원 ]");
        player.getHand().CardAddtion(PokerType);
        computer.getHand().CardAddtion(PokerType);
        player.getHand().MyCard();
        computer.getHand().OpponentCard();
    }

    public void betting(int turn) {

        Scanner bet = new Scanner(System.in);
        player.getHand().MyCard();
        computer.getHand().OpponentCard();
        System.out.println("현재 판돈 : "+SumOfMoney);
        if (turn == 1) {//유저가 선
            System.out.println("베팅하세요.");
            int firstBet = bet.nextInt();
            SumOfMoney += player.betting(firstBet);
            //AI미구현
            SumOfMoney += computer.betting(firstBet);
        } else {//컴퓨터가 선
            int firstBet = (int) Math.random() * 1000; //컴퓨터 베팅 임시 설정
            System.out.println("컴퓨터가 "+firstBet+"만큼 베팅했습니다.");
            SumOfMoney += computer.betting(firstBet);
            int nextBet = bet.nextInt();

            while (nextBet < firstBet) {
                System.out.println(firstBet + "이상 베팅을 해야 합니다.");
                nextBet = bet.nextInt();
            }
            SumOfMoney += player.betting(nextBet);

            if (nextBet > firstBet) {
                SumOfMoney += computer.betting(nextBet - firstBet);// 컴퓨터 추가 베팅 -- 콜
                System.out.println("컴퓨터가 콜을 했습니다.");
                //컴퓨터 추가 베팅 -- 레이즈 추가구현 예정
            }
        }
        System.out.println("현재 판돈 : "+SumOfMoney);
    }
    public void SevenPokerGame(int startMoney){
        enterNewGame(startMoney,3);
        //betting();
    }

    public int evaluating(List<Card> playerList, List<Card> computerList) {
        int playerRank=evaluator.evaluate(playerList).getRankOfHand();
        int computerRank=evaluator.evaluate(computerList).getRankOfHand();
        if (playerRank>computerRank){
            return 0;
        }else if (playerRank==computerRank){
            return 1;
        }else
            return 1;
    }
}
