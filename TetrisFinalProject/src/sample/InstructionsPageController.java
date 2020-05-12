package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

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
}
