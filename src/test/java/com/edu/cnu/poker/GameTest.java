package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.Deck;
import com.edu.cnu.poker.DataObject.Hand;
import com.edu.cnu.poker.DataObject.PokerType;
import org.junit.Test;


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
    public void 까진_패가_높은사람부터_시작(){
        Game game=new Game(10000,PokerType.SEVEN);
        game.enterNewGame(100,3);
        game.evaluating(game.getPlayer().getHand().getDisplayedCard(),game.getComputer().getHand().getDisplayedCard());
        assertThat(game.getPlayer().getMoney(),is(9900));

    }
    /*
    @Test
    public void  턴이_지날때_마다_돈을_걸자(){
        Game game=new Game(10000,PokerType.SEVEN);
        game.betting();
        assertThat(game.getSumOfMoney(),is(1000));
    }

    @Test
    public void 세븐포커_과정은(){
        Game game=new Game(10000,PokerType.SEVEN);
        Deck deck=new Deck(1);
        Hand hand=new Hand(deck,PokerType.SEVEN);
    }*/
}