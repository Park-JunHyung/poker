package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.Deck;
import com.edu.cnu.poker.DataObject.Hand;
import com.edu.cnu.poker.DataObject.Player;
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
        Game game=new Game();
        Deck deck=new Deck(1);
        Hand hand=new Hand(deck,PokerType.SEVEN);
        Player player=new Player(1500,hand);
        game.enterNewGame(100);
        assertThat(player.getMoney(),is(1400));

    }
    @Test
    public void  턴이_지날때_마다_돈을_걸자(){
        Game game=new Game();
        Deck deck=new Deck(1);
        Hand hand=new Hand(deck,PokerType.SEVEN);
        Player player1=new Player(1500,hand);
        Player player2=new Player(1500,hand);
        game.turn();
    }
}