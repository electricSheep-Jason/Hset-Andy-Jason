package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HighestScoresPageController implements Initializable {
    @FXML
    TableView TableHighestScores;
    @FXML
    TableColumn<HighestScoreRecords, String> ColumnPlayer;
    @FXML
    TableColumn<HighestScoreRecords, Integer> ColumnScore;
    private ObservableList<HighestScoreRecords> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = FXCollections.observableArrayList();
        LoadDataFromDatabase loader = new LoadDataFromDatabase();
        loader.LoadRecords(data);
        ColumnPlayer.setCellValueFactory(new PropertyValueFactory<>("PlayerName"));
        ColumnScore.setCellValueFactory(new PropertyValueFactory<>("PlayerScore"));
        TableHighestScores.setItems(data);

    }

    @FXML
    public void BackToMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
