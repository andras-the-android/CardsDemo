package hu.andras.cardsdemo.ui.main.dialog;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import hu.andras.cardsdemo.BR;
import hu.andras.cardsdemo.R;
import hu.andras.cardsdemo.data.Score;
import hu.andras.cardsdemo.repository.GameRepository;
import hu.andras.cardsdemo.repository.HighScoreRepository;

public class MainDialogViewModel extends BaseObservable {

    private final MainDialogRouter router;
    private final Resources resources;
    private final HighScoreRepository highScoreRepository;
    private int score;
    private int rank;
    private String name;
    private String errorMessage;


    MainDialogViewModel(MainDialogRouter router, Resources resources, GameRepository gameRepository, HighScoreRepository highScoreRepository) {
        this.router = router;
        this.resources = resources;
        this.highScoreRepository = highScoreRepository;
        score = gameRepository.getScore();
        rank = highScoreRepository.getRank(score);
    }

    public void onNewGameClick() {
        router.closeDialog();
    }

    public void onSaveNameClick() {
        if (validateName()) {
            highScoreRepository.addHighScore(new Score(name, score));
            router.closeDialog();
        }
    }

    public boolean isOnTheHighScoresList() {
        return rank > 0;
    }

    @Bindable
    public String getValidationErrorMessage() {
        String tempErrorMessage = errorMessage;
        errorMessage = null;
        return tempErrorMessage;
    }

    private boolean validateName() {
        if (name == null || name.length() < 3) {
            errorMessage = resources.getString(R.string.name_validation_message);
            notifyPropertyChanged(BR.validationErrorMessage);
            return false;
        }
        return true;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        if (rank < 0) {
            return resources.getString(R.string.your_score, score);
        } else if (rank == 1) {
            return resources.getString(R.string.new_high_score);
        } else {
            return resources.getString(R.string.your_score_rank, score, rank);
        }
    }

}
