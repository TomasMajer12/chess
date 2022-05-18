package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;

import java.util.ArrayList;
import java.util.List;

/**
 * Rook class extending Figure abstract class
 */
public class Rook extends Figure{
    private String tag = "R";
    public Rook(String color, String name, ChessField field) {
        super(color, name, field);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public List<ChessField> AccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        for (int i = 1; y+i < 8; i++){
            if(addField(x, y + i, fields))break;//break if we find figure
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
