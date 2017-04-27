package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.HandRank;
import com.edu.cnu.poker.DataObject.PokerType;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

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

    @Test
    public void 세븐포커_7장씩_패를_받고_마지막_베팅을_한다음_비교후_이긴사람이_다먹는다(){
        Game game=new Game(10000,PokerType.SEVEN);
        String input = "800";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        game.sevenPokerGame(100);
    }

    @Test
    public void 컴퓨터_패가_좋으면_레이즈(){
        Game game = new Game(10000, PokerType.SEVEN);
        int bettingMoney = game.computerBetting(1,500);
        assert(bettingMoney >= 500);
    }
}