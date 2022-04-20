package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Figure{
    public Knight(Color color, String name, ChessField field) {
        super(color, name, field);
    }

    @Override
    public List<ChessField> AccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        addField(x - 2, y + 1, fields);
        addField(x - 2, y - 1, fields);
        addField(x - 1, y + 2, fields);
        addField(x - 1, y - 2, fields);
        addField(x + 2, y + 1, fields);
        addField(x + 2, y - 1, fields);
        addField(x + 1, y + 2, fields);
        addField(x + 1, y - 2, fields);
        return fields;
    }
}
