package hu.andras.cardsdemo.businesslogic;

import android.os.Handler;

import hu.andras.cardsdemo.data.Card;
import hu.andras.cardsdemo.ui.main.MainViewModel;

import static hu.andras.cardsdemo.ui.main.CardBindingAdapter.ANIMATION_DURATION;

public class GameLogic {

    private static final int TURN_BACK_DELAY = 1000 + 2 * ANIMATION_DURATION;

    private MainViewModel viewModel;
    private Handler handler = new Handler();
    private GameRepository repository;

    public GameLogic(GameRepository repository) {
        this.repository = repository;
    }

    public Card get(int index) {
        return repository.getCard(index);
    }

    public void onCardClick(int index) {
        Card clickedCard = repository.getCard(index);
        if (isCardClickable(clickedCard)) {
            turnCard(index);

            if (hasFirstSelectedCard()) {
                Card firstSelectedCard = repository.getCard(repository.getFirstSelectedIndex());
                repository.setSecondSelectedIndex(index);
                if (firstSelectedCard.getCardType() == clickedCard.getCardType()) {
                    handleMatchingCards();
                } else {
                    handleNonMatchingCards();
                }
            } else {
                repository.setFirstSelectedIndex(index);
            }
        }
    }

    private boolean hasFirstSelectedCard() {
        return repository.getFirstSelectedIndex() >= 0;
    }

    private void handleNonMatchingCards() {
        scoreNonMatchingCards();
        handler.postDelayed(this::turnBackSelectedCards, TURN_BACK_DELAY);
    }

    private void turnBackSelectedCards() {
        turnCard(repository.getFirstSelectedIndex());
        turnCard(repository.getSecondSelectedIndex());
        repository.setFirstSelectedIndex(-1);
        repository.setSecondSelectedIndex(-1);
    }

    private void turnCard(int index) {
        repository.getCard(index).turn();
        viewModel.notifyCardTurn(index);
    }

    private void handleMatchingCards() {
        scoreMatchingCards();
        repository.getCard(repository.getFirstSelectedIndex()).setPairFound(true);
        repository.getCard(repository.getSecondSelectedIndex()).setPairFound(true);
        hideCards(repository.getFirstSelectedIndex(), repository.getSecondSelectedIndex());
        repository.setFirstSelectedIndex(-1);
        repository.setSecondSelectedIndex(-1);
        checkGameEnd();
    }

    private void hideCards(final int firstIndex, final int secondIndex) {
        handler.postDelayed(() -> {
            viewModel.notifyPairFound(firstIndex);
            viewModel.notifyPairFound(secondIndex);}, ANIMATION_DURATION * 2);

    }

    private boolean isCardClickable(Card card) {
        return !card.isPairFound() && repository.getSecondSelectedIndex() < 0;
    }

    private void  scoreMatchingCards() {
        repository.increaseScoreWith(5);
        viewModel.notifyScore();
    }

    private void  scoreNonMatchingCards() {
        repository.decreaseScoreWith(1);
        viewModel.notifyScore();
    }

    public int getScore() {
        return repository.getScore();
    }

    private void checkGameEnd() {
        if (repository.isAllPairFound()) {
            viewModel.onGameEnd();
        }
    }

    public void setViewModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
        checkGameEnd();
    }

    public void newGame() {
        repository.resetGame();
    }
}
