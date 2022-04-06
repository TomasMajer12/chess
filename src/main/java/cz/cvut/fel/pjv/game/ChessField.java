package cz.cvut.fel.pjv.game;

public class ChessField {
    private int x,y;
    private ChessBoard board;

    public ChessField(ChessBoard board,int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
    }
}
