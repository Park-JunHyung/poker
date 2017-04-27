package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.Card;
import com.edu.cnu.poker.DataObject.HandRank;
import com.edu.cnu.poker.DataObject.Suit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cse on 2017-04-17.
 */
public class EvaluatorTest {

    @Test
    public void SUIT가_같고_숫자가_A_K_Q_J_10_이면_로열스트레이트플러시() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(12, Suit.CLUBS),
                new Card(1,Suit.CLUBS),
                new Card(10,Suit.CLUBS),
                new Card(13,Suit.CLUBS),
                new Card(11,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.RoyalStraightFlush));
    }

    @Test
    public void SUIT가_다르고_숫자가_A_K_Q_J_10_이면_마운틴() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(10,Suit.SPADES),
                new Card(11,Suit.HEARTS),
                new Card(12,Suit.DIAMONDS),
                new Card(13,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.Mountain));
    }

    @Test
    public void SUIT가_같고_숫자가_연속되면_스트레이트플러시() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(5, Suit.CLUBS),
                new Card(6,Suit.CLUBS),
                new Card(10,Suit.CLUBS),
                new Card(4,Suit.CLUBS),
                new Card(3,Suit.CLUBS),
                new Card(2,Suit.CLUBS),
                new Card(11,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.StraightFlush));
    }

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
    public void 숫자가_같은_카드_4개면_포카드다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(1,Suit.SPADES),
                new Card(1,Suit.DIAMONDS),
                new Card(1,Suit.HEARTS),
                new Card(2,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.FourCard));
    }

    @Test
    public void 트리플과_원페어가_동시에_있으면_풀하우스다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(2,Suit.SPADES),
                new Card(1,Suit.DIAMONDS),
                new Card(1,Suit.HEARTS),
                new Card(2,Suit.CLUBS),
                new Card(3,Suit.CLUBS),
                new Card(3,Suit.SPADES)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.FullHouse));
    }

    @Test
    public void 숫자가_5장_연속되면_스트레이트다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(6, Suit.CLUBS),
                new Card(4,Suit.SPADES),
                new Card(2,Suit.DIAMONDS),
                new Card(3,Suit.HEARTS),
                new Card(5,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.Straight));
    }

    @Test
    public void 숫자가_같은_카드_3개면_트리플이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(13,Suit.SPADES),
                new Card(1,Suit.DIAMONDS),
                new Card(1,Suit.HEARTS),
                new Card(2,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.Triple));
    }

    @Test
    public void 페어가_2개면_투페어이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2, Suit.CLUBS),
                new Card(13,Suit.SPADES),
                new Card(1,Suit.DIAMONDS),
                new Card(1,Suit.HEARTS),
                new Card(2,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.TwoPair));
    }

    @Test
    public void 같은_카드가_2개면_원페어이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2, Suit.CLUBS),
                new Card(13,Suit.SPADES),
                new Card(3,Suit.DIAMONDS),
                new Card(1,Suit.HEARTS),
                new Card(2,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.OnePair));
    }

    @Test
    public void 아무것도_아니면_탑이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2, Suit.CLUBS),
                new Card(13,Suit.SPADES),
                new Card(3,Suit.DIAMONDS),
                new Card(1,Suit.HEARTS),
                new Card(4,Suit.CLUBS)
        );
        HandRank result = evaluator.evaluate(cardList);
        assertThat(result, is(HandRank.Top));
    }

    @Test
    public void 카드_숫자_합_반환() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2, Suit.CLUBS),
                new Card(13,Suit.SPADES),
                new Card(3,Suit.DIAMONDS),
                new Card(1,Suit.HEARTS),
                new Card(4,Suit.CLUBS)
        );
        int sum = evaluator.sumOfLank(cardList);
        assert(sum == 23);
    }

    @Test
    public  void 카드_모양_합_반환() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2, Suit.CLUBS),
                new Card(13,Suit.SPADES),
                new Card(3,Suit.DIAMONDS),
                new Card(1,Suit.HEARTS),
                new Card(4,Suit.CLUBS)
        );
        int sum = evaluator.sumOfSuit(cardList);
        assert(sum == 11);
    }
    @Test
    public void 로얄스트레이트플러시_사용된_카드_확인() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(12, Suit.CLUBS),
                new Card(13,Suit.CLUBS),
                new Card(11,Suit.CLUBS),
                new Card(1,Suit.CLUBS),
                new Card(10,Suit.CLUBS),
                new Card(5,Suit.CLUBS),
                new Card(6,Suit.CLUBS)
        );
        evaluator.evaluate(cardList);
        List<Card> usedCardList = evaluator.usedCardLIst(cardList);

        assertThat(usedCardList,is(Arrays.asList(
                new Card(12, Suit.CLUBS,true),
                new Card(13,Suit.CLUBS,true),
                new Card(11,Suit.CLUBS,true),
                new Card(1,Suit.CLUBS,true),
                new Card(10,Suit.CLUBS,true)
        )));
    }
    @Test
    public void 스트레이트플러시_사용된_카드_확인() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(7, Suit.CLUBS),
                new Card(8,Suit.CLUBS),
                new Card(11,Suit.CLUBS),
                new Card(4,Suit.CLUBS),
                new Card(10,Suit.CLUBS),
                new Card(5,Suit.CLUBS),
                new Card(6,Suit.CLUBS)
        );
        evaluator.evaluate(cardList);
        List<Card> usedCardList = evaluator.usedCardLIst(cardList);
        
        assertThat(usedCardList,is(Arrays.asList(
                new Card(4, Suit.CLUBS,true),
                new Card(5,Suit.CLUBS,true),
                new Card(6,Suit.CLUBS,true),
                new Card(7,Suit.CLUBS,true),
                new Card(8,Suit.CLUBS,true)
        )));
    }
}