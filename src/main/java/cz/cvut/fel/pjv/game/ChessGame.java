package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.ChessLoader.ChessXmlLoader;
import cz.cvut.fel.pjv.ChessLoader.ChessXmlSaver;
import cz.cvut.fel.pjv.figures.*;
import cz.cvut.fel.pjv.gui.GameScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;

public class ChessGame {

    public ChessBoard board;
    private GameScene gameScene;
    private Thread WhiteTimer;

    public ChessGame(Button button, String game_board) {
        //TimerThread WT = new TimerThread();
        //WhiteTimer = new Thread(WT);
        //WhiteTimer.start();

        board = new ChessBoard();
        ChessXmlLoader LoadXml = new ChessXmlLoader(board);
        LoadXml.loadFromFile(board,game_board);


        ChessXmlSaver SaveXml = new ChessXmlSaver();
        SaveXml.saveDataToFile(SaveXml.save(board), new File("file.xml"));


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
        board.getField(0,0).setFigure(new Rook("black","rook",board.getField(0,0)));
        board.getField(1,0).setFigure(new Knight("black","knight",board.getField(1,0)));
        board.getField(2,0).setFigure(new Bishop("black","bishop",board.getField(2,0)));
        board.getField(3,0).setFigure(new Queen("black","queen",board.getField(3,0)));
        board.getField(4,0).setFigure(new King("black","king",board.getField(4,0)));
        board.getField(5,0).setFigure(new Bishop("black","bishop",board.getField(5,0)));
        board.getField(6,0).setFigure(new Knight("black","knight",board.getField(6,0)));
        board.getField(7,0).setFigure(new Rook("black","rook",board.getField(7,0)));
        for (int i = 0; i < 8;i++ ){
            board.getField(i,1).setFigure(new Pawn("black","pawn",board.getField(i,1)));
        }

        board.getField(0,7).setFigure(new Rook("white","rook",board.getField(0,7)));
        board.getField(1,7).setFigure(new Knight("white","knight",board.getField(1,7)));
        board.getField(2,7).setFigure(new Bishop("white","bishop",board.getField(2,7)));
        board.getField(3,7).setFigure(new Queen("white","queen",board.getField(3,7)));
        board.getField(4,7).setFigure(new King("white","king",board.getField(4,7)));
        board.getField(5,7).setFigure(new Bishop("white","bishop",board.getField(5,7)));
        board.getField(6,7).setFigure(new Knight("white","knight",board.getField(6,7)));
        board.getField(7,7).setFigure(new Rook("white","rook",board.getField(7,7)));
        for (int i = 0; i < 8;i++ ){
            board.getField(i,6).setFigure(new Pawn("white","pawn",board.getField(i,6)));
        }
    }
}
