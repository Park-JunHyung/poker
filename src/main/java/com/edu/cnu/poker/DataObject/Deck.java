package com.edu.cnu.poker.DataObject;

import com.edu.cnu.poker.UserException.NoMoreCardException;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cse on 2017-04-17.
 */
@Data
public class Deck {

    private int numberOfDeck;
    private List<Card> cardList;

    public Deck(int numberOfDeck) {
        this.numberOfDeck = numberOfDeck;
        this.cardList = new ArrayList<Card>();
        createCard(numberOfDeck);
    }

    private void createCard(int numberOfDeck) {
        // card 생성해보자
        for (int j = 0; j < numberOfDeck; j++) {
            for (Suit suit : Suit.values()) {
                for (int i = 1; i < 14; i++) {
                    Card card = new Card(i, suit);
                    cardList.add(card);
                }
            }
        }
        Collections.shuffle(cardList);
    }

    public int getTotalCard() {
        return cardList.size();
    }

    public Card drawCard() {
        if (cardList.size() == 0) {
            throw new NoMoreCardException();
        }
        return cardList.remove(0);
    }
}
