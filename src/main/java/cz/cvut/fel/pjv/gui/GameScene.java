package cz.cvut.fel.pjv.gui;

import cz.cvut.fel.pjv.game.ChessBoard;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameScene extends GridPane {

    public GameScene() {
        setLabels();
        setCorners();
        add(new ChessBoard(), 1, 1, 8, 8);
        setAlignment(Pos.CENTER);
    }

    private void setCorners(){
        add(newCorner(),0,0,1,1);
        add(newCorner(),0,9,1,1);
        add(newCorner(),9,9,1,1);
        add(newCorner(),9,0,1,1);
    }

    private void setLabels(){
        for (int i = 0; i < 8; i++) {
            add(newRowLabel(i), 0, i + 1, 1, 1);
            add(newRowLabel(i), 9, i + 1, 1, 1);
            add(newColLabel(i), i + 1, 0, 1, 1);
            add(newColLabel(i), i + 1, 9, 1, 1);
        }
    }

    private Label newRowLabel(int i) {
        Label l = new Label(8 - i + "");
        l.setStyle("-fx-background-color: #808080;-fx-border-style: solid");
        l.setMinSize(20, 50);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    private Label newColLabel(int i) {
        Label l = new Label((char) (i + 65) + "");
        l.setStyle("-fx-background-color: #808080;-fx-border-style: solid");
        l.setMinSize(50, 20);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    private Label newCorner(){
        Label l = new Label();
        l.setStyle("-fx-background-color: #808080;-fx-border-style: solid");
        l.setMinSize(20, 20);
        return l;
    }
}