package cz.cvut.fel.pjv.game;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChessBoard extends GridPane {

    private ChessField[] fields = new ChessField[64];
    private Map<Color, Set<ChessField>> attackedFields = new HashMap<>();

    public ChessBoard() {
        resetAttackedFields();
        for (int i = 0; i < 64; i++) {
            int x = getX(i);
            int y = getY(i);
            ChessField field = new ChessField(this, x, y);
            add(field, x, y);
            fields[i] = field;
        }
    }

    private void resetAttackedFields() {
        attackedFields.put(Color.BLACK, new HashSet<>());
        attackedFields.put(Color.WHITE, new HashSet<>());
    }


    private int getX(int index) {
        return index % 8;
    }

    private int getY(int index) {
        return (index - getX(index)) / 8;
    }
}
