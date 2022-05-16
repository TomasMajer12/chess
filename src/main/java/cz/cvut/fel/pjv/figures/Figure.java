package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import javafx.scene.input.DataFormat;

import java.io.Serializable;
import java.util.List;

public abstract class Figure implements Serializable {
    public transient static final DataFormat CHESS_FIGURE = new DataFormat("chess.figure");
    public transient ChessField field; //--> not serializable

    public String getColor() {
        return color;
    }

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

    public void move(ChessField move_field) {
        move_field.setFigure(this);
        if (this.field != null) {
            this.field.setFigure(null);
        }
        x =  move_field.getX();
        y =  move_field.getY();
        setField(move_field);
        if(this instanceof King){
            ((King) this).castling_rook_move();
        }
        if(this instanceof Pawn){
            ((Pawn) this).enPassant_move();
        }
        move_count++;
    }

    public boolean can_move_to(List<ChessField> fields, ChessField new_field){
        return fields.contains(new_field);
    }

    public String getName() {
        return name;
    }

    public String getImageStream() {
        return imageStream;
    }

    public boolean addField(int x, int y, List<ChessField> fields) {

        ChessField field = this.field.getBoard().getField(x, y);
        if (field == null){
            return true;
        }
        if (field.getFigure() == null){
            fields.add(field);
            return false;//we can continue
        }else{
            fields.add(field);
        }
        return true;//end
    }

    public List<ChessField> getAccessibleFields(){

        List<ChessField> fields= AccessibleFields();
        if(this.On_turn()){
            return null;
        }
        return fields;
    }

    public ChessField getField() {
        return field;
    }

    public boolean On_turn(){
        if(this.field.getBoard().Turn_counter % 2 != 0 && this.color == "white"){
            return true;
        }else if(this.field.getBoard().Turn_counter % 2 == 0 && this.color == "black"){
            return true;
        }
        return false;
    }

    public String get_second_color(String color){
        if (color == "white"){
            return "black";
        }
        return "white";
    }

    public abstract List<ChessField> AccessibleFields();

}
