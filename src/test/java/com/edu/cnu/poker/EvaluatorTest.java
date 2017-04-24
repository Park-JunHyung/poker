package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.Card;
import com.edu.cnu.poker.DataObject.HandRank;
import com.edu.cnu.poker.DataObject.Suit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cse on 2017-04-17.
 */
public class EvaluatorTest {

    @Test
    public void SUIT가_5개가동일하면_플러쉬다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(4,Suit.CLUBS),
                new Card(8,Suit.CLUBS),
                new Card(13,Suit.CLUBS),
                new Card(2,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.Flush));
    }
    @Test
    public void SUIT가_같고_숫자가_A_K_Q_J_10_이면_로열스트레이트플러시() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(10,Suit.CLUBS),
                new Card(11,Suit.CLUBS),
                new Card(12,Suit.CLUBS),
                new Card(13,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.RoyalStraightFlush));
    }
    @Test
    public void SUIT가_같고_숫자가_A_2_3_4_5_이면_백스트레이트플러시() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(2,Suit.CLUBS),
                new Card(3,Suit.CLUBS),
                new Card(4,Suit.CLUBS),
                new Card(5,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.BackStraightFlush));
    }
    @Test
    public void SUIT가_같고_숫자가_연속되면_스트레이트플러시() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2, Suit.CLUBS),
                new Card(3,Suit.CLUBS),
                new Card(4,Suit.CLUBS),
                new Card(5,Suit.CLUBS),
                new Card(6,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.StraightFlush));
    }

}