package hu.andras.cardsdemo.businesslogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.andras.cardsdemo.data.Card;
import hu.andras.cardsdemo.data.Score;
import lombok.Getter;
import lombok.Setter;

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

    @Getter @Setter private int score;
    @Getter @Setter private int firstSelectedIndex = -1;
    @Getter @Setter private int secondSelectedIndex = -1;
    @Getter @Setter private List<Card> cards;

    public GameRepository() {
        generateCards();
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
        firstSelectedIndex = 0;
        secondSelectedIndex = 0;
        generateCards();
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

//        mixCards(cards);
    }

    @SuppressWarnings("unused")
    private void mixCards(List<Card> cards) {
        Random random = new Random();
        for (int i = 0; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            int switchIndex = random.nextInt(NUMBER_OF_CARDS);
            cards.set(i, cards.get(switchIndex));
            cards.set(switchIndex, currentCard);
        }
    }
}
