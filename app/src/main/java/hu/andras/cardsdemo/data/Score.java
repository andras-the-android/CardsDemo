package hu.andras.cardsdemo.data;

import android.support.annotation.NonNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Score implements Comparable<Score> {

    @Getter @Setter private String name;
    @Getter @Setter private int score;
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
}
