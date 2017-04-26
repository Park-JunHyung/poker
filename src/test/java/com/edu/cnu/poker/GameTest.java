package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.Deck;
import com.edu.cnu.poker.DataObject.Hand;
import com.edu.cnu.poker.DataObject.PokerType;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

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
    public void 까진_패를_비교후에_높은사람부터_시작(){
        Game game=new Game(10000,PokerType.SEVEN);
        game.enterNewGame(100,3);
        int result=game.evaluating(game.getPlayer().getHand().getDisplayedCard(),game.getComputer().getHand().getDisplayedCard());
        assertThat(result,is(1));

    }

    @Test
    public void  사용자가_500만원_걸면_컴퓨터가_콜을한다(){
        Game game=new Game(10000,PokerType.SEVEN);
        game.enterNewGame(100,3);

        String input = "500";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int turn=game.evaluating(game.getPlayer().getHand().getDisplayedCard(),game.getComputer().getHand().getDisplayedCard());
        game.betting(turn);
        assertThat(game.getSumOfMoney(),is(1200));
    }
    /*
    @Test
    public void 세븐포커_과정은(){
        Game game=new Game(10000,PokerType.SEVEN);
        Deck deck=new Deck(1);
        Hand hand=new Hand(deck,PokerType.SEVEN);
    }*/
}