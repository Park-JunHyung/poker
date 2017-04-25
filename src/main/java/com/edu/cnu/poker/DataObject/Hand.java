package com.edu.cnu.poker.DataObject;


import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cse on 2017-04-17.
 */
@Data
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

    }
    public void CardAddtion(int repeatNumber){
        for (int i = 0; i < repeatNumber; i++) {
            cardList.add(deck.drawCard());
        }
    }

    public int getTotalCard() {
        return cardList.size();
    }

    public void OpponentCard() {
        System.out.print("Computer :\t");
        for (Card card : cardList){
            if (card.equals(cardList.get(0))||card.equals(cardList.get(1))||cardList.size()==7){
                System.out.printf("%15s","[Fliped]");
            }else{
                displayedCard.add(card);
                System.out.printf("%15s",card.getSuit()+"_"+card.getRank());
            }
        }
        System.out.println();
    }
    public void MyCard() {
        System.out.print("Player :\t");
        for (Card card : cardList){
            if (!(card.equals(cardList.get(0))||card.equals(cardList.get(1))||cardList.size()==7)) {
                displayedCard.add(card);
            }
                System.out.printf("%15s",card.getSuit()+"_"+card.getRank());

        }
        System.out.println();
    }

}
