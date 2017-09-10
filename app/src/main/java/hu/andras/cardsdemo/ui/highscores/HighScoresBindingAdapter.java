package hu.andras.cardsdemo.ui.highscores;

import android.databinding.BindingAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HighScoresBindingAdapter {

    @BindingAdapter("configureWithAdapter")
    public static void configureWithAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
}
