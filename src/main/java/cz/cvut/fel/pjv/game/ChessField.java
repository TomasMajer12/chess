package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.figures.Figure;
import cz.cvut.fel.pjv.gui.Utils;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.util.List;

/**
 * Class representing object for each field of board
 * 
 */
public class ChessField extends Label{
    private final int x,y;
    private ChessBoard board;
    Figure figure = null;
    //colors
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
        
        //set ondrag and onmouse
        setOnDragDetected(this::handleDragDetection);
        setOnDragOver(this::onDragOver);
        setOnDragDropped(this::onDragDropped);
        setOnDragDone(this::onDragDone);
        setOnMouseEntered(e -> onMouseEntered());
        setOnMouseExited(e -> onMouseExited());
        
        setMinSize(50, 50);
    }

    /**
     * Display Figure on field
     * @param figure
     */
    public void setFigure(Figure figure){
        this.figure = figure;
        if (figure == null){
            setGraphic(null);
        }else{
            setGraphic(new ImageView(Utils.loadImage(figure.getImageStream(), 42,42)));
        }
    }

    /**
     * Reset field color after highlight
     */
    private void setDefaultColor(){
        setStyle(getColor() ? whiteDefault : blackDefault);
    }

    /**
     * Chess pattern making
     * @return
     */
    private boolean getColor(){
        return (x % 2 == 1 && y % 2 == 1) || x % 2 == 0 && y % 2 == 0 ? true : false;
    }

    private void setHighlightEmpty() {
        setStyle(getColor() ? whiteEmptySpace : blackEmptySpace);
    }

    private void setHighlightKill() {
        setStyle(getColor() ? whiteKillSpace : blackKillSpace);
    }

    /**
     * Reset fields color when leave
     * field with figure
     */
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

    /**
     * Method disable color reset when we are in drag mode
     */
    private void onMouseExited2(){
        return;
    }

    /**
     * Highlight fields when enter
     * field with figure
     */
    private void onMouseEntered() {
        if(figure == null){
            return;
        }
        List<ChessField> trueFields = figure.getAccessibleFields();
        if (trueFields != null) {
            for (ChessField field : trueFields) {
                if(field.getFigure() != null && field.getFigure().getColor() == figure.getColor()){//same color
                    continue;
                }else if (field.figure != null) { //different color
                    field.setHighlightKill();
                } else {
                    field.setHighlightEmpty();//empty field
                }
            }
        }
    }

    /**
     * Put Serializable Figure in Clipboard content for movement
     *
     * @param e
     */
    private void handleDragDetection(MouseEvent e){
        if(figure == null){
            return;
        }
        getBoard().updateAttackedFields();
        List<ChessField> trueFields = figure.getAccessibleFields();
        if(trueFields != null){
                
            setOnMouseExited(a -> onMouseExited2()); //disable on mouse exited
            Dragboard db = startDragAndDrop(TransferMode.MOVE);
            db.setDragView(Utils.loadImage(figure.getImageStream(), 50,50)); //get figure image
            ClipboardContent cb = new ClipboardContent();
            cb.put(Figure.CHESS_FIGURE,figure);//put figure in clipboard
            db.setContent(cb);
            db.setDragViewOffsetX(25);//set image on center
            db.setDragViewOffsetY(25);

            for (ChessField field : trueFields) {//highlight fields
                if(field.getFigure() != null && field.getFigure().getColor() == figure.getColor()){
                    continue;
                }else if (field.figure != null) {
                    field.setHighlightKill();
                } else {
                    field.setHighlightEmpty();
                }
            }
        }
        e.consume();
    }

    /**
     * Drop Figure on field where we can move
     * @param e
     */
    private void onDragDropped(DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasContent(Figure.CHESS_FIGURE)) {
            Figure source = deserializeFigure(db); //deserialize figure
            source = board.getField(source.getX(), source.getY()).getFigure(); //find figure in original instance
            if (source.can_move_to(source.getAccessibleFields(),this)) {
                source.getAccessibleFields().forEach(ChessField::setDefaultColor);//reset colors
                source.move(this);//move figure
                //getBoard().next_turn();//initite next turn
            }
        }
        setOnMouseExited(a -> onMouseExited()); // reset on mouse exit back to normal
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

    /**
     * Get figure from dragboard content
     * @param db
     * @return
     */
    private Figure deserializeFigure(Dragboard db) {
        Figure source = (Figure) db.getContent(Figure.CHESS_FIGURE);
        source.setField(this.getBoard().getField(source.getX(), source.getY()));
        return source;
    }


    //getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public ChessBoard getBoard(){
        return board;
    }
    public Figure getFigure() {
        return figure;
    }




}
