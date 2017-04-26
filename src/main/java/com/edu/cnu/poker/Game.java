package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.*;
import lombok.Data;

import java.util.Collections;
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
        this.evaluator=new Evaluator();
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
        System.out.println("현재 판돈 : "+SumOfMoney+"만원");
        if (turn == 1) {//유저가 선
            System.out.println("베팅하세요.");
            int firstBet = bet.nextInt();
            SumOfMoney += player.betting(firstBet);
            System.out.println("당신은 "+firstBet+"만원 만큼 베팅했습니다.");
            //AI미구현
            SumOfMoney += computer.betting(firstBet);
            System.out.println("컴퓨터가 콜 했습니다.");
        } else {//컴퓨터가 선
            int firstBet = (int) Math.random() * 1000; //컴퓨터 베팅 임시 설정
            System.out.println("컴퓨터가 "+firstBet+"만원 만큼 베팅했습니다.");
            SumOfMoney += computer.betting(firstBet);
            int nextBet = bet.nextInt();

            while (nextBet < firstBet) {
                System.out.println(firstBet + "만원 이상 베팅을 해야 합니다.");
                nextBet = bet.nextInt();
            }
            SumOfMoney += player.betting(nextBet);

            if (nextBet > firstBet) {
                System.out.println("당신은 "+(nextBet-firstBet)+"만큼 레이즈 했습니다.");
                SumOfMoney += computer.betting(nextBet - firstBet);// 컴퓨터 추가 베팅 -- 콜
                System.out.println("컴퓨터가 콜 했습니다.");
                //컴퓨터 추가 베팅 -- 레이즈 추가구현 예정
            }else
                System.out.println("당신은 콜 했습니다.");
        }
        System.out.println("현재 판돈 : "+SumOfMoney+"만원");
    }
    /*
    public void SevenPokerGame(int startMoney){
        enterNewGame(startMoney,3);
        betting(evaluating(player.getHand().getDisplayedCard(),player.getHand().getDisplayedCard()));
        //오프닝 후 첫번째 베팅
        for (int i=0;i>7;i++){
            System.out.println("[ 현재 판돈 : "+SumOfMoney+"만원 ] [ 유저 소지금 : "+player.getMoney()+"만원] [ 컴퓨터 소지금 : "+computer.getMoney()+"만원 ]");
            player.getHand().CardAddtion(1);
            computer.getHand().CardAddtion(1);
            player.getHand().MyCard();
            computer.getHand().OpponentCard();
            betting(evaluating(player.getHand().getDisplayedCard(),player.getHand().getDisplayedCard()));
        }
    }*/

    public int evaluating(List<Card> playerList, List<Card> computerList) {
        //int playerRank=evaluator.evaluate(playerList).getRankOfHand();
        //int computerRank=evaluator.evaluate(computerList).getRankOfHand();
        //---------아직 Evaluator수정 안함
        int playerRank=0,computerRank=1;//temporary variable
        if (playerRank>computerRank){
            return 0;
        }else if (playerRank==computerRank){
            int moreValuableRank=0;//족보가 같을경우 숫자-모양을 비교후 리턴받은 값
            return moreValuableRank;
        }else
            return 1;
    }
}
