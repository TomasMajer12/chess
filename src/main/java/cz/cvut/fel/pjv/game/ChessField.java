package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.figures.Figure;
import cz.cvut.fel.pjv.gui.Utils;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ChessField extends Label{
    private int x,y;
    private ChessBoard board;
    Figure figure;

    public ChessField(ChessBoard board,int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        setDefaultColor();
        setMinSize(50, 50);
    }

    public void setFigure(Figure figure){
        this.figure = figure;
        if (figure == null){
            setGraphic(null);
        }else{
            setGraphic(new ImageView(Utils.loadImage(figure.getImageStream(), 45,45)));
        }
    }

    public ChessBoard getBoard(){
        return board;
    }
    
    private void setDefaultColor(){
        setStyle(getColor() ? "-fx-background-color: white;-fx-border-style: solid" : "-fx-background-color: #595959;-fx-border-style: solid");
    }

    private boolean getColor(){
        return (x % 2 == 1 && y % 2 == 1) || x % 2 == 0 && y % 2 == 0 ? true : false;
    }
}
