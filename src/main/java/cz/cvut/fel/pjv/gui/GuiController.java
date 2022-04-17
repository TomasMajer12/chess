package cz.cvut.fel.pjv.gui;

import cz.cvut.fel.pjv.game.ChessBoard;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;



public class GuiController extends Application{

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu_style.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.getIcons().add(Utils.loadImage("/images/icon.png",25,25));
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(700);
        Scene main_menu = new Scene(root);
        main_menu.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(main_menu);
        primaryStage.show();
    }

    @FXML
    private Button new_game,settings,back,load_game,game;

    private GridPane table;

    private Utils utils = new Utils();

    @FXML
    private void change_to_new_game(ActionEvent actionEvent) throws IOException {
        utils.change_scene(new_game,"/fxml/new_game_style.fxml");
    }

    @FXML
    private void change_to_settings(ActionEvent actionEvent) throws IOException {
        utils.change_scene(settings,"/fxml/settings_style.fxml");
    }

    @FXML
    private void change_to_main_menu(ActionEvent actionEvent) throws IOException {
        utils.change_scene(back,"/fxml/menu_style.fxml");
    }

    @FXML
    private void change_to_load_game(ActionEvent actionEvent) throws IOException{
        utils.change_scene(load_game,"/fxml/load_menu_style.fxml");
    }

    @FXML
    private void exit_game(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    private void game_start(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) game.getScene().getWindow();
        BorderPane pane = new BorderPane();
        pane.setMinSize(700,650);
        table = new GameScene();
        pane.setCenter(table);
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
