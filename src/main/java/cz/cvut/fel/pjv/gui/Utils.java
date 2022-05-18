package cz.cvut.fel.pjv.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Helpfull methods
 */
public class Utils {
    /**
     * load image from resource files
     * @param resource
     * @param width
     * @param height
     * @return
     */
    public static Image loadImage(String resource, int width, int height) {
        return new Image(Utils.class.getResourceAsStream(resource), width, height, true, true);
    }

    /**
     * main function for scene changing
     * @param button
     * @param fxml
     * @throws IOException
     */
    public void change_scene(Button button,String fxml) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
