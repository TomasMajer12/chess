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


public class GuiController extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu_style.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.getIcons().add(new Image(GuiController.class.getResourceAsStream("/images/icon.png")));
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(700);
        Scene main_menu = new Scene(root);
        main_menu.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(main_menu);
        primaryStage.show();
    }

    @FXML
    private Button new_game,settings,back,load_game,game;

    @FXML
    private void change_to_new_game(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) new_game.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/new_game_style.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void change_to_settings(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) settings.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/settings_style.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void change_to_main_menu(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu_style.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void change_to_load_game(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) load_game.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/load_menu_style.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void exit_game(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    private void game_start(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) game.getScene().getWindow();
        BorderPane pane = new BorderPane();
        //chess board with column and row markings
        GridPane table = new GridPane();
        for (int i = 0; i < 8; i++) {
            table.add(newRowLabel(i), 0, i + 1, 1, 1);
            table.add(newRowLabel(i), 9, i + 1, 1, 1);
            table.add(newColLabel(i), i + 1, 0, 1, 1);
            table.add(newColLabel(i), i + 1, 9, 1, 1);
        }
        table.add(new ChessBoard(), 1, 1, 8, 8);
        table.setAlignment(Pos.CENTER);
        pane.setCenter(table);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private Label newRowLabel(int i) {
        Label l = new Label(8 - i + "");
        l.setMinSize(20, 50);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    private Label newColLabel(int i) {
        Label l = new Label((char) (i + 65) + "");
        l.setMinSize(50, 20);
        l.setAlignment(Pos.CENTER);
        return l;
    }


}
