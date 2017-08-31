package hu.andras.cardsdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hu.andras.cardsdemo.R;
import hu.andras.cardsdemo.data.Card;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Card().setPairFound(true);
    }
}
