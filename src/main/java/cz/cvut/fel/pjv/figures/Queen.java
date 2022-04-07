package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import javafx.scene.paint.Color;

import java.util.List;

public class Queen extends Figure{
    public Queen(Color color, String name, ChessField field) {
        super(color, name, field);
    }

    @Override
    public List<ChessField> AccessibleFields() {
        return null;
    }
}
