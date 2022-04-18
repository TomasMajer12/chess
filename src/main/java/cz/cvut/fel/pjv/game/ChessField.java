package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.figures.Figure;
import cz.cvut.fel.pjv.gui.Utils;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.List;

public class ChessField extends Label{
    private int x,y;
    private ChessBoard board;
    Figure figure = null;
    private String whiteDefault = "-fx-background-color: white;-fx-border-style: solid";
    private String blackDefault = "-fx-background-color: #4d4d4d;-fx-border-style: solid";
    private String whiteEmptySpace = "-fx-background-color: white;-fx-border-style: solid;-fx-border-color: #ec6060;-fx-border-width: 2";
    private String blackEmptySpace = "-fx-background-color: #4d4d4d;-fx-border-style: solid;-fx-border-color:  #ec6060;-fx-border-width: 2";
    private String whiteKillSpace = "-fx-background-color: #c05555;-fx-border-style: solid;-fx-border-color:  #ec6060;-fx-border-width: 2";
    private String blackKillSpace = "-fx-background-color: #6b2a2a;-fx-border-style: solid;-fx-border-color:  #ec6060;-fx-border-width: 2";

    public ChessField(ChessBoard board,int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        setDefaultColor();
        setAlignment(Pos.CENTER);
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
            setGraphic(new ImageView(Utils.loadImage(figure.getImageStream(), 42,42)));
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
        setStyle(getColor() ? whiteEmptySpace : blackEmptySpace);
    }

    private void setHighlightKill() {
        setStyle(getColor() ? whiteKillSpace : blackKillSpace);
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
