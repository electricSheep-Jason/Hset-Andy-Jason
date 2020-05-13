package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Tetris extends Application implements Initializable {
    public static final int move = 25;
    public static final int size = 25;
    public static final int X_max = size * 12;
    public static final int Y_max = size * 24;
    public static int[][] placeholder = new int[X_max / size][Y_max / size];
    @FXML
    public Pane holder;
    @FXML
    public Button ButtonBack;
    @FXML
    public Text Score;
    @FXML
    public Text Level;
    public Form a;
    public Timeline timeline;
    public int score;
    public boolean game = true;
    public int level = 1;
    public int RowsRemoved = 0;
    public int FallingSpeed = 500;
    Text over;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        placeholder = new int[X_max / size][Y_max / size];
        Level.setText("Level: " + level);
        a = Controller.MakeForm();
        for (int[] a : placeholder) {
            Arrays.fill(a, 0);
        }
        holder.getChildren().addAll(a.a, a.b, a.c, a.d);
        ButtonBack.setVisible(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public void moveOnKeyPress(KeyEvent event) {
        if (game) {
            if (event.getCode() == KeyCode.LEFT) {
                Controller.MoveLeft(a);
            } else if (event.getCode() == KeyCode.RIGHT) {
                Controller.MoveRight(a);
            } else if (event.getCode() == KeyCode.DOWN) {
                MoveDown(a);
            } else if (event.getCode() == KeyCode.UP) {
                Rotate(a);
            }
        }
    }

    public void MoveLeft(Rectangle rectangle) {
        if (rectangle.getX() - move >= 0) {
            rectangle.setX(rectangle.getX() - move);
        }
    }

    public void MoveRight(Rectangle rectangle) {
        if (rectangle.getX() + move <= X_max) {
            rectangle.setX(rectangle.getX() + move);
        }
    }

    public void MoveUp(Rectangle rectangle) {
        if (rectangle.getY() - move >= 0) {
            rectangle.setY(rectangle.getY() - move);
        }
    }

    public void MoveDown(Rectangle rectangle) {
        if (rectangle.getY() + move <= Y_max) {
            rectangle.setY(rectangle.getY() + move);
        }
    }

    public void MoveDown(Form form) {
        if (form.a.getY() == Y_max - size || form.b.getY() == Y_max - size
                || form.c.getY() == Y_max - size || form.d.getY() == Y_max - size
                || CheckLegalMoveDown(form)) {
            placeholder[(int) (form.a.getX() / size)][(int) (form.a.getY() / size)] = 1;
            placeholder[(int) (form.b.getX() / size)][(int) (form.b.getY() / size)] = 1;
            placeholder[(int) (form.c.getX() / size)][(int) (form.c.getY() / size)] = 1;
            placeholder[(int) (form.d.getX() / size)][(int) (form.d.getY() / size)] = 1;
            RemoveRows(holder);
            a = Controller.MakeForm();
            if (a == null) {
                GameOver();
            } else {
                holder.getChildren().addAll(a.a, a.b, a.c, a.d);
            }

        }

        if (form.a.getY() + move < Y_max && form.b.getY() + move < Y_max && form.c.getY() + move < Y_max && form.d.getY() + move < Y_max) {
            int movea = placeholder[(int) (form.a.getX() / size)][(int) ((form.a.getY() / size) + 1)];
            int moveb = placeholder[(int) (form.b.getX() / size)][(int) ((form.b.getY() / size) + 1)];
            int movec = placeholder[(int) (form.c.getX() / size)][(int) ((form.c.getY() / size) + 1)];
            int moved = placeholder[(int) (form.d.getX() / size)][(int) ((form.d.getY() / size) + 1)];
            if (movea == 0 && moveb == 0 && movec == 0 && moved == 0) {
                form.a.setY(form.a.getY() + move);
                form.b.setY(form.b.getY() + move);
                form.c.setY(form.c.getY() + move);
                form.d.setY(form.d.getY() + move);
            }
        }
    }

    public void Rotate(Form form) {
        String name = form.name;
        int status = form.status;
        if (name.equals("I")) {
            if (status == 1 && CheckLegalRotate(form.a, 2, 2)
                    && CheckLegalRotate(form.b, 1, 1)
                    && CheckLegalRotate(form.d, -1, -1)) {
                MoveUp(form.a);
                MoveUp(form.a);
                MoveRight(form.a);
                MoveRight(form.a);
                MoveUp(form.b);
                MoveRight(form.b);
                MoveDown(form.d);
                MoveLeft(form.d);
                form.ChangeStatus();
            }
            if (status == 2 && CheckLegalRotate(form.a, -2, -2)
                    && CheckLegalRotate(form.b, -1, -1)
                    && CheckLegalRotate(form.d, 1, 1)) {
                MoveDown(form.a);
                MoveDown(form.a);
                MoveLeft(form.a);
                MoveLeft(form.a);
                MoveDown(form.b);
                MoveLeft(form.b);
                MoveUp(form.d);
                MoveRight(form.d);
                form.ChangeStatus();
            }
            if (status == 3 && CheckLegalRotate(form.a, 2, 2)
                    && CheckLegalRotate(form.b, 1, 1)
                    && CheckLegalRotate(form.d, -1, -1)) {
                MoveUp(form.a);
                MoveUp(form.a);
                MoveRight(form.a);
                MoveRight(form.a);
                MoveUp(form.b);
                MoveRight(form.b);
                MoveDown(form.d);
                MoveLeft(form.d);
                form.ChangeStatus();
            }
            if (status == 4 && CheckLegalRotate(form.a, -2, -2)
                    && CheckLegalRotate(form.b, -1, -1)
                    && CheckLegalRotate(form.d, 1, 1)) {
                MoveDown(form.a);
                MoveDown(form.a);
                MoveLeft(form.a);
                MoveLeft(form.a);
                MoveDown(form.b);
                MoveLeft(form.b);
                MoveUp(form.d);
                MoveRight(form.d);
                form.ChangeStatus();
            }
        } else if (name.equals("O")) {

        } else if (name.equals("T")) {
            if (status == 1 && CheckLegalRotate(form.a, 1, 1)
                    && CheckLegalRotate(form.c, -1, 1)
                    && CheckLegalRotate(form.d, -1, -1)) {
                MoveUp(form.a);
                MoveRight(form.a);
                MoveLeft(form.c);
                MoveUp(form.c);
                MoveDown(form.d);
                MoveLeft(form.d);
                form.ChangeStatus();
            }
            if (status == 2 && CheckLegalRotate(form.a, 1, -1)
                    && CheckLegalRotate(form.c, 1, 1)
                    && CheckLegalRotate(form.d, -1, 1)) {
                MoveRight(form.a);
                MoveDown(form.a);
                MoveUp(form.c);
                MoveRight(form.c);
                MoveLeft(form.d);
                MoveUp(form.d);
                form.ChangeStatus();
            }
            if (status == 3 && CheckLegalRotate(form.a, -1, -1)
                    && CheckLegalRotate(form.c, 1, -1)
                    && CheckLegalRotate(form.d, 1, 1)) {
                MoveDown(form.a);
                MoveLeft(form.a);
                MoveRight(form.c);
                MoveDown(form.c);
                MoveUp(form.d);
                MoveRight(form.d);
                form.ChangeStatus();
            }
            if (status == 4 && CheckLegalRotate(form.a, -1, 1)
                    && CheckLegalRotate(form.c, -1, -1)
                    && CheckLegalRotate(form.d, 1, -1)) {
                MoveLeft(form.a);
                MoveUp(form.a);
                MoveDown(form.c);
                MoveLeft(form.c);
                MoveRight(form.d);
                MoveDown(form.d);
                form.ChangeStatus();
            }
        } else if (name.equals("J")) {
            if (status == 1 && CheckLegalRotate(form.a, 1, -1)
                    && CheckLegalRotate(form.c, -1, -1)
                    && CheckLegalRotate(form.d, -2, -2)) {
                MoveRight(form.a);
                MoveDown(form.a);
                MoveLeft(form.c);
                MoveDown(form.c);
                MoveDown(form.d);
                MoveDown(form.d);
                MoveLeft(form.d);
                MoveLeft(form.d);
                form.ChangeStatus();
            }
            if (status == 2 && CheckLegalRotate(form.a, -1, -1)
                    && CheckLegalRotate(form.c, -1, 1)
                    && CheckLegalRotate(form.d, -2, 2)) {
                MoveDown(form.a);
                MoveLeft(form.a);
                MoveLeft(form.c);
                MoveUp(form.c);
                MoveUp(form.d);
                MoveUp(form.d);
                MoveLeft(form.d);
                MoveLeft(form.d);
                form.ChangeStatus();
            }
            if (status == 3 && CheckLegalRotate(form.a, -1, 1)
                    && CheckLegalRotate(form.c, 1, 1)
                    && CheckLegalRotate(form.d, 2, 2)) {
                MoveUp(form.a);
                MoveLeft(form.a);
                MoveRight(form.c);
                MoveUp(form.c);
                MoveUp(form.d);
                MoveUp(form.d);
                MoveRight(form.d);
                MoveRight(form.d);
                form.ChangeStatus();
            }
            if (status == 4 && CheckLegalRotate(form.a, 1, 1)
                    && CheckLegalRotate(form.c, 1, -1)
                    && CheckLegalRotate(form.d, 2, -2)) {
                MoveUp(form.a);
                MoveRight(form.a);
                MoveRight(form.c);
                MoveDown(form.c);
                MoveDown(form.d);
                MoveDown(form.d);
                MoveRight(form.d);
                MoveRight(form.d);
                form.ChangeStatus();
            }

        } else if (name.equals("L")) {
            if (status == 1 && CheckLegalRotate(form.a, 1, -1)
                    && CheckLegalRotate(form.c, 1, 1)
                    && CheckLegalRotate(form.d, 2, 2)) {
                MoveRight(form.a);
                MoveDown(form.a);
                MoveUp(form.c);
                MoveRight(form.c);
                MoveUp(form.d);
                MoveUp(form.d);
                MoveRight(form.d);
                MoveRight(form.d);
                form.ChangeStatus();
            }
            if (status == 2 && CheckLegalRotate(form.a, -1, -1)
                    && CheckLegalRotate(form.c, 1, -1)
                    && CheckLegalRotate(form.d, 2, -2)) {
                MoveLeft(form.a);
                MoveDown(form.a);
                MoveDown(form.c);
                MoveRight(form.c);
                MoveDown(form.d);
                MoveDown(form.d);
                MoveRight(form.d);
                MoveRight(form.d);
                form.ChangeStatus();
            }
            if (status == 3 && CheckLegalRotate(form.a, -1, 1)
                    && CheckLegalRotate(form.c, -1, -1)
                    && CheckLegalRotate(form.d, -2, -2)) {
                MoveLeft(form.a);
                MoveUp(form.a);
                MoveDown(form.c);
                MoveLeft(form.c);
                MoveDown(form.d);
                MoveDown(form.d);
                MoveLeft(form.d);
                MoveLeft(form.d);
                form.ChangeStatus();
            }
            if (status == 4 && CheckLegalRotate(form.a, 1, 1)
                    && CheckLegalRotate(form.c, -1, 1)
                    && CheckLegalRotate(form.d, -2, 2)) {
                MoveRight(form.a);
                MoveUp(form.a);
                MoveUp(form.c);
                MoveLeft(form.c);
                MoveUp(form.d);
                MoveUp(form.d);
                MoveLeft(form.d);
                MoveLeft(form.d);
                form.ChangeStatus();
            }

        } else if (name.equals("S")) {
            if (status == 1 && CheckLegalRotate(form.a, -1, -1)
                    && CheckLegalRotate(form.c, -1, 1)
                    && CheckLegalRotate(form.d, 0, 2)) {
                MoveDown(form.a);
                MoveLeft(form.a);
                MoveLeft(form.c);
                MoveUp(form.c);
                MoveUp(form.d);
                MoveUp(form.d);
                form.ChangeStatus();
            }
            if (status == 2 && CheckLegalRotate(form.a, 1, 1)
                    && CheckLegalRotate(form.c, 1, -1)
                    && CheckLegalRotate(form.d, 0, -2)) {
                MoveUp(form.a);
                MoveRight(form.a);
                MoveRight(form.c);
                MoveDown(form.c);
                MoveDown(form.d);
                MoveDown(form.d);
                form.ChangeStatus();
            }
            if (status == 3 && CheckLegalRotate(form.a, -1, -1)
                    && CheckLegalRotate(form.c, -1, 1)
                    && CheckLegalRotate(form.d, 0, 2)) {
                MoveDown(form.a);
                MoveLeft(form.a);
                MoveLeft(form.c);
                MoveUp(form.c);
                MoveUp(form.d);
                MoveUp(form.d);
                form.ChangeStatus();
            }
            if (status == 4 && CheckLegalRotate(form.a, 1, 1)
                    && CheckLegalRotate(form.c, 1, -1)
                    && CheckLegalRotate(form.d, 0, -2)) {
                MoveUp(form.a);
                MoveRight(form.a);
                MoveRight(form.c);
                MoveDown(form.c);
                MoveDown(form.d);
                MoveDown(form.d);
                form.ChangeStatus();
            }

        } else if (name.equals("Z")) {
            if (status == 1 && CheckLegalRotate(form.a, 1, 1)
                    && CheckLegalRotate(form.c, -1, 1)
                    && CheckLegalRotate(form.d, -2, 0)) {
                MoveUp(form.a);
                MoveRight(form.a);
                MoveLeft(form.c);
                MoveUp(form.c);
                MoveLeft(form.d);
                MoveLeft(form.d);
                form.ChangeStatus();
            }
            if (status == 2 && CheckLegalRotate(form.a, -1, -1)
                    && CheckLegalRotate(form.c, 1, -1)
                    && CheckLegalRotate(form.d, 2, 0)) {
                MoveDown(form.a);
                MoveLeft(form.a);
                MoveRight(form.c);
                MoveDown(form.c);
                MoveRight(form.d);
                MoveRight(form.d);
                form.ChangeStatus();
            }
            if (status == 3 && CheckLegalRotate(form.a, 1, 1)
                    && CheckLegalRotate(form.c, -1, 1)
                    && CheckLegalRotate(form.d, -2, 0)) {
                MoveUp(form.a);
                MoveRight(form.a);
                MoveLeft(form.c);
                MoveUp(form.c);
                MoveLeft(form.d);
                MoveLeft(form.d);
                form.ChangeStatus();
            }
            if (status == 4 && CheckLegalRotate(form.a, -1, -1)
                    && CheckLegalRotate(form.c, 1, -1)
                    && CheckLegalRotate(form.d, 2, 0)) {
                MoveDown(form.a);
                MoveLeft(form.a);
                MoveRight(form.c);
                MoveDown(form.c);
                MoveRight(form.d);
                MoveRight(form.d);
                form.ChangeStatus();
            }

        }
    }

    public void RemoveRows(Pane pane) {
        ArrayList<Node> Rectangles = new ArrayList<Node>();
        ArrayList<Integer> Rows = new ArrayList<Integer>();
        ArrayList<Node> NewRectangles = new ArrayList<Node>();
        int CheckIfFilled = 0;
        for (int i = 0; i < placeholder[0].length; i++) {
            for (int j = 0; j < placeholder.length; j++) {
                if (placeholder[j][i] == 1) {
                    CheckIfFilled++;
                }
            }
            if (CheckIfFilled == placeholder.length) {
                Rows.add(i + Rows.size());
            }
            CheckIfFilled = 0;

        }

        if (Rows.size() > 0) {
            while (Rows.size() > 0) {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        Rectangles.add(node);
                }
                score += 50;
                UpdateScore(score);
                for (Node node : Rectangles) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() / size == Rows.get(0)) {
                        placeholder[(int) a.getX() / size][(int) a.getY() / size] = 0;
                        pane.getChildren().remove(node);
                    } else
                        NewRectangles.add(node);
                }

                for (Node node : NewRectangles) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY()/size < Rows.get(0)) {
                        placeholder[(int) a.getX() / size][(int) a.getY() / size] = 0;
                        a.setY(a.getY() + size);
                    }
                }
                Rows.remove(0);
                Rectangles.clear();
                NewRectangles.clear();
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        Rectangles.add(node);
                }
                for (Node node : Rectangles) {
                    Rectangle a = (Rectangle) node;
                    placeholder[(int) a.getX() / size][(int) a.getY() / size] = 1;

                }
                Rectangles.clear();
                RowsRemoved += 1;
                if (RowsRemoved == 2) {
                    LevelUp();
                }
            }
        }
    }

    public boolean CheckLegalRotate(Rectangle rect, int XMove, int YMove) {
        Boolean LegalXMove = false;
        Boolean LegalYMove = false;

        if (XMove >= 0) {
            LegalXMove = rect.getX() + XMove * move <= X_max - size;
        }

        if (XMove < 0) {
            LegalXMove = rect.getX() + XMove * move >= 0;
        }

        if (YMove >= 0) {
            LegalYMove = rect.getY() - YMove * move > 0;
        }

        if (YMove < 0) {
            LegalYMove = rect.getY() - YMove * move < Y_max;
        }

        return LegalXMove && LegalYMove && placeholder[(int) rect.getX() / size + XMove][(int) rect.getY() / size - YMove] == 0;
    }

    public boolean CheckLegalMoveDown(Form form) {
        boolean CheckRectA = placeholder[(int) form.a.getX() / size][(int) form.a.getY() / size + 1] == 1;
        boolean CheckRectB = placeholder[(int) form.b.getX() / size][(int) form.b.getY() / size + 1] == 1;
        boolean CheckRectC = placeholder[(int) form.c.getX() / size][(int) form.c.getY() / size + 1] == 1;
        boolean CheckRectD = placeholder[(int) form.d.getX() / size][(int) form.d.getY() / size + 1] == 1;
        return CheckRectA || CheckRectB || CheckRectC || CheckRectD;
    }

    public void UpdateScore(int NewScore) {
        Score.setText("Score: " + NewScore);
    }

    public void LevelUp() {
        level = level + 1;
        timeline.stop();
        IncreaseFallingSpeed();
        Level.setText("Level: " + level);
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(FallingSpeed),
                        ae -> MoveDown(a)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void GameOver() {
        timeline.stop();
        over = new Text("GAME OVER");
        over.setFill(Color.RED);
        over.setStyle("-fx-font: 70 arial;");
        over.setY(250);
        over.setX(10);
        holder.getChildren().add(over);
        game = false;
        ButtonBack.setVisible(true);
    }

    public void IncreaseFallingSpeed() {
        FallingSpeed -= 100;
    }

    @FXML
    public void Start() {
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(FallingSpeed),
                        ae -> MoveDown(a)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void Pause() {
        if (timeline != null) {
            timeline.pause();
            ButtonBack.setVisible(true);
        }
    }

    @FXML
    public void BackToMenu(ActionEvent actionEvent) throws IOException {
        placeholder = new int[X_max / size][Y_max / size];
        holder.getChildren().removeIf(node -> node instanceof Rectangle);
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}
