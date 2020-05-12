package sample;

import javafx.collections.ObservableList;

import java.sql.*;

public class LoadDataFromDatabase {
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
}
