package hu.andras.cardsdemo.di;


import android.content.Context;

import hu.andras.cardsdemo.CardsDemoApplication;
import hu.andras.cardsdemo.businesslogic.GameLogic;
import hu.andras.cardsdemo.businesslogic.GameRepository;
import hu.andras.cardsdemo.businesslogic.HighScoreRepository;
import hu.andras.cardsdemo.persitency.HighScoreDao;
import hu.andras.cardsdemo.ui.main.MainActivity;
import hu.andras.cardsdemo.ui.main.dialog.MainDialog;
import hu.andras.cardsdemo.ui.main.dialog.MainDialogViewModel;

public class Injector {

    private static GameRepository gameRepository;
    private static HighScoreRepository highScoreRepository;

    public static void inject(MainActivity mainActivity) {
        mainActivity.setGameLogic(new GameLogic(gameRepository));
    }

    public static void inject(MainDialog mainDialog) {
        mainDialog.setHighScoreRepository(highScoreRepository);
        mainDialog.setGameRepository(gameRepository);
    }

    public static void init(Context context) {
        gameRepository = new GameRepository();
        highScoreRepository = new HighScoreRepository(new HighScoreDao(context.getSharedPreferences("", Context.MODE_PRIVATE)));
    }
}
