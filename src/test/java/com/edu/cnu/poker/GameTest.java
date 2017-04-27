package com.edu.cnu.poker;


import com.edu.cnu.poker.DataObject.Card;
import com.edu.cnu.poker.DataObject.HandRank;
import com.edu.cnu.poker.DataObject.PokerType;
import com.edu.cnu.poker.DataObject.Suit;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Schwa on 2017-04-24.
 */
public class GameTest {
    @Test
    public void 더이상_돈이_없으면_패배(){

    }

    @Test
    public void 게임시작시_판돈은_걸어야지(){
        Game game=new Game(10000,PokerType.SEVEN);
        game.enterNewGame(100,3);
        assertThat(game.getPlayer().getMoney(),is(9900));

    }

    @Test
    public void 사용자가_컴퓨터보다_오픈한_패가_좋으면_먼저한다(){
        Game game=new Game(10000,PokerType.SEVEN);
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(13,Suit.SPADES),
                new Card(1,Suit.DIAMONDS),
                new Card(1,Suit.HEARTS),
                new Card(2,Suit.CLUBS)
        );
        List<Card> cardList2 = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(13,Suit.SPADES),
                new Card(3,Suit.DIAMONDS),
                new Card(8,Suit.HEARTS),
                new Card(2,Suit.CLUBS)
        );
        game.getPlayer().getHand().setDisplayedCard(cardList);
        game.getComputer().getHand().setDisplayedCard(cardList2);
        int result=game.evaluating(game.getPlayer().getHand().getDisplayedCard(),game.getComputer().getHand().getDisplayedCard());
        assertThat(result,is(0));

    }

    @Test
    public void  사용자가_500만원_먼저_걸면_컴퓨터가_콜을한다(){
        Game game=new Game(10000,PokerType.SEVEN);
        game.enterNewGame(100,3);

        String input = "500";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int turn=game.evaluating(game.getPlayer().getHand().getDisplayedCard(),game.getComputer().getHand().getDisplayedCard());
        game.bettingTime(turn);
        assertThat(game.getSumOfMoney(),is(1200));
    }
    @Test
    public void  컴퓨터가_먼저_걸면_사용자가_콜을_할수있다(){
        Game game=new Game(10000,PokerType.SEVEN);
        game.enterNewGame(100,3);
        String input = "600";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        game.bettingTime(0);
        assertThat(game.getSumOfMoney(),is(1400));
    }
    @Test
    public void  컴퓨터가_먼저_걸고_사용자가_레이즈하면_컴퓨터가_콜한다(){
        Game game=new Game(10000,PokerType.SEVEN);
        game.enterNewGame(100,3);
        String input = "800";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        game.bettingTime(0);
        assertThat(game.getSumOfMoney(),is(1800));
    }
    /*
    @Test
    public void 세븐포커_7장씩_패를_받고_마지막_베팅을_한다음_비교후_이긴사람이_다먹는다(){
        Game game=new Game(10000,PokerType.SEVEN);
        String input = "800";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        game.runSevenPokerGame(100);
    }*/
    @Test
    public void 플레이어가_다이하면_패배(){
        Game game=new Game(10000,PokerType.SEVEN);
        String input = "0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        game.runSevenPokerGame(100);

    }

    @Test
    public void 컴퓨터는_자기차례에서_패가_5장이상이고_보여지는_플레이어_패보다_낮으면_다이한다(){
        Game game = new Game(10000, PokerType.SEVEN);
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(13,Suit.SPADES),
                new Card(1,Suit.DIAMONDS),
                new Card(1,Suit.HEARTS),
                new Card(2,Suit.CLUBS)
        );
        List<Card> cardList2 = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(13,Suit.SPADES),
                new Card(3,Suit.DIAMONDS),
                new Card(8,Suit.HEARTS),
                new Card(2,Suit.CLUBS)
        );
        game.getPlayer().getHand().setDisplayedCard(cardList);
        game.getComputer().getHand().setCardList(cardList2);
        int bettingMoney = game.computerBetting(0,500);
        assertThat(bettingMoney,is(0));
    }
}