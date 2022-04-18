package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.figures.Figure;
import cz.cvut.fel.pjv.gui.Utils;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.List;

public class ChessField extends Label{
    private int x,y;
    private ChessBoard board;
    Figure figure = null;
    private String whiteDefault = "-fx-background-color: white;-fx-border-style: solid";
    private String blackDefault = "-fx-background-color: #595959;-fx-border-style: solid";

    public ChessField(ChessBoard board,int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        setDefaultColor();
        setOnMouseEntered(e -> onMouseEntered());
        setOnMouseExited(e -> onMouseExited());
        setMinSize(50, 50);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        setStyle(getColor() ? whiteDefault : blackDefault);
    }

    private boolean getColor(){
        return (x % 2 == 1 && y % 2 == 1) || x % 2 == 0 && y % 2 == 0 ? true : false;
    }

    private void setHighlightEmpty() {
        setStyle(getColor() ? "-fx-background-color: #fdc4c4;-fx-border-style: solid" : "-fx-background-color: #8a6f6f;-fx-border-style: solid");
    }

    private void setHighlightKill() {
        setStyle(getColor() ? "-fx-background-color: #f80000;-fx-border-style: solid" : "-fx-background-color: #ff0000;-fx-border-style: solid");
    }

    public Figure getFigure() {
        return figure;
    }

    private void onMouseExited() {
        if(figure == null){
            return;
        }
        List<ChessField> trueFields = figure.getAccessibleFields();
        if (trueFields != null) {
            for (ChessField field : trueFields){
                field.setDefaultColor();
            }
        }
    }

    private void onMouseEntered() {
        if(figure == null){
            return;
        }
        List<ChessField> trueFields = figure.getAccessibleFields();
        if (trueFields != null) {
            for (ChessField field : trueFields) {
                if (field.figure != null) {
                    field.setHighlightKill();
                } else {
                    field.setHighlightEmpty();
                }
            }
        }
    }
}
