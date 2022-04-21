package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Figure{
    public Rook(String color, String name, ChessField field) {
        super(color, name, field);
    }

    @Override
    public List<ChessField> AccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        for (int i = 1; y+i < 8; i++){
            if(addField(x, y + i, fields))break;
        }
        for (int i = 1; y-i >=0; i++){
            if(addField(x, y - i, fields))break;
        }
        for (int i = 1; x+i < 8; i++){
            if(addField(x + i, y, fields))break;
        }
        for (int i = 1; x-i >=0; i++){
            if(addField(x - i, y, fields))break;
        }
        return fields;
    }
}
