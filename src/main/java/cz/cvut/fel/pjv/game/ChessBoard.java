package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.AI.SimpleAI;
import cz.cvut.fel.pjv.figures.*;

import cz.cvut.fel.pjv.gui.AddFigureContexMenu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.*;
import java.util.logging.*;

/**
 * Class that holds whole game
 * Scene is created by ChessGame and ChessGameScene classes
 */
public class ChessBoard extends GridPane {

    private ChessField[] fields = new ChessField[64];
    private Set<ChessField> WhiteAttackedFields = new HashSet<>();
    private Set<ChessField> BlackAttackedFields = new HashSet<>();
    private Map<String, Set<ChessField>> attackedFields = new HashMap<>();
    public int Turn_counter;
    private boolean AI;
    private SimpleAI ai_player;
    public Logger LOG = Logger.getLogger(ChessBoard.class.getName());

    /**
     * Constructor for cooperative and normal game
     * @param cooperative
     */
    public ChessBoard(boolean cooperative) {
        prepare_board(cooperative);
        Turn_counter = 0;
        AI = false;
    }

    /**
     * Constructor for AI game
     * @param cooperative
     * @param AI
     */
    public ChessBoard(boolean cooperative, boolean AI) {
        System.out.println("Starting AI game");
        prepare_board(cooperative);
        this.AI = AI;
        ai_player = new SimpleAI(this,"black");
        Turn_counter = 0;
    }

    /**
     * Prepare all fields and add FigureContexMenu if mode is cooperative
     * @param cooperative
     */
    public void prepare_board(boolean cooperative){
        LOG.addHandler(new StreamHandler(System.out, new SimpleFormatter()));

        for (int i = 0; i < 64; i++) {
            int x = getX(i);
            int y = getY(i);
            ChessField field = new ChessField(this, x, y);
            if(cooperative){
                AddFigureContexMenu menu= new AddFigureContexMenu(field);//add contexmenu to each field
                field.setContextMenu(menu);
            }
            add(field, x, y);
            fields[i] = field;
        }
        LOG.log(Level.INFO,"Board Created");
    }

    /**
     * remove all figures from board
     */
    public void clear_board(){
        for (ChessField field: fields){
            field.setFigure(null);
        }
    }

    /**
     * Return field, if field is on the board, Null otherweise
     * @param x
     * @param y
     * @return
     */
    public ChessField getField(int x, int y) {
        return x < 0 || x > 7 || y < 0 || y > 7 ? null : fields[y * 8 + x];
    }


    /**
     * Method called after every move
     * Updating attackable fields and checking if king is in check
     * If AI mode active -> makes move
     */
    public void next_turn(){
        updateAttackedFields();
        state_test();
        Turn_counter++;
        if(AI){
            System.out.println("Ai makes move");
            ai_player.make_move();
            Turn_counter++;
            updateAttackedFields();
            state_test();
        }
        updateAttackedFields();
        state_test();
    }


    /**
     * Method for determining which color is on the turn
     * @return
     */
    public String getTurn() {
        return Turn_counter % 2 == 0 ? "black" : "white";
    }

    /**
     * Method for color revert
     * @param color
     * @return
     */
    public String revertColor(String color){
        if(color == "white"){
            return "black";
        }
        return "white";
    }

    /**
     * Check is king is in check
     * Check if king is on the board -> if not endPopUpWindow
     */
    public void state_test(){
        King king = get_king(getTurn());
        if (king != null) {
            if (king.isCheck()){
                LOG.log(Level.INFO,getTurn() + " is in check");
            }
        }else{
            end_stage();//display win
        }
    }

    /**
     * Method for getting X index in field array
     * @param index
     * @return
     */
    private int getX(int index) {
        return index % 8;
    }

    /**
     * Method for getting Y index in field array
     * @param index
     * @return
     */
    private int getY(int index) {
        return (index - getX(index)) / 8;
    }


    /**
     * Method that updates Sets of fields that can be attacked by color
     */
    public void updateAttackedFields(){
        if(Turn_counter % 2 == 0){
            WhiteAttackedFields = new HashSet<>();//reset
            for(ChessField f : fields){
                if (f.figure != null && f.figure.getColor() == "white"){ //all white figures
                    if(f.figure instanceof Pawn && ((Pawn) f.figure).can_attack_fields() != null){ //pawn attack on fields without figure
                        WhiteAttackedFields.addAll(((Pawn) f.figure).can_attack_fields());
                    }else if(f.figure instanceof King && ((King) f.figure).can_attack_fields() != null){ //special ->king cant go to check
                        WhiteAttackedFields.addAll(((King) f.figure).can_attack_fields());
                    } else if(f.getFigure().getAccessibleFields() != null){ //all other figures
                        WhiteAttackedFields.addAll(f.getFigure().getAccessibleFields());
                    }
                }
            }
        }else{
            BlackAttackedFields = new HashSet<>();//reset
            for(ChessField f : fields){
                if (f.figure != null && f.figure.getColor() == "black"){//all black figures
                    if(f.figure instanceof Pawn && ((Pawn) f.figure).can_attack_fields() != null) {//pawn attack on fields without figure
                        BlackAttackedFields.addAll(((Pawn) f.figure).can_attack_fields());
                    }else if(f.figure instanceof King && ((King) f.figure).can_attack_fields() != null){//special ->king cant go to check
                        BlackAttackedFields.addAll(((King) f.figure).can_attack_fields());
                    }else if(f.getFigure().getAccessibleFields() != null){
                        BlackAttackedFields.addAll(f.getFigure().getAccessibleFields());
                    }
                }
            }
        }
    }

    /**
     * getter for white and black attacked fields
     * @param color
     * @return
     */
    public Set<ChessField> getAttackedFields(String color) {
        if(color == "white"){
            return WhiteAttackedFields;
        }
        return BlackAttackedFields;
    }

    /**
     * Method for getting all figures with given color
     * @param color
     * @return
     */
    public List<Figure> getFigures(String color) {
        List<Figure> figures = new ArrayList<>();
        for(ChessField field : fields){
            if(field.figure != null && field.figure.getColor() == color){
                figures.add(field.figure);
            }
        }
        return figures;
    }

    /**
     * Method for getting king
     * @param color
     * @return
     */
    public King get_king(String color){
        for (ChessField field : fields){
            if(field.figure instanceof King && field.figure.getColor() == color){
                return (King) field.figure;
            }
        }
        return null;
    }


    /**
     * Easy popUpWindow for endstage
     * window close on buttun press
     */
    private void end_stage(){
        LOG.log(Level.INFO,"Color " + revertColor(getTurn()) +" wins"); //who won
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("End window");
        Label label1= new Label("Color " + getTurn() +" wins"); //Display winner
        label1.setStyle("-fx-font-size: 40px;");
        Button button1= new Button("Close");
        button1.setOnAction(e -> popupwindow.close()); //set on button action

        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        scene1.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());//add stylesheet
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    public Logger getLOG() {
        return LOG;
    }
}
