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
        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                Collections.sort(cardList);
                if(cardList.contains(new Card(1,key))&&
                        cardList.contains(new Card(10,key))&&
                        cardList.contains(new Card(11,key))&&
                        cardList.contains(new Card(12,key))&&
                        cardList.contains(new Card(13,key))){
                    return HandRank.RoyalStraightFlush;
                }else if(cardList.contains(new Card(1,key))&&
                        cardList.contains(new Card(2,key))&&
                        cardList.contains(new Card(3,key))&&
                        cardList.contains(new Card(4,key))&&
                        cardList.contains(new Card(5,key))){
                    return HandRank.BackStraightFlush;
                }else if(cardList.containsAll(
                        Arrays.asList(
                        new Card(cardList.get(0).getRank(), key),
                        new Card(cardList.get(0).getRank()+1, key),
                        new Card(cardList.get(0).getRank()+2, key),
                        new Card(cardList.get(0).getRank()+3, key),
                        new Card(cardList.get(0).getRank()+4, key)
                ))){
                    return HandRank.StraightFlush;
                }else{
                    return HandRank.Flush;
                }

            }
        }

        return HandRank.Nothing;

    }

}
