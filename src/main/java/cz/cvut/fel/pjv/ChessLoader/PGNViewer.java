package cz.cvut.fel.pjv.ChessLoader;

import cz.cvut.fel.pjv.figures.*;
import cz.cvut.fel.pjv.figures.King;
import cz.cvut.fel.pjv.game.ChessBoard;
import cz.cvut.fel.pjv.game.ChessField;
import cz.cvut.fel.pjv.gui.ChessGameScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PGNViewer {
    public ChessBoard board;
    private ChessGameScene gameScene;
    List<String> moves;
    int index = 0;

    public PGNViewer(Button button, String game_board) {
        board = new ChessBoard(true);
        ChessXmlLoader LoadXml = new ChessXmlLoader(board);
        LoadXml.loadFromFile(board,"/saved_games/starter_board.xml");

        ChessPgnLoader reader = new ChessPgnLoader();
        moves = reader.load_from_file(game_board);




        Stage stage = (Stage) button.getScene().getWindow();
        BorderPane pane = prepare_boarder_pane();
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private BorderPane prepare_boarder_pane(){
        VBox right = new VBox();
        Button next = new Button("NEXT MOVE");
        next.setOnAction(e->next_move());
        right.getChildren().add(next);
        BorderPane pane = new BorderPane();

        pane.setBottom(right);
        pane.setAlignment(right,Pos.TOP_RIGHT);
        pane.setMinSize(700,650);
        gameScene = new ChessGameScene(board);
        pane.setCenter(gameScene);
        pane.setAlignment(gameScene,Pos.CENTER);

        return pane;
    }

    private void next_move(){
        if(index < moves.size()){
            String [] fields = moves.get(index).split(" ");

            List<Figure> figures = board.getFigures(board.getTurn());
            ChessField field = board.getField(get_XCoords(fields[0]),get_YCoords(fields[0]));
            for (Figure f:figures) {
                if(f.getAccessibleFields() == null){
                    continue;
                }
                if(f.can_move_to(f.getAccessibleFields(), field)){
                    switch (movingFigure(fields[0])){
                        case 'K':
                            if(f instanceof King){
                                board.getField(f.getX(),f.getY()).getFigure().move(board.getField(get_XCoords(fields[0]),get_YCoords(fields[0])));
                            }
                            break;
                        case 'Q':
                            if(f instanceof Queen){
                                board.getField(f.getX(),f.getY()).getFigure().move(board.getField(get_XCoords(fields[0]),get_YCoords(fields[0])));
                            }
                            break;
                        case 'R':
                            if(f instanceof Rook){
                                board.getField(f.getX(),f.getY()).getFigure().move(board.getField(get_XCoords(fields[0]),get_YCoords(fields[0])));
                            }
                            break;
                        case 'N':
                            if(f instanceof Knight){
                                board.getField(f.getX(),f.getY()).getFigure().move(board.getField(get_XCoords(fields[0]),get_YCoords(fields[0])));
                            }
                            break;
                        default:
                            if(f instanceof Pawn){
                                board.getField(f.getX(),f.getY()).getFigure().move(board.getField(get_XCoords(fields[0]),get_YCoords(fields[0])));
                            }
                            break;
                    }
                }

            }


            //board.getField(get_XCoords(fields[0]),get_YCoords(fields[0])).getFigure().move(board.getField(get_XCoords(fields[1]),get_YCoords(fields[1])));
            index++;
        }else{
            System.out.println("out of range");
        }
    }

    private char movingFigure(String move){
        char ch = move.charAt(0);
        return ch;
    }

    private int get_XCoords(String move){
        int index = move.length() - 2;
        return move.charAt(index) -97;
    }

    private int get_YCoords(String move){
        int index = move.length() - 1;
        return Character.getNumericValue(move.charAt(index));
    }

}