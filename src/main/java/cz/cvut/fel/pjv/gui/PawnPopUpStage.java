package cz.cvut.fel.pjv.gui;

import cz.cvut.fel.pjv.figures.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * PopUpWindow for pawn when he reaches end of board
 * Gives you 4 Figures that can be changed for pawn
 */
public class PawnPopUpStage extends Stage {

    public PawnPopUpStage(Figure figure) {
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Choose figure");
        Button queen= figure_button("/images/figures/" + figure.getColor()+"_queen.png");
        Button rook= figure_button("/images/figures/" + figure.getColor()+"_rook.png");
        Button bishop= figure_button("/images/figures/" + figure.getColor()+"_bishop.png");
        Button knight= figure_button("/images/figures/" + figure.getColor()+"_knight.png");

        //create new figure on pawn field
        queen.setOnAction(e -> {
            figure.getField().setFigure(new Queen(figure.getColor(), "queen",figure.field));
            close();
        });

        rook.setOnAction(e -> {
            figure.getField().setFigure(new Rook(figure.getColor(), "rook",figure.field));
            close();
        });

        bishop.setOnAction(e -> {
            figure.getField().setFigure(new Bishop(figure.getColor(), "bishop",figure.field));
            close();
        });

        knight.setOnAction(e -> {
            figure.getField().setFigure(new Knight(figure.getColor(), "knight",figure.field));
            close();
        });

        GridPane figure_chooser = new GridPane();
        figure_chooser.setHgap(20);
        figure_chooser.add(queen,0,0);
        figure_chooser.add(rook,1,0);
        figure_chooser.add(bishop,2,0);
        figure_chooser.add(knight,3,0);
        Scene scene= new Scene(figure_chooser, 280, 60);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        setScene(scene);
        showAndWait();
    }

    /**
     * Method for graphic look of FigureButtons
     * @param pathToImage
     * @return
     */
    private Button figure_button(String pathToImage){
        Button button = new Button();
        button.getStyleClass().add("optionButton"); //for hover mode
        button.setStyle("-fx-background-color: rgba(255,238,238,0)");
        button.setGraphic(new ImageView(Utils.loadImage(pathToImage,30,30)));
        button.setMinSize(50,50);
        button.setMaxSize(50,50);
        return  button;
    }
}
