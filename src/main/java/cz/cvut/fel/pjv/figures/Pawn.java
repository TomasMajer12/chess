package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import cz.cvut.fel.pjv.gui.PawnPopUpStage;

import java.util.ArrayList;
import java.util.List;

/**
 * King class extending Figure abstract class
 */
public class Pawn extends Figure{
    public Pawn(String color, String name, ChessField field) {
        super(color, name, field);
    }

    @Override
    public List<ChessField> AccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        ChessField movement_field;
        ChessField attack_field;

        if (this.color == "black"){
            //get movement fields
            if((movement_field = this.field.getBoard().getField(x,y+1)) != null && movement_field.getFigure() == null){
                fields.add(movement_field);
                if(y == 1 && ((movement_field = this.field.getBoard().getField(x,y+2)) != null) && movement_field.getFigure() == null){//first move can go +2
                    fields.add(movement_field);
                }
            }

            //fields that can be attacked by pawn
            if(x+1 < 8 && ((attack_field = this.field.getBoard().getField(x+1,y+1)) != null)
                    && attack_field.getFigure() != null && attack_field.getFigure().color != this.color){
                fields.add(attack_field);
            }

            if(x-1 >= 0 && ((attack_field = this.field.getBoard().getField(x-1,y+1)) != null)
                    && attack_field.getFigure() != null && attack_field.getFigure().color != this.color){
                fields.add(attack_field);
            }

            //enPassant
            if(y == 4){
                if (x - 1 >= 0 && (attack_field = this.field.getBoard().getField(x - 1, y)).getFigure() != null
                        && attack_field.getFigure().color != color //different color
                        && attack_field.getFigure() instanceof Pawn //it is pawn
                        && attack_field.getFigure().getMove_count() == 1) { //first pawn move was +2
                    fields.add(this.field.getBoard().getField(x - 1, y + 1));
                }
                if (x + 1 < 8 && (attack_field = this.field.getBoard().getField(x + 1, y)).getFigure() != null
                        && attack_field.getFigure().color != color//different color
                        && attack_field.getFigure() instanceof Pawn//it is pawn
                        && attack_field.getFigure().getMove_count() == 1) {//first pawn move was +2
                    fields.add(this.field.getBoard().getField(x + 1, y + 1));
                }
            }

        //white color
        }else{
            //get movement fields
            if((movement_field = this.field.getBoard().getField(x,y-1)) != null && movement_field.getFigure() == null){
                fields.add(movement_field);
                if(y == 6 && ((movement_field = this.field.getBoard().getField(x,y-2)) != null) && movement_field.getFigure() == null){//first move can go +2
                    fields.add(movement_field);
                }
            }
            //fields that can be attacked by pawn
            if(x+1 < 8 && ((attack_field = this.field.getBoard().getField(x+1,y-1)) != null)
                    && attack_field.getFigure() != null && attack_field.getFigure().color != this.color){
                fields.add(attack_field);
            }

            if(x-1 >= 0 && ((attack_field = this.field.getBoard().getField(x-1,y-1)) != null)
                    && attack_field.getFigure() != null && attack_field.getFigure().color != this.color){
                fields.add(attack_field);
            }

            //enPassant
            if(y == 3){
                if (x - 1 >= 0 && (attack_field = this.field.getBoard().getField(x - 1, y)).getFigure() != null
                        && attack_field.getFigure().color != color//different color
                        && attack_field.getFigure() instanceof Pawn//it is pawn
                        && attack_field.getFigure().getMove_count() == 1) {//first pawn move was +2
                    fields.add(this.field.getBoard().getField(x - 1, y - 1));
                }
                if (x + 1 < 8 && (attack_field = this.field.getBoard().getField(x + 1, y)).getFigure() != null
                        && attack_field.getFigure().color != color//different color
                        && attack_field.getFigure() instanceof Pawn//it is pawn
                        && attack_field.getFigure().getMove_count() == 1) {//first pawn move was +2
                    fields.add(this.field.getBoard().getField(x + 1, y - 1));
                }
            }
        }
        return fields;
    }

    /**
     * Post turn action for en passant move --> remove pawn
     */
    public void enPassant_move(){
        Figure f;
        if(color == "black" && field.getY() == 5){
            if((f = field.getBoard().getField(x,4).getFigure()) != null && f.getMove_count() == 1 && f instanceof Pawn){
                f.getField().setFigure(null);
            }
        }
        if(color == "white" && field.getY() == 2){
            if((f = field.getBoard().getField(x,3).getFigure()) != null && f.getMove_count() == 1 && f instanceof Pawn){
                f.getField().setFigure(null);
            }
        }
    }

    /**
     * fields that can be attacked by pawn
     * @return
     */
    public List<ChessField> can_attack_fields(){

        List<ChessField> attack_fields = new ArrayList<>();
        ChessField field;
        if(this.color == "black"){

            if(x+1 < 8 && ((field = this.field.getBoard().getField(x+1,y+1)) != null)){
                attack_fields.add(field);
            }

            if(x-1 >= 0 && ((field = this.field.getBoard().getField(x-1,y+1)) != null)){
                attack_fields.add(field);
            }
        }else{
            if(x+1 < 8 && ((field = this.field.getBoard().getField(x+1,y-1)) != null)){
                attack_fields.add(field);
            }

            if(x-1 >= 0 && ((field = this.field.getBoard().getField(x-1,y-1)) != null)){
                attack_fields.add(field);
            }
        }
        return attack_fields;
    }

    /**
     * Creating PopUpWindow for choosing which figure we want
     * when reaching end with pawn
     */
    public void reach_end_of_board(){
        if(this.color == "black" && field.getY() == 7){
            new PawnPopUpStage(this);
        }else if (this.color == "white" && field.getY() == 0){
            new PawnPopUpStage(this);
        }
    }
}
