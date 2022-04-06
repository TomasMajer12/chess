package cz.cvut.fel.pjv.game;

public class ChessBoard {

    private ChessField[] fields = new ChessField[64];

    public ChessBoard() {
        for (int i = 0; i < 64; i++) {
            int x = getX(i);
            int y = getY(i);
            ChessField field = new ChessField(this, x, y);
            fields[i] = field;
        }
    }

    private int getX(int index) {
        return index % 8;
    }

    private int getY(int index) {
        return (index - getX(index)) / 8;
    }
}
