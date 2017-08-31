package hu.andras.cardsdemo.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Card {

    public Card(CardType cardType) {
        this.cardType = cardType;
    }

    private CardType cardType;
    private boolean pairFound;
    private boolean turnedUp;

    public void turn() {
        turnedUp = !turnedUp;
    }
}
