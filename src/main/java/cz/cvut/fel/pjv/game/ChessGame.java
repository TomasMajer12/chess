package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.ChessLoader.ChessXmlLoader;
import cz.cvut.fel.pjv.ChessLoader.ChessXmlSaver;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;

public class ChessGame {

    public ChessBoard board;
    private ChessGameScene gameScene;
    private Label WhiteTimerLabel,BlackTimerLabel;
    private ChessTimer WhiteTimer,BlackTimer;

    public ChessGame(Button button, String game_board) {
        board = new ChessBoard();
        prepare_timers();
        ChessXmlLoader LoadXml = new ChessXmlLoader(board);
        LoadXml.loadFromFile(board,game_board);
        board.updateAttackedFields();

        ChessXmlSaver SaveXml = new ChessXmlSaver();
        SaveXml.saveDataToFile(SaveXml.save(board), new File("file.xml"));


        Stage stage = (Stage) button.getScene().getWindow();
        BorderPane pane = prepare_boarder_pane();
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void prepare_timers(){
        WhiteTimer = new ChessTimer("white",board);
        BlackTimer = new ChessTimer("black",board);

        WhiteTimerLabel = Timer_label();
        BlackTimerLabel = Timer_label();

        Task White_timer_task = timer_task(WhiteTimer);
        Task Black_timer_task = timer_task(BlackTimer);

        WhiteTimerLabel.textProperty().bind(White_timer_task.messageProperty());
        BlackTimerLabel.textProperty().bind(Black_timer_task.messageProperty());

        Thread thread1 = new Thread(White_timer_task);
        thread1.setDaemon(true);
        thread1.start();

        Thread thread2 = new Thread(Black_timer_task);
        thread2.setDaemon(true);
        thread2.start();
    }

    private BorderPane prepare_boarder_pane(){
        BorderPane pane = new BorderPane();
        pane.setMinSize(700,650);

        VBox TimerGrid = new VBox();
        TimerGrid.getChildren().add(WhiteTimerLabel);
        TimerGrid.getChildren().add(BlackTimerLabel);
        TimerGrid.setAlignment(Pos.CENTER);
        TimerGrid.setPadding(new Insets(0,50,0,50));

        GridPane options = new GridPane();
        options.setHgap(10);
        options.add(ChessGameScene.optionButton(),0,0);
        options.add(ChessGameScene.optionButton(),2,0);
        options.add(ChessGameScene.optionButton(),4,0);
        options.add(ChessGameScene.optionButton(),6,0);

        pane.setTop(options);
        pane.setRight(TimerGrid);
        pane.setAlignment(TimerGrid,Pos.CENTER_LEFT);
        gameScene = new ChessGameScene(board);
        pane.setCenter(gameScene);
        pane.setAlignment(gameScene,Pos.CENTER);
        return pane;
    }

    private Label Timer_label(){
        Label Tlabel = new Label();
        Tlabel.setStyle("-fx-background-color: #ffffff;-fx-border-style: solid;-fx-border-color:  #000000;-fx-border-width: 2");
        Tlabel.setAlignment(Pos.CENTER);
        Tlabel.setMinSize(65,30);
        return Tlabel;
    }

    private Task timer_task(ChessTimer ChT){
        Task task = new Task() {
            @Override
            public Void call() throws InterruptedException{
                ChT.start();
                while(true){
                    updateMessage(ChT.get_formated_time());
                    Thread.sleep(10);
                }
            }
        };
        return task;
    }

}
