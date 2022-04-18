package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.figures.Figure;
import cz.cvut.fel.pjv.gui.Utils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ChessField extends Label{
    private int x,y;
    private ChessBoard board;
    Figure figure;

    public ChessField(ChessBoard board,int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        if(getColor()){
            setStyle("-fx-background-color: white;-fx-border-style: solid");
        }else{
            setStyle("-fx-background-color: #595959;-fx-border-style: solid");
        }
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

    /*Image img = Utils.loadImage("/images/figures/black_bishop.png",45,45);
        ImageView view = new ImageView(img);
        setGraphic(view);*/

    public ChessBoard getBoard(){
        return board;
    }

    private boolean getColor(){
        return (x % 2 == 1 && y % 2 == 1) || x % 2 == 0 && y % 2 == 0 ? true : false;
    }
}
