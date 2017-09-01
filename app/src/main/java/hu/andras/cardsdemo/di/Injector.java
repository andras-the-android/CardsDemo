package hu.andras.cardsdemo.di;


import hu.andras.cardsdemo.businesslogic.GameLogic;
import hu.andras.cardsdemo.ui.main.MainActivity;
import hu.andras.cardsdemo.ui.main.MainViewModel;

public class Injector {

    private static final MainViewModel mainViewModel = new MainViewModel(new GameLogic());

    public static void inject(MainActivity mainActivity) {
        mainActivity.setViewModel(mainViewModel);
    }
}
