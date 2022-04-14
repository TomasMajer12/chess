package cz.cvut.fel.pjv.game;

import javafx.scene.control.Label;

public class ChessField extends Label{
    private int x,y;
    private ChessBoard board;

    public ChessField(ChessBoard board,int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
    }
}
