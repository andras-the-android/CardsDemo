package hu.andras.cardsdemo.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"score"})
public class Score {

    String name;
    int score;
}
