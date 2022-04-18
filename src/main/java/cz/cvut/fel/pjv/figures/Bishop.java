package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Figure{
    public Bishop(Color color, String name, ChessField field) {
        super(color, name, field);
    }

    @Override
    public List<ChessField> AccessibleFields() {
        List<ChessField> fields = new ArrayList<>();

        for (int i = 1; x + i < 8 && y + i < 8; i++){
            if (addField(x + i, y + i, fields) == false)break;
        }

        for (int i = 1; x + i < 8 && y - i >= 0; i++){
            if (addField(x + i, y - i, fields) == false)break;
        }

        for (int i = 1; x - i >= 0 && y + i < 8; i++){
            if (addField(x - i, y + i, fields) == false)break;
        }

        for (int i = 1; x - i >= 0 && y - i >= 0; i++){
            if (addField(x - i, y - i, fields) == false)break;
        }

        return fields;
    }
}
