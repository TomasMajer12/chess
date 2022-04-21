package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import javafx.scene.input.DataFormat;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.List;

public abstract class Figure implements Serializable {
    public transient static final DataFormat CHESS_FIGURE = new DataFormat("chess.figure");
    public transient ChessField field; //--> not serializable

    int x, y;
    String color;
    private final String name;
    private final String imageStream;

    public Figure(String color, String name, ChessField field) {
        this.color = color;
        this.name = name;
        this.field = field;
        if (color == "black"){
            imageStream ="/images/figures/black_" + name + ".png";
        }else {
            imageStream = "/images/figures/white_" + name + ".png";
        }
        if (field != null){
            this.x = field.getX();
            this.y = field.getY();
        }
    }

    public String getName() {
        return name;
    }

    public String getImageStream() {
        return imageStream;
    }

    public boolean addField(int x, int y, List<ChessField> fields) {

        ChessField field = this.field.getBoard().getField(x, y);
        if (field == null){
            return true;
        }
        if (field.getFigure() == null){
            fields.add(field);
            return false;//we can continue
        }else if(field.getFigure().color != this.color){
            fields.add(field);
        }
        return true;//end
    }

    public List<ChessField> getAccessibleFields(){
        List<ChessField> fields= AccessibleFields();
        return fields;
    }


    public abstract List<ChessField> AccessibleFields();

}
