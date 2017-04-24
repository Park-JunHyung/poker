package com.edu.cnu.poker.DataObject;

import com.edu.cnu.poker.DataObject.Card;
import com.edu.cnu.poker.DataObject.Suit;
import com.edu.cnu.poker.UserException.NoSuchRankException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cse on 2017-04-17.
 */
public class CardTest {

    @Test
    public void 카드_5_다이아몬드_생성() {
        Card card = new Card(5, Suit.DIAMONDS);
        assertThat(card.getRank(), is(5));
        assertThat(card.getSuit(), is(Suit.DIAMONDS));
    }

    @Test(expected = NoSuchRankException.class)
    public void 카드의_랭크는_13이하여야한다() {
        new Card(14, Suit.CLUBS);
    }
}
