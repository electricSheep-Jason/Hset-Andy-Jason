package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InstructionsPageController implements Initializable {
    @FXML
    TextArea Instructions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Instructions.setText("Left arrow key: move block to the left\n" + "\n" +
                "Right arrow key: move block to the right\n" + "\n" +
                "Down arrow key: move block down\n" + "\n" +
                "Up arrow key: rotate block clockwise");
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
