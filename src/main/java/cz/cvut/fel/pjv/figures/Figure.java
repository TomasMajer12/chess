package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import javafx.scene.input.DataFormat;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract class for all figures
 */
public abstract class Figure implements Serializable {
    public transient static final DataFormat CHESS_FIGURE = new DataFormat("chess.figure");
    public transient ChessField field; //--> not serializable
    int x, y;
    String color;
    private final String name;
    private final String imageStream;
    private int move_count;

    public Figure(String color, String name, ChessField field) {
        this.color = color;
        this.name = name;
        this.field = field;
        this.move_count = 0;

        if (color == "black"){
            imageStream ="/images/figures/black_" + name + ".png";
        }else if(color == "white"){
            imageStream = "/images/figures/white_" + name + ".png";
        }else{
            imageStream = null;
            System.out.println("invalid color\n");
        }
        if (field != null){
            this.x = field.getX();
            this.y = field.getY();
        }
    }

    /**
     * Abstract method for all instances of Figure
     * Every instance has special movement
     * @return
     */
    public abstract List<ChessField> AccessibleFields();

    /**
     * Main method for Figure movement
     *
     * @param move_field
     */
    public void move(ChessField move_field) {
        move_field.setFigure(this);
        if (this.field != null) {
            this.field.setFigure(null);
        }
        x =  move_field.getX();
        y =  move_field.getY();
        setField(move_field);
        if(this instanceof King){ //castling
            ((King) this).castling_rook_move();
        }
        if(this instanceof Pawn){ //special pawn movements
            ((Pawn) this).enPassant_move();
            ((Pawn) this).reach_end_of_board();
        }
        move_count++;
        this.getField().getBoard().next_turn();//initiate next turn
    }


    public boolean can_move_to(List<ChessField> fields, ChessField new_field){
        return fields.contains(new_field);
    }

    /**
     * method for adding field in accessible fields
     * return true/false if we find figure != this.figure.color --> used for queen,rook and bishop movement prep.
     * @param x
     * @param y
     * @param fields
     * @return
     */
    public boolean addField(int x, int y, List<ChessField> fields) {
        ChessField field = this.field.getBoard().getField(x, y);
        if (field == null){
            return true;
        }
        if (field.getFigure() == null){
            fields.add(field);
            return false;//we can continue
        }
        fields.add(field);
        return true;//end
    }

    /**
     * Method for getting all fields where can figure be placed
     * @return
     */
    public List<ChessField> getAccessibleFields(){

        List<ChessField> fields= AccessibleFields();
        if(this.On_turn()){
            return null;
        }
        return fields;
    }

    /**
     * Method for getting which color is on turn
     * @return
     */
    public boolean On_turn(){
        if(this.field.getBoard().Turn_counter % 2 != 0 && this.color == "white"){
            return true;//white on turn
        }else if(this.field.getBoard().Turn_counter % 2 == 0 && this.color == "black"){
            return true;//black on turn
        }
        return false;
    }

    /**
     * color revert
     * @param color
     * @return
     */
    public String get_second_color(String color){
        if (color == "white"){
            return "black";
        }
        return "white";
    }

    //all getters and setters
    public String getColor() {
        return color;
    }

    public void setField(ChessField field) {
        this.field = field;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMove_count(){
        return move_count;
    }

    public String getName() {
        return name;
    }

    public String getImageStream() {
        return imageStream;
    }

    public ChessField getField() {
        return field;
    }
}
