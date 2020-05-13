package sample;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Form {
    public Rectangle a;
    public Rectangle b;
    public Rectangle c;
    public Rectangle d;
    public String name;
    public Color color;
    public int status = 1;

    public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name=name;

        if (name == "I") {
            color = Color.BLUE;
        } else if (name == "O") {
            color = Color.YELLOW;
        } else if (name == "T") {
            color = Color.PURPLE;
        } else if (name == "J") {
            color = Color.DARKBLUE;
        } else if (name == "L") {
            color = Color.ORANGE;
        } else if (name == "S") {
            color = Color.GREEN;
        } else if (name == "Z") {
            color = Color.RED;
        }

        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }

    public void ChangeStatus() {
        if (this.status < 4) {
            this.status += 1;
        } else {
            this.status = 1;
        }
    }
}

