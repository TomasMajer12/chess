package cz.cvut.fel.pjv.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class StartMenu extends Application {
    Button new_game;
    Button settings;
    Button exit;

    @Override
    public void start(Stage primaryStage) {
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
        Scene my_scene = new Scene(border,640,480);
        primaryStage.setScene(my_scene);
        primaryStage.show();
    }
}
