package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import cz.cvut.fel.pjv.game.ChessGame;
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

    public Figure(String color, String name, ChessField field) {
        this.color = color;
        this.name = name;
        this.field = field;
        if (color == "black"){
            imageStream ="/images/figures/black_" + name + ".png";
        }else if(color == "white"){
            imageStream = "/images/figures/white_" + name + ".png";
        }else{
            imageStream ="/images/figures/black_" + name + ".png";
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

    public Figure move(ChessField field) {
        field.setFigure(this);
        if (this.field != null) {
            this.field.setFigure(null);
        }
        x = field.getX();
        y = field.getY();
        setField(field);
        field.getBoard().next_turn();
        return null;
    }

    public boolean can_move_to(List<ChessField> fields, ChessField new_field){
        if(fields.contains(new_field)){
            return true;
        }
        return false;
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
        }else if(field.getFigure().color != this.color){
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

    public String getPos() {
        return ((char) (x + 97)) + "" + ((char) (7 - y + 49));
    }


    public boolean On_turn(){
        if(this.field.getBoard().Turn_counter % 2 != 0 && this.color == "white"){
            return true;
        }else if(this.field.getBoard().Turn_counter % 2 == 0 && this.color == "black"){
            return true;
        }
        return false;
    }



    public abstract List<ChessField> AccessibleFields();

}
