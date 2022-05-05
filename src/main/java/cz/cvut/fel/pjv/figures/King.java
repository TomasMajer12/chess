package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class King extends Figure{
    public King(String color, String name, ChessField field) {
        super(color, name, field);
    }

    @Override
    public List<ChessField> AccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        ChessField field;
        for (int i = -1; i <= 1; i++ ){
            for (int j = -1; j <=1; j++){
                if(((field = this.field.getBoard().getField(x+i,y+j)) != null) && (field.getFigure() == null
                    || (field.getFigure() != null && field.getFigure().color != this.color)) ){
                    fields.add(field);
                }
            }
        }
        return fields;
    }
}
