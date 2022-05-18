package cz.cvut.fel.pjv.gui;

import cz.cvut.fel.pjv.ChessLoader.PGNViewer;
import cz.cvut.fel.pjv.game.ChessGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class holds main GUI controller
 * ->creating mainstage
 * ->easy changing between scenes
 * ->main connector to fxml files
 * ->it holds all fxml button id
 *
 */
public class GuiController extends Application{

    @FXML
    private Label TimerLabel;

    private int maxTime = 60;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu_style.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.getIcons().add(Utils.loadImage("/images/icons/game_icon.png",25,25));
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(700);
        Scene main_menu = new Scene(root);
        main_menu.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(main_menu);
        primaryStage.show();
    }

    @FXML
    private Button new_game,settings,back,load_game,game,save_1,save_2,save_3,coop,AIgame,pgn_menu,pgn_1,pgn_2,pgn_3;

    private Utils utils = new Utils();

    @FXML
    private void change_to_new_game() throws IOException {
        utils.change_scene(new_game,"/fxml/new_game_style.fxml");
    }

    @FXML
    private void change_to_settings() throws IOException {
        utils.change_scene(settings,"/fxml/settings_style.fxml");
    }

    @FXML
    private void change_to_main_menu() throws IOException {
        utils.change_scene(back,"/fxml/menu_style.fxml");
    }

    @FXML
    private void change_to_load_game() throws IOException{
        utils.change_scene(load_game,"/fxml/load_menu_style.fxml");
    }

    @FXML
    private void exit_game() {
        Platform.exit();
    }

    @FXML
    private void game_start(){
        new ChessGame(game, "/saved_games/starter_board.xml",false,false,maxTime);
    }

    @FXML
    private void load_save_1(){
        new ChessGame(save_1,"/saved_games/save_1.xml",false,false,maxTime);
    }

    @FXML
    private void load_save_2(){
        new ChessGame(save_2,"/saved_games/save_2.xml",false,false,maxTime);
    }

    @FXML
    private void load_save_3(){
        new ChessGame(save_3,"/saved_games/save_3.xml",false,false,maxTime);
    }

    @FXML
    public void load_coop_game() {
        new ChessGame(coop, "/saved_games/starter_board.xml",true,false,maxTime);
    }

    @FXML
    public void ai_game_start() {
        new ChessGame(AIgame, "/saved_games/starter_board.xml",false,true,maxTime);
    }

    @FXML
    public void add_to_timer() {
        maxTime++;
        TimerLabel.setText(String.valueOf(maxTime));
    }

    @FXML
    public void remove_from_timer() {
        maxTime--;
        TimerLabel.setText(String.valueOf(maxTime));
    }
    @FXML
    public void pgn_menu() throws IOException {
        utils.change_scene(pgn_menu,"/fxml/pgn_menu.fxml");
    }
    @FXML
    public void load_pgn_1() {
        new PGNViewer(pgn_1,"/pgn/pgn_save1.pgn");
    }
    @FXML
    public void load_pgn_2() {
        new PGNViewer(pgn_2,"/pgn/pgn_save2.pgn");
    }
    @FXML
    public void load_pgn_3() {
        new PGNViewer(pgn_3,"/pgn/pgn_save3.pgn");
    }
}
