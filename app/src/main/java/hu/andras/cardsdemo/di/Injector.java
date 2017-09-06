package hu.andras.cardsdemo.di;


import hu.andras.cardsdemo.businesslogic.GameLogic;
import hu.andras.cardsdemo.ui.main.MainActivity;
import hu.andras.cardsdemo.ui.main.MainDialog;
import hu.andras.cardsdemo.ui.main.MainDialogViewModel;
import hu.andras.cardsdemo.ui.main.MainViewModel;

public class Injector {

    private static final MainViewModel mainViewModel = new MainViewModel(new GameLogic());
    private static final MainDialogViewModel mainDialogViewModel = new MainDialogViewModel();

    public static void inject(MainActivity mainActivity) {
        mainActivity.setViewModel(mainViewModel);
    }

    public static void inject(MainDialog mainDialog) {
        mainDialog.setViewModel(mainDialogViewModel);
    }
}
