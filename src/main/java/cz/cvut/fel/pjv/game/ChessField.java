package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.figures.Figure;
import cz.cvut.fel.pjv.figures.Pawn;
import cz.cvut.fel.pjv.figures.Queen;
import cz.cvut.fel.pjv.gui.Utils;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.util.List;
import java.util.Set;

public class ChessField extends Label{
    private final int x,y;
    private ChessBoard board;
    Figure figure = null;
    private final String whiteDefault = "-fx-background-color: white;-fx-border-style: solid";
    private final String blackDefault = "-fx-background-color: #4d4d4d;-fx-border-style: solid";
    private final String whiteEmptySpace = "-fx-background-color: #c05555;-fx-border-style: solid";
    private final String blackEmptySpace = "-fx-background-color: #6b2a2a;-fx-border-style: solid";
    private final String whiteKillSpace = "-fx-background-color: #c05555;-fx-border-style: solid;-fx-border-color:  #ff1b1b;-fx-border-width: 2";
    private final String blackKillSpace = "-fx-background-color: #6b2a2a;-fx-border-style: solid;-fx-border-color:  #ff1b1b;-fx-border-width: 2";

    public ChessField(ChessBoard board,int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        setDefaultColor();
        setAlignment(Pos.CENTER);
        setOnDragDetected(this::handleDragDetection);
        setOnDragOver(this::onDragOver);
        setOnDragDropped(this::onDragDropped);
        setOnDragDone(this::onDragDone);
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

    private void onMouseExited(){
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

    private void onMouseExited2(){
        return;
    }

    private void onMouseEntered() {
        if(figure == null){
            return;
        }
        List<ChessField> trueFields = figure.getAccessibleFields();
        if (trueFields != null) {
            for (ChessField field : trueFields) {
                if (field.figure != null){
                    field.setHighlightKill();
                } else {
                    field.setHighlightEmpty();
                }
            }
        }
    }

    private void handleDragDetection(MouseEvent e){
        if(figure == null){
            return;
        }
        getBoard().updateAttackedFields();
        List<ChessField> trueFields = figure.getAccessibleFields();
        if(trueFields != null){
                
            setOnMouseExited(a -> onMouseExited2());
            Dragboard db = startDragAndDrop(TransferMode.MOVE);
            db.setDragView(Utils.loadImage(figure.getImageStream(), 50,50));
            ClipboardContent cb = new ClipboardContent();
            cb.put(Figure.CHESS_FIGURE,figure);
            db.setContent(cb);
            db.setDragViewOffsetX(25);
            db.setDragViewOffsetY(25);

            for (ChessField field : trueFields) {
                if (field.figure != null) {
                    field.setHighlightKill();
                } else {
                    field.setHighlightEmpty();
                }
            }
        }
        e.consume();
    }

    private void onDragDropped(DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasContent(Figure.CHESS_FIGURE)) {
            Figure source = deserializeFigure(db);
            source = board.getField(source.getX(), source.getY()).getFigure();
            if (source.can_move_to(source.getAccessibleFields(),this)) {
                source.getAccessibleFields().forEach(ChessField::setDefaultColor);
                source.move(this);
                getBoard().next_turn();
            }
        }
        setOnMouseExited(a -> onMouseExited());
        e.consume();
    }

    private void onDragOver(DragEvent e) {
        if (e.getDragboard().hasContent(Figure.CHESS_FIGURE)) {
            e.acceptTransferModes(TransferMode.MOVE);
        }
        e.consume();
    }

    private void onDragDone(DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasContent(Figure.CHESS_FIGURE)) {
            Figure source = deserializeFigure(db);
            source.getAccessibleFields().forEach(ChessField::setDefaultColor);
        }
        e.consume();
    }


    private Figure deserializeFigure(Dragboard db) {
        Figure source = (Figure) db.getContent(Figure.CHESS_FIGURE);
        source.setField(this.getBoard().getField(source.getX(), source.getY()));
        return source;
    }

}
