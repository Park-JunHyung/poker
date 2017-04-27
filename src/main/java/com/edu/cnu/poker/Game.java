package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.*;
import lombok.Data;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Schwa on 2017-04-24.
 */
@Data
public class Game {
    private Player player;
    private Player computer;
    private Evaluator evaluator;
    private int SumOfMoney = 0;
    private static Printing isDie;

    public Game(int money, PokerType pokerType) {
        this.evaluator = new Evaluator();
        Deck deck = new Deck(1);
        this.player = new Player(money, new Hand(deck, pokerType));
        this.computer = new Player(money, new Hand(deck, pokerType));
    }

    public void enterNewGame(int startMoney, int PokerType) {
        System.out.println("New Game Start");
        SumOfMoney += player.betting(startMoney);
        SumOfMoney += computer.betting(startMoney);
        player.getHand().CardAddtion(PokerType);
        computer.getHand().CardAddtion(PokerType);
        printStatus(Printing.CARDS_IN_TABLE,0);
    }

    public void bettingTime(int turn) {

        Scanner bet = new Scanner(System.in);
        if (turn == 0) {//유저가 선
            System.out.println("베팅하세요.");
            int firstBet = bet.nextInt();
            if (firstBet==0){
                isDie=Printing.PLAYER_DIE;
            }else{
                SumOfMoney += player.betting(firstBet);
                printStatus(Printing.PLAYER_BET,firstBet);
                int computerBetting = computerBetting(turn, firstBet);
                SumOfMoney += computer.betting(computerBetting);
                if (computerBetting == firstBet) {
                    printStatus(Printing.COMPUTER_CALL, 0);
                }else if (computerBetting==0){
                    isDie=Printing.COMPUTER_DIE;
                }
                else {
                    printStatus(Printing.COMPUTER_RAISE, computerBetting - firstBet);
                }
            }
        } else {//컴퓨터가 선
            int firstBet = computerBetting(1, 0);
            if (firstBet==0){
                isDie=Printing.COMPUTER_DIE;
            } else {
                SumOfMoney += computer.betting(firstBet);
                printStatus(Printing.COMPUTER_BET,firstBet);
                int nextBet = bet.nextInt();
                if (nextBet == 0) {
                    isDie = Printing.PLAYER_DIE;
                } else {
                    while (nextBet < firstBet) {
                        System.out.println(firstBet + "만원 이상 베팅을 해야 합니다.");
                        nextBet = bet.nextInt();
                    }
                    SumOfMoney += player.betting(nextBet);
                    if (nextBet > firstBet) {
                        printStatus(Printing.PLAYER_RAISE, nextBet - firstBet);
                        SumOfMoney += computer.betting(nextBet - firstBet);// 컴퓨터 추가 베팅 -- 콜
                        printStatus(Printing.COMPUTER_CALL, 0);
                        //컴퓨터 추가 베팅 -- 레이즈 추가구현 예정
                    } else
                        printStatus(Printing.PLAYER_CALL, 0);
                }
            }
        }
    }

    public int computerBetting(int turn, int firstBet) {
        int bettingMoney;
        HandRank handRank;
        handRank = this.evaluator.evaluate(computer.getHand().getCardList());

        if (computer.getHand().getCardList().size() < 5)
        {
            bettingMoney = (turn == 0 ) ? firstBet : (int)Math.random() * 1000;
            return bettingMoney;
        }
        if (turn == 1) {
            bettingMoney = (handRank.getRankOfHand() > 2) ?
                    (handRank.getRankOfHand() > 8) ?
                            firstBet * 2 : firstBet : 0;
        } else {
            bettingMoney = (int) Math.random() * 100;
        }
        return bettingMoney;
    }

