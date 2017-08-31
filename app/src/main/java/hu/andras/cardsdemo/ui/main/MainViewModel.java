package hu.andras.cardsdemo.ui.main;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.List;

import hu.andras.cardsdemo.BR;
import hu.andras.cardsdemo.R;
import hu.andras.cardsdemo.data.Card;

import static hu.andras.cardsdemo.data.CardType.cc;
import static hu.andras.cardsdemo.data.CardType.cloud;
import static hu.andras.cardsdemo.data.CardType.console;
import static hu.andras.cardsdemo.data.CardType.multiscreen;
import static hu.andras.cardsdemo.data.CardType.remote;
import static hu.andras.cardsdemo.data.CardType.tablet;
import static hu.andras.cardsdemo.data.CardType.tv;
import static hu.andras.cardsdemo.data.CardType.vr;

public class MainViewModel extends BaseObservable {

    private static final int CARD_BACKGROUND_RES_ID = R.drawable.card_background;

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

    public List<CardViewModel> getCardViewModels() {
        return cardViewModels;
    }

    private List<CardViewModel> cardViewModels; {
        cardViewModels = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            cardViewModels.add(new CardViewModel(i, this));
        }
    }

    @DrawableRes
    public int getCardImageRes(int index) {
        Card card = cards.get(index);
        return card.isTurnedUp() ? card.getCardType().getImageResId() : CARD_BACKGROUND_RES_ID;
    }

    public void onCardClick(int index) {
        Card card = cards.get(index);
        if (!card.isPairFound()) {
            card.turn();
            cardViewModels.get(index).notifyImageRes();
        }
    }

    public static class CardViewModel extends BaseObservable {
        private int index;
        private MainViewModel viewModel;

        public CardViewModel(int index, MainViewModel viewModel) {
            this.index = index;
            this.viewModel = viewModel;
        }

        @Bindable
        @DrawableRes
        public int getCardImageRes() {
            return viewModel.getCardImageRes(index);
        }

        public void notifyImageRes() {
            notifyPropertyChanged(BR.cardImageRes);
        }

    }

}
