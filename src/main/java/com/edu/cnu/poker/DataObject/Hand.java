package com.edu.cnu.poker.DataObject;

import com.edu.cnu.poker.DataObject.Card;
import com.edu.cnu.poker.DataObject.Deck;
import com.edu.cnu.poker.PokerType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cse on 2017-04-17.
 */
public class Hand {

    private Deck deck;
    private PokerType pokerType;
    private List<Card> cardList;
    private List<Card> displayedCard;

    public Hand(Deck deck, PokerType pokerType) {
        this.deck = deck;
        this.pokerType = pokerType;
        cardList = new ArrayList<Card>();
        displayedCard=new ArrayList<Card>();
        for (int i = 0; i < pokerType.getNumberOfCard(); i++) {
            cardList.add(deck.drawCard());
        }
    }

    public int getTotalCard() {
        return cardList.size();
    }

    public void OpponentCard() {
        for (Card card : cardList){
            if (card.equals(cardList.get(0))||card.equals(cardList.get(1))||card.equals(cardList.get(6))){
                System.out.printf("%15s","[Fliped]");
            }else{
                displayedCard.add(card);
                System.out.printf("%15s",card.getSuit()+"_"+card.getRank());
            }
        }
        System.out.println();
    }
    public void MyCard() {
        for (Card card : cardList){
            if (!(card.equals(cardList.get(0))||card.equals(cardList.get(1))||card.equals(cardList.get(6)))) {
                displayedCard.add(card);
            }
                System.out.printf("%15s",card.getSuit()+"_"+card.getRank());

        }
        System.out.println();
    }

}
