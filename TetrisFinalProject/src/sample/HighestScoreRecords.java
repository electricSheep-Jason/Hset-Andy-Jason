package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//showing scores on the table

public class HighestScoreRecords {
    private IntegerProperty PlayerScore;

    public HighestScoreRecords(int score) {
        this.PlayerScore = new SimpleIntegerProperty(score);
    }

    public int getPlayerScore() {
        return PlayerScore.get();
    }

    public void setPlayerScore(int score) {
        PlayerScore.set(score);
    }

    public IntegerProperty PlayerScoreProperty() { return PlayerScore;
    }
}
