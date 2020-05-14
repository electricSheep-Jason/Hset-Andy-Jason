package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestClass {

    //simple one open database and close
    @Test
    void ConnectTest1() {
        InsertAndLoad app = new InsertAndLoad();
        Connection con = app.connect();
        assertNotNull(con);
    }

    @Test
    void InsertTest1() {
        InsertAndLoad app = new InsertAndLoad();
        ObservableList<HighestScoreRecords> data = FXCollections.observableArrayList();
        ;
        app.LoadRecords(data);
        HighestScoreRecords NewRecord = new HighestScoreRecords(7);
        data.add(NewRecord);
        data = app.SortList(data);
        assertEquals(7, data.get(data.size() - 1).getPlayerScore());
    }

    @Test
    void InsertTest2() {
        InsertAndLoad app = new InsertAndLoad();
        ObservableList<HighestScoreRecords> data = FXCollections.observableArrayList();
        ;
        app.LoadRecords(data);
        app.DeleteRecords(data);
        HighestScoreRecords NewRecord = new HighestScoreRecords(7);
        data.add(NewRecord);
        data = app.SortList(data);
        app.InsertRecords(data);
        ObservableList<HighestScoreRecords> data1 = FXCollections.observableArrayList();
        app.LoadRecords(data1);
        for (int i = 0; i < data.size(); i++) {
            assertEquals(data.get(i).getPlayerScore(), data1.get(i).getPlayerScore());
        }
    }
}

