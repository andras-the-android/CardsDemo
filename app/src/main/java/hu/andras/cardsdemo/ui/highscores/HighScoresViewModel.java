package hu.andras.cardsdemo.ui.highscores;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import hu.andras.cardsdemo.businesslogic.HighScoreRepository;
import hu.andras.cardsdemo.data.Score;
import hu.andras.cardsdemo.databinding.ListItemHighScoreBinding;

public class HighScoresViewModel {

    private List<Score> scores;
    private Adapter adapter;

    HighScoresViewModel(HighScoreRepository repository) {
        scores = repository.getAll();
        adapter = new Adapter();
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public boolean isHighScoreListEmpty() {
        return scores.isEmpty();
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(ListItemHighScoreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Score model = scores.get(position);
            model.setRank(position + 1);
            holder.setModel(model);
        }

        @Override
        public int getItemCount() {
            return scores.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private ListItemHighScoreBinding binding;

        private ViewHolder(ListItemHighScoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setModel(Score model) {
            binding.setModel(model);
        }
    }
}
