package hu.andras.cardsdemo.persitency;


import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Set;

import hu.andras.cardsdemo.data.Score;

public class HighScoreDao {

    private static final String KEY_HIGHSCORES = "highScores";

    private SharedPreferences preferences;

    public HighScoreDao(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void save(Set<Score> highScores) {
        String jsonString = new Gson().toJson(highScores);
        preferences.edit().putString(KEY_HIGHSCORES, jsonString).apply();
    }

    public Set<Score> load() {
        String jsonString = preferences.getString(KEY_HIGHSCORES, "[]");
        Type type = new TypeToken<Set<Score>>() {}.getType();
        return new Gson().fromJson(jsonString, type);
    }
}
