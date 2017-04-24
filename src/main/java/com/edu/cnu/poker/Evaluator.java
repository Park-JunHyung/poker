package com.edu.cnu.poker;

import com.edu.cnu.poker.DataObject.Card;
import com.edu.cnu.poker.DataObject.HandRank;
import com.edu.cnu.poker.DataObject.Suit;

import java.util.*;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {
    public HandRank evaluate(List<Card> cardList) {
        Map<Suit, Integer> suitMap = makeSuitMap(cardList);
        Map<Integer, Integer> rankMap = makeRankMap(cardList);

        Suit flushKey = null;

        for (Suit key : suitMap.keySet()) {
            if (suitMap.get(key) >= 5) {
                flushKey = key;
            }
        }

        if (isRoyalStraightFlush(cardList, flushKey)) {
            return HandRank.RoyalStraightFlush;
        } else if (isStraightFlush(cardList, flushKey)) {
            return HandRank.StraightFlush;
        } else if (isFourCard(cardList, rankMap)) {
            return HandRank.FourCard;
        } else if (isFullHouse(cardList, rankMap)) {
            return HandRank.FullHouse;
        } else if (isFlush(cardList, flushKey)) {
            return HandRank.Flush;
        } else if (isStraight(cardList, rankMap)) {
            return HandRank.Straight;
        }


        return HandRank.Nothing;

    }

    private boolean isStraight(List<Card> cardList, Map<Integer, Integer> rankMap) {
        Collections.sort(cardList);
        for (int i = 0; i < 3; i++) {
            if (cardList.get(i).getRank() > 10) break;
            if (rankMap.containsKey(cardList.get(i).getRank()) &&
                    rankMap.containsKey(cardList.get(i).getRank()+1)&&
                    rankMap.containsKey(cardList.get(i).getRank()+2)&&
                    rankMap.containsKey(cardList.get(i).getRank()+3)&&
                    rankMap.containsKey(cardList.get(i).getRank()+4)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouse(List<Card> cardList, Map<Integer, Integer> rankMap) {
        boolean triple = false;
        boolean onePair = false;

        for (Integer key : rankMap.keySet()) {
            if (rankMap.get(key) == 3) {
                triple = true;
            } else if (rankMap.get(key) == 2) {
                onePair = true;
            }
        }
        return triple && onePair;
    }

    private boolean isFourCard(List<Card> cardList, Map<Integer, Integer> rankMap) {
        for (Integer key : rankMap.keySet()) {
            if (rankMap.get(key) >= 4) {
                return true;
            }
        }
        return false;
    }

    private boolean isFlush(List<Card> cardList, Suit key) {
        if (key == null) return false;
        return true;
    }

    private boolean isRoyalStraightFlush(List<Card> cardList, Suit key) {
        if (key == null) return false;
        if (cardList.contains(new Card(1, key)) &&
                cardList.contains(new Card(10, key)) &&
                cardList.contains(new Card(11, key)) &&
                cardList.contains(new Card(12, key)) &&
                cardList.contains(new Card(13, key))) {
            return true;
        }
        return false;
    }

    private boolean isStraightFlush(List<Card> cardList, Suit key) {
        if (key == null) return false;
        Collections.sort(cardList);
        for (int i = 0; i < 3; i++) {
            if (cardList.get(i).getRank() > 10) break;
            if (cardList.containsAll(
                    Arrays.asList(
                            new Card(cardList.get(i).getRank(), key),
                            new Card(cardList.get(i).getRank() + 1, key),
                            new Card(cardList.get(i).getRank() + 2, key),
                            new Card(cardList.get(i).getRank() + 3, key),
                            new Card(cardList.get(i).getRank() + 4, key)
                    ))) {
                return true;
            }
        }
        return false;
    }

    private Map<Suit, Integer> makeSuitMap(List<Card> cardList) {
        Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>();
        for (Card card : cardList) {
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);
            } else {
                tempMap.put(card.getSuit(), new Integer(1));
            }
        }

        return tempMap;
    }

    private Map<Integer, Integer> makeRankMap(List<Card> cardList) {
        Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
        for (Card card : cardList) {
            if (tempMap.containsKey(card.getRank())) {
                Integer count = tempMap.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getRank(), count);
            } else {
                tempMap.put(card.getRank(), new Integer(1));
            }
        }

        return tempMap;
    }


}
