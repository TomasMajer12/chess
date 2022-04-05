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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/gui_style.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.getIcons().add(new Image(StartMenu.class.getResourceAsStream("/images/icon.png")));
        Scene my_scene = new Scene(root,640,480);
        my_scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(my_scene);
        primaryStage.show();
    }
}
