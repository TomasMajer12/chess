package cz.cvut.fel.pjv.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;


public class StartMenu extends Application {
    Button new_game;
    Button settings;
    Button exit;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui_style.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.getIcons().add(new Image(StartMenu.class.getResourceAsStream("/images/img.png")));

        new_game = new Button();
        settings = new Button();
        exit = new Button();

        new_game.setText("NEW GAME");
        settings.setText("SETTINGS");
        exit.setText("EXIT");

        VBox box = new VBox(new_game,settings,exit);
        box.setAlignment(Pos.CENTER);

        BorderPane border = new BorderPane();
        border.setCenter(box);
        Scene my_scene = new Scene(root,640,480);
        primaryStage.setScene(my_scene);
        primaryStage.show();
    }
}
