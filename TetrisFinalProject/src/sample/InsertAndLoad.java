package sample;

import javafx.collections.ObservableList;

import java.sql.*;

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

    public void InsertRecords(String name, int score) {
        String sql = "INSERT INTO HighestScores(Players, Scores) VALUES(?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
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
                HighestScoreRecords record = new HighestScoreRecords(rs.getString(1), rs.getInt(2));
                data.add(record);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void InsertHelper(String name, int score) {
        ObservableList<HighestScoreRecords> data = null;
        LoadRecords(data);
        for(int i=0;i<data.size();i++){
            if(score<data.get(i).getPlayerScore()){
                
            }
        }
    }
}
