package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.figures.*;
import cz.cvut.fel.pjv.gui.GameScene;
import cz.cvut.fel.pjv.gui.Utils;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChessGame {

    private ChessBoard board;
    private GameScene gameScene;

    public ChessGame(Button button) {
        board = new ChessBoard();
        starterBoard();
        gameScene = new GameScene(board);
        Stage stage = (Stage) button.getScene().getWindow();
        BorderPane pane = new BorderPane();
        pane.setMinSize(700,650);
        pane.setCenter(gameScene);
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void starterBoard(){
        board.getField(0,0).setFigure(new Rook(Color.BLACK,"rook",board.getField(0,0)));
        board.getField(1,0).setFigure(new Knight(Color.BLACK,"knight",board.getField(1,0)));
        board.getField(2,0).setFigure(new Bishop(Color.BLACK,"bishop",board.getField(2,0)));
        board.getField(3,0).setFigure(new Queen(Color.BLACK,"queen",board.getField(3,0)));
        board.getField(4,0).setFigure(new King(Color.BLACK,"king",board.getField(4,0)));
        board.getField(5,0).setFigure(new Bishop(Color.BLACK,"bishop",board.getField(5,0)));
        board.getField(6,0).setFigure(new Knight(Color.BLACK,"knight",board.getField(6,0)));
        board.getField(7,0).setFigure(new Rook(Color.BLACK,"rook",board.getField(7,0)));
        for (int i = 0; i < 8;i++ ){
            board.getField(i,1).setFigure(new Pawn(Color.BLACK,"pawn",board.getField(i,1)));
        }

        board.getField(0,7).setFigure(new Rook(Color.WHITE,"rook",board.getField(0,7)));
        board.getField(1,7).setFigure(new Knight(Color.WHITE,"knight",board.getField(1,7)));
        board.getField(2,7).setFigure(new Bishop(Color.WHITE,"bishop",board.getField(2,7)));
        board.getField(3,7).setFigure(new Queen(Color.WHITE,"queen",board.getField(3,7)));
        board.getField(4,7).setFigure(new King(Color.WHITE,"king",board.getField(4,7)));
        board.getField(5,7).setFigure(new Bishop(Color.WHITE,"bishop",board.getField(5,7)));
        board.getField(6,7).setFigure(new Knight(Color.WHITE,"knight",board.getField(6,7)));
        board.getField(7,7).setFigure(new Rook(Color.WHITE,"rook",board.getField(7,7)));
        /*for (int i = 0; i < 8;i++ ){
            board.getField(i,6).setFigure(new Pawn(Color.WHITE,"pawn",board.getField(i,6)));
        }*/
    }

    private void updateBoard(ChessBoard board){

    }


}
