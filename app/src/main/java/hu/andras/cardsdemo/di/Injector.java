package hu.andras.cardsdemo.di;


import hu.andras.cardsdemo.businesslogic.GameLogic;
import hu.andras.cardsdemo.businesslogic.GameRepository;
import hu.andras.cardsdemo.ui.main.MainActivity;
import hu.andras.cardsdemo.ui.main.dialog.MainDialog;
import hu.andras.cardsdemo.ui.main.dialog.MainDialogViewModel;

public class Injector {

    private static final GameRepository gameRepository = new GameRepository();

    public static void inject(MainActivity mainActivity) {
        mainActivity.setGameLogic(new GameLogic(gameRepository));
    }

    public static void inject(MainDialog mainDialog) {
    }
}
