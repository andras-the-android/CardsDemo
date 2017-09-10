package hu.andras.cardsdemo.ui.main;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hu.andras.cardsdemo.R;
import hu.andras.cardsdemo.businesslogic.GameLogic;
import hu.andras.cardsdemo.databinding.ActivityMainBinding;
import hu.andras.cardsdemo.di.Injector;
import hu.andras.cardsdemo.ui.main.dialog.MainDialog;
import lombok.Setter;

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity  implements MainRouter, DialogInterface.OnDismissListener{

    @Setter private GameLogic gameLogic;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initViewModel();
        binding.setModel(viewModel);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initViewModel() {
        viewModel = (MainViewModel) getLastCustomNonConfigurationInstance();
        if (viewModel == null) {
            viewModel = new MainViewModel(gameLogic);
        }
        viewModel.setRouter(this);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return viewModel;
    }

    @Override
    public void openDialog() {
        MainDialog mainDialog = new MainDialog();
        mainDialog.show(getSupportFragmentManager(), "");
        getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        viewModel.onDialogDismissed();
    }
}
