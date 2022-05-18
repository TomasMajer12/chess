package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;

import java.util.ArrayList;
import java.util.List;

/**
 * Bishop class extending Figure abstract class
 */
public class Bishop extends Figure{
    private String tag = "B";
    public Bishop(String color, String name, ChessField field) {
        super(color, name, field);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public List<ChessField> AccessibleFields() {
        List<ChessField> fields = new ArrayList<>();

        for (int i = 1; x + i < 8 && y + i < 8; i++){
            if (addField(x + i, y + i, fields))break; //break if we found oponent or our figure
        }

        for (int i = 1; x + i < 8 && y - i >= 0; i++){
            if (addField(x + i, y - i, fields))break;
        }

        for (int i = 1; x - i >= 0 && y + i < 8; i++){
            if (addField(x - i, y + i, fields))break;
        }

        for (int i = 1; x - i >= 0 && y - i >= 0; i++){
            if (addField(x - i, y - i, fields))break;
        }

        return fields;
    }
}
