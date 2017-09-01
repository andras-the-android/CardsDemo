package hu.andras.cardsdemo.businesslogic;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.andras.cardsdemo.data.Card;
import hu.andras.cardsdemo.ui.main.MainViewModel;
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
import static hu.andras.cardsdemo.ui.main.CardBindingAdapter.ANIMATION_DURATION;

public class GameLogic {

    private static final int TURN_BACK_DELAY = 1000 + 2 * ANIMATION_DURATION;
    private static final int NUMBER_OF_CARDS = 16;

    @Setter private MainViewModel viewModel;
    @Getter private int score;

    private int firstSelectedIndex = -1;
    private int secondSelectedIndex = -1;

    private List<Card> cards;  {
        generateCards();
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public void onCardClick(int index) {
        Card clickedCard = cards.get(index);
        if (isCardClickable(clickedCard)) {
            turnCard(index);

            if (hasFirstSelectedCard()) {
                Card firstSelectedCard = cards.get(firstSelectedIndex);
                if (firstSelectedCard.getCardType() == clickedCard.getCardType()) {
                    handleMatchingCards(firstSelectedCard, clickedCard);
                } else {
                    handleNonMatchingCards(index);
                }
            } else {
                firstSelectedIndex = index;
            }
        }
    }

    private boolean hasFirstSelectedCard() {
        return firstSelectedIndex >= 0;
    }

    private void handleNonMatchingCards(int index) {
        scoreNonMatchingCards();
        secondSelectedIndex = index;
        new Handler().postDelayed(this::turnBackSelectedCards, TURN_BACK_DELAY);
    }

    private void turnBackSelectedCards() {
        turnCard(firstSelectedIndex);
        turnCard(secondSelectedIndex);
        firstSelectedIndex = -1;
        secondSelectedIndex = -1;
    }

    private void turnCard(int index) {
        cards.get(index).turn();
        viewModel.notifyCardTurn(index);
    }

    private void handleMatchingCards(Card firstSelected, Card card) {
        scoreMatchingCards();
        firstSelected.setPairFound(true);
        card.setPairFound(true);
        firstSelectedIndex = -1;
    }

    private boolean isCardClickable(Card card) {
        return !card.isPairFound() && secondSelectedIndex < 0;
    }

    private void  scoreMatchingCards() {
        score += 5;
        viewModel.notifyScore();
    }

    private void  scoreNonMatchingCards() {
        score -= 1;
        viewModel.notifyScore();
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

//        mixCards();
    }

    @SuppressWarnings("unused")
    private void mixCards() {
        Random random = new Random();
        for (int i = 0; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            int switchIndex = random.nextInt(NUMBER_OF_CARDS);
            cards.set(i, cards.get(switchIndex));
            cards.set(switchIndex, currentCard);
        }
    }
}
