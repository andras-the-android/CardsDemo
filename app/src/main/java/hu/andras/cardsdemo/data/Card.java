package hu.andras.cardsdemo.data;

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

    public CardType getCardType() {
        return cardType;
    }

    public boolean isPairFound() {
        return pairFound;
    }

    public void setPairFound(boolean pairFound) {
        this.pairFound = pairFound;
    }

    public boolean isTurnedUp() {
        return turnedUp;
    }

}
