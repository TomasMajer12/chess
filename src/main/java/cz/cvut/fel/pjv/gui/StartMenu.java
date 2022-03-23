package cz.cvut.fel.pjv.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class StartMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess");
        primaryStage.getIcons().add(new Image("C:\\Users\\tomas\\Desktop\\Programs\\Java\\majerto4\\src\\main\\resources\\images\\img.png"));
        Button btn = new Button();

        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(btn);
        Scene first_scene = new Scene(root,640,480,Color.BLUE);
        primaryStage.setScene(first_scene);
        primaryStage.show();
    }
}
