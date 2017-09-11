package hu.andras.cardsdemo.repository;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import hu.andras.cardsdemo.data.Score;
import hu.andras.cardsdemo.persitency.HighScoreDao;


public class HighScoreRepository {

    private static final String TAG = "HighScoreRepository";
    private static final int HIGH_SCORE_SIZE = 10;

    private HighScoreDao dao;
    private TreeSet<Score> highScores;

    public HighScoreRepository(HighScoreDao dao) {
        this.dao = dao;
        highScores = new TreeSet<>(dao.load());
    }

    public List<Score> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(highScores));
    }

    public int getRank(int score) {
        int rank = 1;
        Iterator<Score> iterator = highScores.iterator();
        while (iterator.hasNext() && score <= iterator.next().getScore()) {
            ++rank;
        }
        return rank <= HIGH_SCORE_SIZE ? rank : -1;
    }

    public void addHighScore(Score score) {
        if (highScores.size() <= HIGH_SCORE_SIZE) {
            highScores.add(score);
            if (highScores.size() > HIGH_SCORE_SIZE) {
                highScores.remove(highScores.last());
            }
            dao.save(highScores);
        } else {
            Log.w(TAG, "Illegal attempt to add to high scores");
        }
    }
}
