package cz.cvut.fel.pjv.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class MainMenu extends Application {
    Scene main_menu;
    Parent root;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/menu_style.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.getIcons().add(new Image(MainMenu.class.getResourceAsStream("/images/icon.png")));
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(700);
        main_menu = new Scene(root);
        main_menu.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(main_menu);
        primaryStage.show();

    }

}
