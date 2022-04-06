package cz.cvut.fel.pjv.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class GuiController {

    @FXML
    private Button new_game,settings,back;

    @FXML
    private void change_to_new_game(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) new_game.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/chess_board_style.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void change_to_settings(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) settings.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/chess_board_style.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void change_to_main_menu(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu_style.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void exit_game(ActionEvent actionEvent) {
        Platform.exit();
    }

}
