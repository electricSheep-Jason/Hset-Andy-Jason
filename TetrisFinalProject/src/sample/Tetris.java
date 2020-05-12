package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Tetris extends Application implements Initializable {
    public static final int move = 25;
    public static final int size = 25;
    public static final int X_max = size * 12;
    public static final int Y_max = size * 24;
    public static int[][] placeholder = new int[X_max / size][Y_max / size];
    @FXML
    public Pane holder;
    public static Form Tetriminos = Controller.MakeForm();
    public Form a;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        a=Tetriminos;
        holder.getChildren().addAll(a.a, a.b, a.c, a.d);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public void moveOnKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            Controller.MoveLeft(a);
        } else if (event.getCode() == KeyCode.RIGHT) {
            Controller.MoveRight(a);
        } else if (event.getCode() == KeyCode.DOWN) {
        } else if (event.getCode() == KeyCode.SPACE) {
            Rotate(a);
        }
    }

    @FXML
    public void ABC(){
        System.out.println("HELLO!!!");
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

    public void Rotate(Form form) {
        String name = form.name;
        int status = form.status;
        if (name.equals("I")) {
            if (status == 1) {
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
            if (status == 2) {
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
            if (status == 3) {
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
            if (status == 4) {
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
            if (status == 1) {
                MoveUp(form.a);
                MoveRight(form.a);
                MoveLeft(form.c);
                MoveUp(form.c);
                MoveDown(form.d);
                MoveLeft(form.d);
                form.ChangeStatus();
            }
            if (status == 2) {
                MoveRight(form.a);
                MoveDown(form.a);
                MoveUp(form.c);
                MoveRight(form.c);
                MoveLeft(form.d);
                MoveUp(form.d);
                form.ChangeStatus();
            }
            if (status == 3) {
                MoveDown(form.a);
                MoveLeft(form.a);
                MoveRight(form.c);
                MoveDown(form.c);
                MoveUp(form.d);
                MoveRight(form.d);
                form.ChangeStatus();
            }
            if (status == 4) {
                MoveLeft(form.a);
                MoveUp(form.a);
                MoveDown(form.c);
                MoveLeft(form.c);
                MoveRight(form.d);
                MoveDown(form.d);
                form.ChangeStatus();
            }
        } else if (name.equals("J")) {
            if (status == 1) {

            }
            if (status == 2) {

            }
            if (status == 3) {

            }
            if (status == 4) {

            }

        } else if (name.equals("L")) {
            if (status == 1) {

            }
            if (status == 2) {

            }
            if (status == 3) {

            }
            if (status == 4) {

            }

        } else if (name.equals("S")) {
            if (status == 1) {

            }
            if (status == 2) {

            }
            if (status == 3) {

            }
            if (status == 4) {

            }

        } else if (name.equals("Z")) {
            if (status == 1) {

            }
            if (status == 2) {

            }
            if (status == 3) {

            }
            if (status == 4) {

            }

        }
    }
}
