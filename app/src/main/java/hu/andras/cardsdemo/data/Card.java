package hu.andras.cardsdemo.data;

import lombok.Data;

@Data
public class Card {

    private CardType cardType;
    private boolean pairFound;
}
