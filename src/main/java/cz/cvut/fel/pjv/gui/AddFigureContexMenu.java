package cz.cvut.fel.pjv.gui;

import cz.cvut.fel.pjv.figures.*;
import cz.cvut.fel.pjv.game.ChessField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class AddFigureContexMenu extends ContextMenu {
    ChessField field;
    public AddFigureContexMenu(ChessField field){
        this.field = field;
        getItems().add(new item( "Add White Queen", e -> setFigure(new Queen("white", "queen",field))));
        getItems().add(new item("Add White Bishop", e -> setFigure(new Bishop("white","bishop" ,field))));
        getItems().add(new item("Add White Knight", e -> setFigure(new Knight("white","knight" ,field))));
        getItems().add(new item("Add White Rook", e -> setFigure(new Rook("white","rook" ,field))));
        getItems().add(new item("Add White King", e -> setFigure(new King("white","king" ,field))));
        getItems().add(new item("Add White Pawn", e -> setFigure(new Pawn("white", "pawn",field))));
        getItems().add(new item("Add Black Queen", e -> setFigure(new Queen("black","queen" ,field))));
        getItems().add(new item("Add Black Bishop", e -> setFigure(new Bishop("black", "bishop",field))));
        getItems().add(new item("Add Black Knight", e -> setFigure(new Knight("black", "knight",field))));
        getItems().add(new item("Add Black Rook", e -> setFigure(new Rook("black", "rook",field))));
        getItems().add(new item("Add Black King", e -> setFigure(new King("black", "king",field))));
        getItems().add(new item("Add Black Pawn", e -> setFigure(new Pawn("black","pawn", field))));
        getItems().add(new item("Remove Figure", e -> setFigure(null)));
    }

    private void setFigure(Figure figure) {
        field.setFigure(figure);
        field.getBoard().updateAttackedFields();
        field.getBoard().state_test();
    }

    private class item extends MenuItem{
        public item(String text, EventHandler<ActionEvent> event){
            setText(text);
            setOnAction(event);
        }
    }
}
