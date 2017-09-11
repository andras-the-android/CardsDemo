package hu.andras.cardsdemo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.andras.cardsdemo.data.Card;

import static hu.andras.cardsdemo.data.CardType.cc;
import static hu.andras.cardsdemo.data.CardType.cloud;
import static hu.andras.cardsdemo.data.CardType.console;
import static hu.andras.cardsdemo.data.CardType.multiscreen;
import static hu.andras.cardsdemo.data.CardType.remote;
import static hu.andras.cardsdemo.data.CardType.tablet;
import static hu.andras.cardsdemo.data.CardType.tv;
import static hu.andras.cardsdemo.data.CardType.vr;


public class GameRepository {

    private static final int NUMBER_OF_CARDS = 16;

    private int score;
    private int firstSelectedIndex;
    private int secondSelectedIndex;
    private List<Card> cards;

    public GameRepository() {
        resetGame();
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public void increaseScoreWith(int value) {
        score += value;
    }

    public void decreaseScoreWith(int value) {
        score -= value;
    }

    public void resetGame() {
        score = 0;
        firstSelectedIndex = -1;
        secondSelectedIndex = -1;
        generateCards();
    }

    public boolean isAllPairFound() {
        for (Card card : cards) {
            if (!card.isPairFound()) {
                return false;
            }
        }
        return true;
    }

    private void generateCards() {
        cards = new ArrayList<>(NUMBER_OF_CARDS);
        cards.add(new Card(cc));
        cards.add(new Card(cc));
        cards.add(new Card(cloud));
        cards.add(new Card(cloud));
        cards.add(new Card(console));
        cards.add(new Card(console));
        cards.add(new Card(multiscreen));
        cards.add(new Card(multiscreen));
        cards.add(new Card(remote));
        cards.add(new Card(remote));
        cards.add(new Card(tablet));
        cards.add(new Card(tablet));
        cards.add(new Card(tv));
        cards.add(new Card(tv));
        cards.add(new Card(vr));
        cards.add(new Card(vr));

        mixCards(cards);
    }

    private void mixCards(List<Card> cards) {
        Random random = new Random();
        for (int i = 0; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            int switchIndex = random.nextInt(NUMBER_OF_CARDS);
            cards.set(i, cards.get(switchIndex));
            cards.set(switchIndex, currentCard);
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFirstSelectedIndex() {
        return firstSelectedIndex;
    }

    public void setFirstSelectedIndex(int firstSelectedIndex) {
        this.firstSelectedIndex = firstSelectedIndex;
    }

    public int getSecondSelectedIndex() {
        return secondSelectedIndex;
    }

    public void setSecondSelectedIndex(int secondSelectedIndex) {
        this.secondSelectedIndex = secondSelectedIndex;
    }
}
