package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.ChessLoader.ChessXmlLoader;

import cz.cvut.fel.pjv.gui.ChessGameScene;
import cz.cvut.fel.pjv.gui.LoadBarMenu;
import cz.cvut.fel.pjv.gui.SaveBarMenu;
import cz.cvut.fel.pjv.gui.Utils;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class ChessGame {

    public ChessBoard board;
    private ChessGameScene gameScene;
    private Label WhiteTimerLabel,BlackTimerLabel;
    private ChessTimer WhiteTimer,BlackTimer;

    public ChessGame(Button button, String game_board, Boolean cooperative) {
        board = new ChessBoard(cooperative);
        prepare_timers();

        ChessXmlLoader LoadXml = new ChessXmlLoader(board);
        LoadXml.loadFromFile(board,game_board);

        board.updateAttackedFields();


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


        GridPane options = prepare_options_menu();

        pane.setTop(options);
        pane.setRight(TimerGrid);
        pane.setAlignment(TimerGrid,Pos.CENTER_LEFT);
        gameScene = new ChessGameScene(board);
        pane.setCenter(gameScene);
        pane.setAlignment(gameScene,Pos.CENTER);
        return pane;
    }

    private GridPane prepare_options_menu(){

        GridPane options = new GridPane();
        MenuButton SaveMenu = new SaveBarMenu(board);
        MenuButton LoadMenu = new LoadBarMenu(board);
        SaveMenu.setGraphic(new ImageView(Utils.loadImage("/images/icons/save_icon.png",30,30)));
        LoadMenu.setGraphic(new ImageView(Utils.loadImage("/images/icons/load_icon.png",30,30)));

        Button reset = ChessGameScene.optionButton("/images/icons/reset_icon.png");
        Button exit = ChessGameScene.optionButton("/images/icons/exit_icon.png");

        reset.setOnAction((EventHandler) event -> new ChessGame(reset, "/saved_games/starter_board.xml",false));
        exit.setOnAction((EventHandler) event -> {
            try {
                new Utils().change_scene(exit,"/fxml/menu_style.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        options.setPadding(new Insets(20,0,0,20));
        options.setHgap(20);
        options.setVgap(20);
        options.add(exit,0,0);
        options.add(SaveMenu,1,0);
        options.add(LoadMenu,2,0);
        options.add(reset,3,0);
        return options;
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
