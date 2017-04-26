package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.Deck;
import com.edu.cnu.poker.DataObject.Hand;
import com.edu.cnu.poker.DataObject.PokerType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cse on 2017-04-17.
 */
public class HandTest {

    @Test
    public void 파이브카드_핸드_카드숫자는_5() {
        Deck deck = new Deck(1);
        Hand hand = new Hand(deck, PokerType.FIVE);
        hand.CardAddtion(7);
        assertThat(hand.getTotalCard(), is(5));
    }

    @Test
    public void 세븐카드_핸드_카드숫자는_7() {
        Deck deck = new Deck(1);
        Hand hand = new Hand(deck, PokerType.SEVEN);
        hand.CardAddtion(7);
        assertThat(hand.getTotalCard(), is(7));
    }

    @Test
    public void 세븐포커는_처음에_두개를_안보여주고_마지막_한개를_안보여준다(){
        Deck deck = new Deck(1);
        Hand hand1 = new Hand(deck, PokerType.SEVEN);
        Hand hand2 = new Hand(deck, PokerType.SEVEN);
        hand1.CardAddtion(7);
        hand2.CardAddtion(7);
        hand1.OpponentCard();
        hand2.MyCard();

    }
}
