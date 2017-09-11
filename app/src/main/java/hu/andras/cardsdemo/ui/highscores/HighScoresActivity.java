package hu.andras.cardsdemo.ui.highscores;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import hu.andras.cardsdemo.R;
import hu.andras.cardsdemo.databinding.ActivityHighScoresBinding;
import hu.andras.cardsdemo.di.Injector;
import hu.andras.cardsdemo.repository.HighScoreRepository;

public class HighScoresActivity extends AppCompatActivity {

    private HighScoreRepository repository;
    private HighScoresViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHighScoresBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_high_scores);
        Injector.inject(this);
        initViewModel();
        binding.setModel(viewModel);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViewModel() {
        viewModel = (HighScoresViewModel) getLastCustomNonConfigurationInstance();
        if (viewModel == null) {
            viewModel = new HighScoresViewModel(repository);
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return viewModel;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setRepository(HighScoreRepository repository) {
        this.repository = repository;
    }
}
