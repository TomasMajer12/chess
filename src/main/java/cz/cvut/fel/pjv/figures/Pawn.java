package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure{
    public Pawn(String color, String name, ChessField field) {
        super(color, name, field);
    }

    @Override
    public List<ChessField> AccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        ChessField movement_field;
        ChessField attack_fields;

        if (this.color == "black"){
            if((movement_field = this.field.getBoard().getField(x,y+1)) != null && movement_field.getFigure() == null){
                fields.add(movement_field);
                if(y == 1 && ((movement_field = this.field.getBoard().getField(x,y+2)) != null) && movement_field.getFigure() == null){
                    fields.add(movement_field);
                }
            }

            if(x+1 < 8 && ((attack_fields = this.field.getBoard().getField(x+1,y+1)) != null)
                    && attack_fields.getFigure() != null && attack_fields.getFigure().color != this.color){
                fields.add(attack_fields);
            }

            if(x-1 >= 0 && ((attack_fields = this.field.getBoard().getField(x-1,y+1)) != null)
                    && attack_fields.getFigure() != null && attack_fields.getFigure().color != this.color){
                fields.add(attack_fields);
            }


        }else{
            if((movement_field = this.field.getBoard().getField(x,y-1)) != null && movement_field.getFigure() == null){
                fields.add(movement_field);
                if(y == 6 && ((movement_field = this.field.getBoard().getField(x,y-2)) != null) && movement_field.getFigure() == null){
                    fields.add(movement_field);
                }
            }

            if(x+1 < 8 && ((attack_fields = this.field.getBoard().getField(x+1,y-1)) != null)
                    && attack_fields.getFigure() != null && attack_fields.getFigure().color != this.color){
                fields.add(attack_fields);
            }

            if(x-1 >= 0 && ((attack_fields = this.field.getBoard().getField(x-1,y-1)) != null)
                    && attack_fields.getFigure() != null && attack_fields.getFigure().color != this.color){
                fields.add(attack_fields);
            }

        }
        return fields;
    }

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
}
