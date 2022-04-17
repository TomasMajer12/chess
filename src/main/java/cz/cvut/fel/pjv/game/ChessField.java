package cz.cvut.fel.pjv.game;

import javafx.scene.control.Label;

public class ChessField extends Label{
    private int x,y;
    private ChessBoard board;

    public ChessField(ChessBoard board,int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        if(getColor(x,y)){
            setStyle("-fx-background-color: white;-fx-border-style: solid");
        }else{
            setStyle("-fx-background-color: #595959;-fx-border-style: solid");
        }
        setMinSize(50, 50);
    }

    private boolean getColor(int x,int y){
        return (x % 2 == 1 && y % 2 == 1) || x % 2 == 0 && y % 2 == 0 ? true : false;
    }
}
