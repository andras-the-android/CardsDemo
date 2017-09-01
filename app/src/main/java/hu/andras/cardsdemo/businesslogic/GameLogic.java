package hu.andras.cardsdemo.businesslogic;

import java.util.ArrayList;
import java.util.List;

import hu.andras.cardsdemo.data.Card;
import hu.andras.cardsdemo.ui.main.MainViewModel;
import lombok.Setter;

import static hu.andras.cardsdemo.data.CardType.cc;
import static hu.andras.cardsdemo.data.CardType.cloud;
import static hu.andras.cardsdemo.data.CardType.console;
import static hu.andras.cardsdemo.data.CardType.multiscreen;
import static hu.andras.cardsdemo.data.CardType.remote;
import static hu.andras.cardsdemo.data.CardType.tablet;
import static hu.andras.cardsdemo.data.CardType.tv;
import static hu.andras.cardsdemo.data.CardType.vr;

public class GameLogic {

    @Setter private MainViewModel viewModel;

    private List<Card> cards;  {
        cards = new ArrayList<>(16);
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
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public void onCardClick(int index) {
        Card card = cards.get(index);
        if (!card.isPairFound()) {
            card.turn();
            viewModel.notifyCardTurn(index);
        }
    }
}
