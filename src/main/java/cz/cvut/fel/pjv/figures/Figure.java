package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class Figure {

    int x, y;
    Color color;
    private String name;
    private String imageStream;
    public ChessField field;

    public Figure(Color color, String name, ChessField field) {
        this.color = color;
        this.name = name;
        this.field = field;
        if (color == Color.BLACK){
            imageStream ="/images/figures/black_" + name + ".png";
        }else {
            imageStream = "/images/figures/white_" + name + ".png";
        }
    }

    public String getImageStream() {
        return imageStream;
    }

    public List<ChessField> getAccessibleFields(){
        List<ChessField> fields= AccessibleFields();
        return fields;
    }


    public abstract List<ChessField> AccessibleFields();

}
