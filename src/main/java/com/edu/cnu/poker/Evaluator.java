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
                if(cardList.get(0).getRank()==1&&
                   cardList.get(1).getRank()==10&&
                   cardList.get(2).getRank()==11&&
                   cardList.get(3).getRank()==12&&
                   cardList.get(4).getRank()==13){
                    return HandRank.RoyalStraightFlush;
                }else if(cardList.get(0).getRank()==1&&
                        cardList.get(1).getRank()==2&&
                        cardList.get(2).getRank()==3&&
                        cardList.get(3).getRank()==4&&
                        cardList.get(4).getRank()==5){
                    return HandRank.BackStraughtFlush;
                }
                return HandRank.Flush;
            }
        }

        return HandRank.Nothing;

    }

}
