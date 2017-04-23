package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.Card;
import com.edu.cnu.poker.DataObject.Deck;
import com.edu.cnu.poker.UserException.NoMoreCardException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cse on 2017-04-17.
 */
public class DeckTest {

    @Test
    public void Deck2개의_총카드수는_104(){
        Deck deck = new Deck(2);
        int totalCard = deck.getTotalCard();
        assertThat(totalCard, is(104));
    }

    @Test
    public void Deck5개의_총카드수는_260(){
        Deck deck = new Deck(5);
        int totalCard = deck.getTotalCard();
        assertThat(totalCard, is(260));
    }

    @Test
    public void 싱글덱에서_카드를_한장뽑으면_51장이남는다() {
        Deck deck = new Deck(1);
        deck.drawCard();
        assertThat(deck.getTotalCard(), is(51));
    }

    @Test(expected = NoMoreCardException.class)
    public void 카드가없으면_에러발생() {
        Deck deck = new Deck(1);
        for (int i = 0; i < 52; i++) {
            Card card = deck.drawCard();
            System.out.println(card.getRank());
            System.out.println(card.getSuit());
        }
        deck.drawCard();
    }
}
