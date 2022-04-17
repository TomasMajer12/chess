package cz.cvut.fel.pjv.gui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Utils {

    public static Image loadImage(String resource, int width, int height) {
        return new Image(Utils.class.getResourceAsStream(resource), width, height, true, true);
    }

    public void change_scene(Button button,String fxml) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static Label newRowLabel(int i) {
        Label l = new Label(8 - i + "");
        l.setStyle("-fx-background-color: #808080;-fx-border-style: solid");
        l.setMinSize(20, 50);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    public static Label newColLabel(int i) {
        Label l = new Label((char) (i + 65) + "");
        l.setStyle("-fx-background-color: #808080;-fx-border-style: solid");
        l.setMinSize(50, 20);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    public static Label setCorners(){
        Label l = new Label();
        l.setStyle("-fx-background-color: #808080;-fx-border-style: solid");
        l.setMinSize(20, 20);
        return l;
    }
}
