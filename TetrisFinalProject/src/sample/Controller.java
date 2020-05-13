package sample;

import javafx.scene.shape.Rectangle;

public class Controller {
    public static final int move = Tetris.move;
    public static final int size = Tetris.size;
    public static final int X_max = Tetris.X_max;
    public static final int Y_max = Tetris.Y_max;
    public static int[][] placeholder = Tetris.placeholder;

    public static void MoveLeft(Form form) {
        if (form.a.getX() - move >= 0 && form.b.getX() - move >= 0 && form.c.getX() - move >= 0 && form.d.getX() - move >= 0) {
            int movea = placeholder[(int) ((form.a.getX() / size) - 1)][(int) (form.a.getY() / size)];
            int moveb = placeholder[(int) ((form.b.getX() / size) - 1)][(int) (form.b.getY() / size)];
            int movec = placeholder[(int) ((form.c.getX() / size) - 1)][(int) (form.c.getY() / size)];
            int moved = placeholder[(int) ((form.d.getX() / size) - 1)][(int) (form.d.getY() / size)];
            if (movea == 0 && moveb == 0 && movec == 0 && moved == 0) {
                form.a.setX(form.a.getX() - move);
                form.b.setX(form.b.getX() - move);
                form.c.setX(form.c.getX() - move);
                form.d.setX(form.d.getX() - move);
            }
        }
    }

    public static void MoveRight(Form form) {
        if (form.a.getX() + move <= X_max - size && form.b.getX() + move <= X_max - size
                && form.c.getX() + move <= X_max - size && form.d.getX() + move <= X_max - size) {
            int movea = placeholder[(int) ((form.a.getX() / size) + 1)][(int) (form.a.getY() / size)];
            int moveb = placeholder[(int) ((form.b.getX() / size) + 1)][(int) (form.b.getY() / size)];
            int movec = placeholder[(int) ((form.c.getX() / size) + 1)][(int) (form.c.getY() / size)];
            int moved = placeholder[(int) ((form.d.getX() / size) + 1)][(int) (form.d.getY() / size)];
            if (movea == 0 && moveb == 0 && movec == 0 && moved == 0) {
                form.a.setX(form.a.getX() + move);
                form.b.setX(form.b.getX() + move);
                form.c.setX(form.c.getX() + move);
                form.d.setX(form.d.getX() + move);
            }
        }
    }

    public static Form MakeForm() {
        int DecideForm = (int) (Math.random() * 100);
        if (!Checker(DecideForm)) {
            return null;
        }
        String name;
        Rectangle a = new Rectangle(size - 1, size - 1);
        Rectangle b = new Rectangle(size - 1, size - 1);
        Rectangle c = new Rectangle(size - 1, size - 1);
        Rectangle d = new Rectangle(size - 1, size - 1);
        if (DecideForm < 15) {
            a.setX(X_max / 2 - size);
            b.setX(X_max / 2);
            c.setX(X_max / 2 + size);
            d.setX(X_max / 2 + 2 * size);
            name = "I";
        } else if (DecideForm < 30) {
            a.setX(X_max / 2);
            b.setX(X_max / 2);
            b.setY(size);
            c.setX(X_max / 2 + size);
            d.setX(X_max / 2 + size);
            d.setY(size);
            name = "O";
        } else if (DecideForm < 45) {
            a.setX(X_max / 2 - size);
            b.setX(X_max / 2);
            c.setX(X_max / 2);
            c.setY(size);
            d.setX(X_max / 2 + size);
            name = "T";
        } else if (DecideForm < 60) {
            a.setX(X_max / 2 - size);
            b.setX(X_max / 2 - size);
            b.setY(size);
            c.setX(X_max / 2);
            c.setY(size);
            d.setX(X_max / 2 + size);
            d.setY(size);
            name = "J";
        } else if (DecideForm < 75) {
            a.setX(X_max / 2 + size);
            b.setX(X_max / 2 + size);
            b.setY(size);
            c.setX(X_max / 2);
            c.setY(size);
            d.setX(X_max / 2 - size);
            d.setY(size);
            name = "L";
        } else if (DecideForm < 90) {
            a.setX(X_max / 2 + size);
            b.setX(X_max / 2);
            c.setX(X_max / 2);
            c.setY(size);
            d.setX(X_max / 2 - size);
            d.setY(size);
            name = "S";
        } else {
            a.setX(X_max / 2 - size);
            b.setX(X_max / 2);
            c.setX(X_max / 2);
            c.setY(size);
            d.setX(X_max / 2 + size);
            d.setY(size);
            name = "Z";
        }

        return new Form(a, b, c, d, name);
    }

    public static boolean Checker(int FormNumber) {
        boolean result = true;
        if (FormNumber < 15) {
            if (placeholder[(X_max / (2 * size)) - 1][0] == 1
                    || placeholder[X_max / (2 * size)][0] == 1
                    || placeholder[(X_max / (2 * size)) + 1][0] == 1
                    || placeholder[(X_max / (2 * size)) + 2][0] == 1) {
                result = false;
            }
        } else if (FormNumber < 30) {
            if (placeholder[X_max / (2 * size)][0] == 1
                    || placeholder[X_max / (2 * size)][1] == 1
                    || placeholder[(X_max / (2 * size)) + 1][0] == 1
                    || placeholder[(X_max / (2 * size)) + 1][1] == 1) {
                result = false;
            }
        } else if (FormNumber < 45) {
            if (placeholder[(X_max / (2 * size)) - 1][0] == 1
                    || placeholder[X_max / (2 * size)][0] == 1
                    || placeholder[X_max / (2 * size)][1] == 1
                    || placeholder[(X_max / (2 * size)) + 1][0] == 1) {
                result = false;
            }
        } else if (FormNumber < 60) {
            if (placeholder[(X_max / (2 * size)) - 1][0] == 1
                    || placeholder[(X_max / (2 * size)) - 1][1] == 1
                    || placeholder[X_max / (2 * size)][1] == 1
                    || placeholder[(X_max / (2 * size)) + 1][1] == 1) {
                result = false;
            }
        } else if (FormNumber < 75) {
            if (placeholder[(X_max / (2 * size)) + 1][0] == 1
                    || placeholder[(X_max / (2 * size)) + 1][1] == 1
                    || placeholder[X_max / (2 * size)][1] == 1
                    || placeholder[(X_max / (2 * size)) - 1][1] == 1) {
                result = false;
            }
        } else if (FormNumber < 90) {
            if (placeholder[(X_max / (2 * size)) + 1][0] == 1
                    || placeholder[X_max / (2 * size)][0] == 1
                    || placeholder[X_max / (2 * size)][1] == 1
                    || placeholder[(X_max / (2 * size)) - 1][1] == 1) {
                result = false;
            }
        } else {
            if (placeholder[(X_max / (2 * size)) - 1][0] == 1
                    || placeholder[X_max / (2 * size)][0] == 1
                    || placeholder[X_max / (2 * size)][1] == 1
                    || placeholder[(X_max / (2 * size)) + 1][1] == 1) {
                result = false;
            }
        }

        return result;
    }
}
