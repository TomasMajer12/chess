package cz.cvut.fel.pjv.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class Menu_gui extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/gui_style.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.getIcons().add(new Image(Menu_gui.class.getResourceAsStream("/images/icon.png")));
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(800);
        Scene main_menu = new Scene(root);
        main_menu.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(main_menu);
        primaryStage.show();

    }

    public void exit_game(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void settings(ActionEvent actionEvent) {
    }

    public void new_game(ActionEvent actionEvent) {
    }
}
