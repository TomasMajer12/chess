package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;

import java.util.ArrayList;
import java.util.List;

/**
 * Queen class extending Figure abstract class
 */
public class Queen extends Figure{
    public Queen(String color, String name, ChessField field) {
        super(color, name, field);
    }


    @Override
    public List<ChessField> AccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        //diagonals
        for (int i = 1; x + i < 8 && y + i < 8; i++){
            if (addField(x + i, y + i, fields))break; //break if we find figure
        }
        for (int i = 1; x + i < 8 && y - i >= 0; i++) {
            if (addField(x + i, y - i, fields))break;
        }
        for (int i = 1; x - i >= 0 && y + i < 8; i++){
            if (addField(x - i, y + i, fields))break;
        }
        for (int i = 1; x - i >= 0 && y - i >= 0; i++){
            if (addField(x - i, y - i, fields))break;
        }

        //other directions
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
