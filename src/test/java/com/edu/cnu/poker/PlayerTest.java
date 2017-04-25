package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.Deck;
import com.edu.cnu.poker.DataObject.Hand;
import com.edu.cnu.poker.DataObject.Player;
import com.edu.cnu.poker.DataObject.PokerType;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Schwa on 2017-04-24.
 */
public class PlayerTest {


    @Test
    public void 플레이어는_베팅시_판돈보다많이_소지금보다_적게_걸어야한다(){
        Deck deck=new Deck(1);
        Hand hand=new Hand(deck, PokerType.SEVEN);
        Player player=new Player(1000,hand);
        int result = player.betting(500);

        assertThat(result,is(500));
    }

    @Test
    public void 플레이어가_DIE_할경우_해당게임종료(){
        Deck deck=new Deck(1);
        Hand hand=new Hand(deck, PokerType.SEVEN);
        Player player=new Player(1000,hand);
        int result = player.die();

        assertThat(result,is(1000));
    }
}