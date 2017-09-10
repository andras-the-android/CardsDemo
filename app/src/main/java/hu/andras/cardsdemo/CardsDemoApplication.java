package hu.andras.cardsdemo;

import android.app.Application;

import hu.andras.cardsdemo.di.Injector;

public class CardsDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.init(this);
    }
}
