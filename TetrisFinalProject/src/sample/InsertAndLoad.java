package sample;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.sql.*;

//insert new score into database
//load the score from database

public class InsertAndLoad {
    public Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/sample/Tetris.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void InsertRecords(ObservableList<HighestScoreRecords> data) {
        String sql = "INSERT INTO HighestScores(Scores) VALUES(?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int i=0;
            for(HighestScoreRecords record:data){
                pstmt.setInt(1, record.getPlayerScore());
                pstmt.addBatch();
                i++;
                if(i==data.size()){
                    pstmt.executeBatch();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void DeleteRecords(ObservableList<HighestScoreRecords> data) {
        String sql = "DELETE FROM HighestScores WHERE Scores = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int i=0;
            for(HighestScoreRecords record:data){
                pstmt.setInt(1, record.getPlayerScore());
                pstmt.addBatch();
                i++;
                if(i==data.size()){
                    pstmt.executeBatch();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void LoadRecords(ObservableList<HighestScoreRecords> data) {
        String sql = "SELECT * FROM HighestScores";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                HighestScoreRecords record = new HighestScoreRecords(rs.getInt(1));
                data.add(record);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<HighestScoreRecords> SortList(ObservableList<HighestScoreRecords> data) {
        for (int i = 0; i < data.size(); i++) {
            int position = i;
            int Highest = data.get(i).getPlayerScore();
            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(j).getPlayerScore()>Highest) {
                    Highest = data.get(j).getPlayerScore();
                    position = j;
                }
            }
            Object k = data.get(i);
            data.set(i,data.get(position));
            data.set(position, (HighestScoreRecords) k);
        }
        return data;
    }

    public static void main(String[] args) {
        InsertAndLoad app = new InsertAndLoad();
        ObservableList<HighestScoreRecords> data = FXCollections.observableArrayList();
        app.LoadRecords(data);
        app.DeleteRecords(data);
        HighestScoreRecords newrecord =new HighestScoreRecords(8);
        HighestScoreRecords newrecord1 =new HighestScoreRecords(11);
        data.add(newrecord);
        data.add(newrecord1);
        data=app.SortList(data);
        app.InsertRecords(data);
    }
}
