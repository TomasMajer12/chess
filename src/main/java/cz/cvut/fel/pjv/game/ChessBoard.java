package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.figures.Figure;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChessBoard extends GridPane {

    private ChessField[] fields = new ChessField[64];
    public int Turn_counter;
    public ChessBoard() {
        for (int i = 0; i < 64; i++) {
            int x = getX(i);
            int y = getY(i);
            ChessField field = new ChessField(this, x, y);
            add(field, x, y);
            fields[i] = field;
        }
        Turn_counter = 0;

    }

    public ChessField getField(int x, int y) {
        return x < 0 || x > 7 || y < 0 || y > 7 ? null : fields[y * 8 + x];
    }

    public void next_turn(){
        Turn_counter++;
    }

    private int getX(int index) {
        return index % 8;
    }

    private int getY(int index) {
        return (index - getX(index)) / 8;
    }

    public List<Figure> getFigures(String color) {
        List<Figure> figures = new ArrayList<>();
        Arrays.stream(fields)
                .filter(f -> f.figure != null && (color == null || f.figure.getColor() == color))
                .forEach(f -> figures.add(f.figure));
        return figures;
    }

}
