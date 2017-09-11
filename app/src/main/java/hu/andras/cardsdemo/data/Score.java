package hu.andras.cardsdemo.data;

import android.support.annotation.NonNull;


public class Score implements Comparable<Score> {

    private transient int rank;
    private String name;
    private int score;
    private Long timestamp;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
        timestamp = System.currentTimeMillis();
    }

    @Override
    public int compareTo(@NonNull Score arg) {
        return arg.score != score ? arg.score - score : timestamp.compareTo(arg.timestamp);
    }

    public String getRankString() {
        return String.valueOf(rank);
    }

    public String getScoreString() {
        return String.valueOf(score);
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
