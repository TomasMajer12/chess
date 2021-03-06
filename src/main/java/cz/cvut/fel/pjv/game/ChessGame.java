package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.ChessLoader.ChessPgnLoader;
import cz.cvut.fel.pjv.ChessLoader.ChessXmlLoader;

import cz.cvut.fel.pjv.ChessLoader.PGNViewer;
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

/**
 * Class for managing GameScene, inGameMenu and timers
 *
 */
public class ChessGame {

    public ChessBoard board;
    private ChessGameScene gameScene;
    private Label WhiteTimerLabel,BlackTimerLabel;
    private ChessTimer WhiteTimer,BlackTimer;

    private boolean cooperative,AI_game;
    private int maxTime;

    public ChessGame(Button button, String game_board, boolean cooperative,boolean AI_game,int maxTime) {
        this.cooperative = cooperative;
        this.AI_game = AI_game;
        this.maxTime = maxTime;

        if(AI_game){//start AI game
            board = new ChessBoard(cooperative,AI_game);
        }else{//other game modes
            board = new ChessBoard(cooperative);
        }
        //set timer threads
        prepare_timers();
        if(game_board.endsWith(".xml")){
            ChessXmlLoader LoadXml = new ChessXmlLoader(board);//load save or starter board
            LoadXml.loadFromFile(board,game_board);
        }else if(game_board.endsWith(".pgn")){ //loading from pgn
            ChessXmlLoader LoadXml = new ChessXmlLoader(board);
            LoadXml.loadFromFile(board,"/saved_games/starter_board.xml");//prepare started board
            ChessPgnLoader pgnLoader = new ChessPgnLoader();
            pgnLoader.load_pgn_game(board,game_board); //make moves
        }

        board.updateAttackedFields();

        Stage stage = (Stage) button.getScene().getWindow();
        BorderPane pane = prepare_boarder_pane();
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that prepares and start timers
     */
    private void prepare_timers(){
        WhiteTimer = new ChessTimer("white",board,maxTime);
        BlackTimer = new ChessTimer("black",board,maxTime);

        WhiteTimerLabel = Timer_label(); //create labels for timers
        BlackTimerLabel = Timer_label();

        Task White_timer_task = timer_task(WhiteTimer); //create task for threads
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

    /**
     * Method for creating main BorderPane
     * @return
     */
    private BorderPane prepare_boarder_pane(){
        BorderPane pane = new BorderPane();
        pane.setMinSize(700,650);

        VBox TimerGrid = new VBox();
        TimerGrid.getChildren().add(WhiteTimerLabel);
        TimerGrid.getChildren().add(BlackTimerLabel); //add timers to borderpane
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

    /**
     * Preparing InGameMenu
     * Reset button -> new game
     * Exit button -> back to main menu
     * SaveBar and LoadBar menu for loading and saving games
     * @return
     */
    private GridPane prepare_options_menu(){

        GridPane options = new GridPane();
        MenuButton SaveMenu = new SaveBarMenu(board);
        MenuButton LoadMenu = new LoadBarMenu(board);
        SaveMenu.setGraphic(new ImageView(Utils.loadImage("/images/icons/save_icon.png",30,30)));
        LoadMenu.setGraphic(new ImageView(Utils.loadImage("/images/icons/load_icon.png",30,30)));

        Button reset = ChessGameScene.optionButton("/images/icons/reset_icon.png");
        Button exit = ChessGameScene.optionButton("/images/icons/exit_icon.png");

        //button actions
        reset.setOnAction((EventHandler) event -> new ChessGame(reset, "/saved_games/starter_board.xml",cooperative,AI_game,maxTime));
        exit.setOnAction((EventHandler) event -> {
            try {
                new Utils().change_scene(exit,"/fxml/menu_style.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //add all to gridpane
        options.setPadding(new Insets(20,0,0,20));
        options.setHgap(20);
        options.setVgap(20);
        options.add(exit,0,0);
        options.add(SaveMenu,1,0);
        options.add(LoadMenu,2,0);
        options.add(reset,3,0);
        return options;
    }

    /**
     * Graphic look for TimerLabel
     * @return
     */
    private Label Timer_label(){
        Label Tlabel = new Label();
        Tlabel.setStyle("-fx-background-color: #ffffff;-fx-border-style: solid;-fx-border-color:  #000000;-fx-border-width: 2");
        Tlabel.setAlignment(Pos.CENTER);
        Tlabel.setMinSize(65,30);
        return Tlabel;
    }

    /**
     * Method for creating Timer task
     * Updates chesstimer every 10ms
     * Task is useb by threads
     * @param ChT
     * @return
     */
    private Task timer_task(ChessTimer ChT){
        Task task = new Task() {
            @Override
            public Void call() throws InterruptedException{
                ChT.start();
                while(true){
                    updateMessage(ChT.get_formated_time());//update
                    Thread.sleep(10);
                }
            }
        };
        return task;
    }
}
