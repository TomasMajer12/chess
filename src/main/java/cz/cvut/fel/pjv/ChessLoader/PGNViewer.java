package cz.cvut.fel.pjv.ChessLoader;

import cz.cvut.fel.pjv.figures.*;
import cz.cvut.fel.pjv.figures.King;
import cz.cvut.fel.pjv.game.ChessBoard;
import cz.cvut.fel.pjv.game.ChessField;
import cz.cvut.fel.pjv.game.ChessGame;
import cz.cvut.fel.pjv.gui.ChessGameScene;
import cz.cvut.fel.pjv.gui.Utils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

/**
 * Scene for viewing PGN games
 */
public class PGNViewer {
    private ChessBoard board;
    private ChessGameScene gameScene;
    private String filename;
    List<String> moves;
    int index = 0;
    public Logger LOG = Logger.getLogger(PGNViewer.class.getName());

    /**
     * Contstructor for viewing scene
     * @param button
     * @param game_board
     */
    public PGNViewer(Button button, String game_board){

        this.filename = game_board;
        LOG.addHandler(new StreamHandler(System.out, new SimpleFormatter()));
        board = new ChessBoard(true);
        ChessXmlLoader LoadXml = new ChessXmlLoader(board);
        LoadXml.loadFromFile(board,"/saved_games/starter_board.xml");//load starter board

        ChessPgnLoader reader = new ChessPgnLoader();
        moves = reader.load_from_file(game_board);

        Stage stage = (Stage) button.getScene().getWindow();
        BorderPane pane = prepare_boarder_pane();
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method for creating main borderpane
     * @return
     */
    private BorderPane prepare_boarder_pane(){
        GridPane options = new GridPane();
        Button next = new Button("NEXT MOVE");
        Button back = new Button("BACK");
        Button load_game = new Button("LOAD GAME");

        //button actions
        next.setOnAction(e->next_move());
        load_game.setOnAction(e->new ChessGame(load_game, filename,true,false,120));
        back.setOnAction((EventHandler) event -> {
            try {
                new Utils().change_scene(back,"/fxml/pgn_menu.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        BorderPane pane = new BorderPane();

        options.setPadding(new Insets(20,0,0,20));
        options.setHgap(20);
        options.add(next,0,0);
        options.add(load_game,1,0);
        options.add(back,2,0);


        pane.setBottom(options);
        pane.setAlignment(options,Pos.CENTER);
        pane.setMinSize(700,650);
        gameScene = new ChessGameScene(board);
        pane.setCenter(gameScene);
        pane.setAlignment(gameScene,Pos.CENTER);
        return pane;
    }

    /**
     * Method for showing next move
     */
    private void next_move(){
        if(index < moves.size()){

            if(moves.get(index).startsWith("O")){
                System.out.println("Castling move");
                castling_move(moves.get(index));
            }else{
                move_figure(moves.get(index));
            }
            index++;
        }else{
            System.out.println("out of range");
        }
    }

    /**
     * Check for castling move
     * @param move
     */
    public void castling_move(String move){
        King king = board.get_king(board.revertColor(board.getTurn()));
        if(move.length() == 3){
            board.getField(king.getX(),king.getY()).getFigure().move(board.getField(6,king.getY()));
            LOG.log(Level.INFO,king.getColor() + " Kingside castling");
        }else{
            board.getField(king.getX(),king.getY()).getFigure().move(board.getField(2,king.getY()));
            LOG.log(Level.INFO,king.getColor() + " Queenside castling");
        }
    }

    /**
     * Field figure movement
     * @param move
     */
    public void move_figure( String move){
        System.out.println(move);
        int x = get_XCoords(move);
        int y = get_YCoords(move);
        List<Figure> figures = board.getFigures(board.revertColor(board.getTurn()));
        ChessField field = board.getField(x,y);
        for (Figure f:figures) {
            if(f.getAccessibleFields() == null){
                continue;
            }
            if(f.can_move_to(f.getAccessibleFields(), field)){
                switch (movingFigure(move)){
                    case 'K':
                        if(f instanceof King){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                            LOG.log(Level.INFO,f.getColor() +" " + f.getName() +" was moved on field " + f.field.getX() + " " +f.field.getY());
                        }
                        break;
                    case 'Q':
                        if(f instanceof Queen){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                            LOG.log(Level.INFO,f.getColor() +" " + f.getName() +" was moved on field " + f.field.getX() + " " +f.field.getY());
                        }
                        break;
                    case 'R':
                        if(f instanceof Rook){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                            LOG.log(Level.INFO,f.getColor() +" " + f.getName() +" was moved on field " + f.field.getX() + " " +f.field.getY());
                        }
                        break;
                    case 'N':
                        if(f instanceof Knight){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                            LOG.log(Level.INFO,f.getColor() +" " + f.getName() +" was moved on field " + f.field.getX() + " " +f.field.getY());
                        }
                        break;
                    case 'B':
                        if(f instanceof Bishop){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                            LOG.log(Level.INFO,f.getColor() +" " + f.getName() +" was moved on field " + f.field.getX() + " " +f.field.getY());
                        }
                        break;
                    default:
                        if(f instanceof Pawn){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                            LOG.log(Level.INFO,f.getColor() +" " + f.getName() +" was moved on field " + f.field.getX() + " " +f.field.getY());
                        }
                        break;
                }
            }
        }
    }

    /**
     * Method for determinig which figure is moving
     * @param move
     * @return
     */
    public static char movingFigure(String move){
        char ch = move.charAt(0);
        return ch;
    }

    /**
     * Transform X coord to int
     * @param move
     * @return
     */
    public static int get_XCoords(String move){
        int index;
        if(move.endsWith("+")){
            index = move.length() - 3;
        }else {
            index = move.length() - 2;
        }
        return move.charAt(index) -97;
    }

    /**
     * Transform Y coord to int
     * @param move
     * @return
     */
    public static int get_YCoords(String move){
        int index;
        if(move.endsWith("+")){
            index = move.length() - 2;
        }else {
            index = move.length() - 1;
        }
        return 7- (move.charAt(index) - 49);
    }

}