    public void printStatus(Printing choice, int output) {
        System.out.println("[ 현재 판돈 : " + SumOfMoney + "만원 ] [ 유저 소지금 : " + player.getMoney() + "만원] [ 컴퓨터 소지금 : " + computer.getMoney() + "만원 ]");
        switch (choice) {
            case PLAYER_CALL://유저가 콜 하는 경우
                System.out.println(">>> 당신은 콜 했습니다.");
                break;
            case PLAYER_RAISE://유저가 레이즈 하는 경우
                System.out.println(">>> 당신은 " + output + "만원 레이즈 했습니다.");
                break;
            case PLAYER_BET://유저가 베팅하는 경우
                System.out.println(">>> 당신은 " + output + "만원 만큼 베팅했습니다.");
                break;
            case PLAYER_WIN://유저가 이긴 경우
                System.out.println(">>> 승리!! " + SumOfMoney + "만원 획득!!");
                break;
            case PLAYER_DIE://유저가 다이한 경우
                System.out.println(">>> 당신은 다이 했습니다.");
                break;
            case COMPUTER_CALL://컴퓨터가 콜하는 경우
                System.out.println(">>> 컴퓨터가 콜 했습니다.");
                break;
            case COMPUTER_RAISE://컴퓨터가 레이즈 하는 경우
                System.out.println(">>> 컴퓨터가 " + output + "만원 레이즈 했습니다.");
                break;
            case COMPUTER_BET://컴퓨터가 베팅하는 경우
                System.out.println(">>> 컴퓨터가 " + output + "만원 만큼 베팅했습니다.");
                break;
            case COMPUTER_WIN://컴퓨터가 이긴 경우
                System.out.println(">>> 패배!! " + (SumOfMoney / 2) + "만원 만큼 잃었습니다");
                break;
            case COMPUTER_DIE://컴퓨터가 다이한 경우
                System.out.println(">>> 컴퓨터가 다이 했습니다. "+SumOfMoney+"만원 획득!!");
                break;
            case CARDS_IN_TABLE://테이블에 공개된 카드
                computer.getHand().OpponentCard();
                player.getHand().MyCard();
                break;
            case CARDS_OPENED://마지막에 카드 공개
                computer.getHand().MyCard();
                player.getHand().MyCard();
                break;
            default:
                break;
        }
    }
  
    public void runSevenPokerGame(int startMoney){
        isDie=Printing.DEFAULT;
        enterNewGame(startMoney,3);
        bettingTime(evaluating(player.getHand().getDisplayedCard(),player.getHand().getDisplayedCard()));//오프닝 후 첫번째 베팅
        for (int i=0;i<4;i++){
            if (isDie == Printing.DEFAULT) {
                player.getHand().CardAddtion(1);
                computer.getHand().CardAddtion(1);
                printStatus(Printing.CARDS_IN_TABLE, 0);
                bettingTime(evaluating(player.getHand().getDisplayedCard(), player.getHand().getDisplayedCard()));
            }else if (isDie==Printing.PLAYER_DIE){//유저가 죽은 경우
                printStatus(Printing.PLAYER_DIE,0);
                computer.won(SumOfMoney);
                break;
            }else if (isDie==Printing.COMPUTER_DIE){//컴퓨터가 죽은 경우
                printStatus(Printing.COMPUTER_DIE,0);
                player.won(SumOfMoney);
                break;
            }
        }
        if (isDie==Printing.DEFAULT){
            if (evaluating(player.getHand().getCardList(),computer.getHand().getCardList())==0){
                printStatus(Printing.PLAYER_WIN,0);
                player.won(SumOfMoney);
            }else {
                printStatus(Printing.COMPUTER_WIN,0);
                computer.won(SumOfMoney);
            }
        }

        SumOfMoney=0;
        printStatus(Printing.DEFAULT,0);
    }

    public int evaluating(List<Card> playerList, List<Card> computerList) {
        //int playerRank = evaluator.evaluate(playerList).getRankOfHand();
        //int computerRank = evaluator.evaluate(computerList).getRankOfHand();
        int playerRank=1;
        int computerRank=0;

        if (playerRank > computerRank) {
            return 0;
        } else if (playerRank == computerRank) {
            //족보가 같을경우 숫자-모양을 비교후 리턴받은 값
            int isSofRSame =
                    (evaluator.sumOfLank(playerList) >= evaluator.sumOfLank(computerList)) ?
                            (evaluator.sumOfLank(playerList) > evaluator.sumOfLank(computerList)) ?
                                    0 : -1 : 1;
            int moreValuableRank = (isSofRSame == -1) ?
                    (evaluator.sumOfSuit(playerList) > evaluator.sumOfSuit(computerList)) ?
                            0 : 1: isSofRSame;
            return moreValuableRank;
        } else
            return 1;
    }
}
