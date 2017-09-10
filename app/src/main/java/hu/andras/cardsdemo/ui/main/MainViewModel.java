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
import lombok.Setter;

public class MainViewModel extends BaseObservable {

    private static final int CARD_BACKGROUND_RES_ID = R.drawable.card_background;

    @Setter private MainRouter router;
    private GameLogic gameLogic;
    private boolean newGame = true;


    public List<CardViewModel> getCardViewModels() {
        return cardViewModels;
    }

    private List<CardViewModel> cardViewModels; {
        cardViewModels = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            cardViewModels.add(new CardViewModel(i, this));
        }
    }

    MainViewModel(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        gameLogic.setViewModel(this);
    }

    @DrawableRes
    private int getCardImageRes(int index) {
        Card card = gameLogic.get(index);
        return card.isTurnedUp() ? card.getCardType().getImageResId() : CARD_BACKGROUND_RES_ID;
    }

    private boolean isPairFound(int index) {
        return gameLogic.get(index).isPairFound();
    }

    public void onCardClick(int index) {
        gameLogic.onCardClick(index);
        newGame = false;
    }

    public void notifyCardTurn(int index) {
        cardViewModels.get(index).notifyImageRes();
    }

    public void notifyPairFound(int index) {
        cardViewModels.get(index).notifyPairFound();
    }

    @Bindable
    public String getScore() {
        return String.valueOf(gameLogic.getScore());
    }

    public void notifyScore() {
        notifyPropertyChanged(BR.score);
    }

    public void onGameEnd() {
        router.openDialog();
    }

    public void onDialogDismissed() {
        gameLogic.newGame();
        newGame = true;
        notifyChange();
        for (CardViewModel cvm : cardViewModels) {
            cvm.notifyChange();
        }
    }

    public boolean isNewGame() {
        return newGame;
    }

    public static class CardViewModel extends BaseObservable {
        private int index;
        private MainViewModel viewModel;

        CardViewModel(int index, MainViewModel viewModel) {
            this.index = index;
            this.viewModel = viewModel;
        }

        @Bindable
        @DrawableRes
        public int getCardImageRes() {
            return viewModel.getCardImageRes(index);
        }

        void notifyImageRes() {
            notifyPropertyChanged(BR.cardImageRes);
        }

        @Bindable
        public boolean isPairFound() {
            return viewModel.isPairFound(index);
        }

        void notifyPairFound() {
            notifyPropertyChanged(BR.pairFound);
        }

    }

}
