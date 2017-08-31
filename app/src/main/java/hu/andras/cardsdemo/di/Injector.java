package hu.andras.cardsdemo.di;


import hu.andras.cardsdemo.ui.main.MainActivity;
import hu.andras.cardsdemo.ui.main.MainViewModel;

public class Injector {

    private static final MainViewModel mainViewModel = new MainViewModel();

    public static void inject(MainActivity mainActivity) {
        mainActivity.setViewModel(mainViewModel);
    }
}
