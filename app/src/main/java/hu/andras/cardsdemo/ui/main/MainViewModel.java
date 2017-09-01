package hu.andras.cardsdemo.ui.main;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.List;

import hu.andras.cardsdemo.BR;
import hu.andras.cardsdemo.R;
import hu.andras.cardsdemo.businesslogic.GameLogic;
import hu.andras.cardsdemo.data.Card;

public class MainViewModel extends BaseObservable {

    private static final int CARD_BACKGROUND_RES_ID = R.drawable.card_background;

    private GameLogic gameLogic;


    public List<CardViewModel> getCardViewModels() {
        return cardViewModels;
    }

    private List<CardViewModel> cardViewModels; {
        cardViewModels = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            cardViewModels.add(new CardViewModel(i, this));
        }
    }

    public MainViewModel(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        gameLogic.setViewModel(this);
    }

    @DrawableRes
    public int getCardImageRes(int index) {
        Card card = gameLogic.get(index);
        return card.isTurnedUp() ? card.getCardType().getImageResId() : CARD_BACKGROUND_RES_ID;
    }

    public void onCardClick(int index) {
        gameLogic.onCardClick(index);
    }

    public void notifyCardTurn(int index) {
        cardViewModels.get(index).notifyImageRes();
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
