package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HighestScoreRecords {
    private StringProperty PlayerName;
    private IntegerProperty PlayerScore;

    public HighestScoreRecords(String name, int score) {
        this.PlayerName = new SimpleStringProperty(name);
        this.PlayerScore = new SimpleIntegerProperty(score);
    }

    public String getPlayerName() {
        return PlayerName.get();
    }

    public int getPlayerScore() {
        return PlayerScore.get();
    }

    public void setPlayerName(String name) {
        PlayerName.set(name);
    }

    public void setPlayerScore(int score) {
        PlayerScore.set(score);
    }

    public StringProperty PlayerNameProperty() {
        return PlayerName;
    }

    public IntegerProperty PlayerScoreProperty() {
        return PlayerScore;
    }
}
