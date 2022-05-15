package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class King extends Figure{
    public int check_count;
    public King(String color, String name, ChessField field) {
        super(color, name, field);
        this.check_count = 0;
    }

    @Override
    public List<ChessField> AccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        Set<ChessField> attackedFields;
        attackedFields = field.getBoard().getAttackedFields(field.getBoard().getTurn());


        for (int i = -1; i <= 1; i++ ){
            for (int j = -1; j <=1; j++){
                ChessField f;
                if ((f = field.getBoard().getField(field.getX() +i, field.getY() + j)) != null
                        && f != field
                        && !attackedFields.contains(f)
                        && (f.getFigure() == null
                        || f.getFigure() != null && f.getFigure().color != color)) {
                    fields.add(f);
                }
            }
        }

        if(this.getMove_count() == 0 && check_count == 0){
            //right
            ChessField rookfield = field.getBoard().getField(7,y);
            if(rookfield.getFigure() instanceof Rook && rookfield.getFigure().getMove_count() == 0){
                boolean canRight = true;
                for (int i = x+1; i < 7; i++){
                    ChessField currentfield = field.getBoard().getField(i,y);
                    if(currentfield.getFigure() != null || attackedFields.contains(currentfield)){
                        canRight = false;
                    }
                }
                if(canRight){
                    fields.add(field.getBoard().getField(6,y));
                }
            }
            //left
            rookfield = field.getBoard().getField(0,y);
            if(rookfield.getFigure() instanceof Rook && rookfield.getFigure().getMove_count() == 0){
                boolean canLeft = true;
                for (int i = x-1; i > 0; i--){
                    ChessField currentfield = field.getBoard().getField(i,y);
                    if(currentfield.getFigure() != null || attackedFields.contains(currentfield)){
                        canLeft  = false;
                    }
                }
                if(canLeft){
                    fields.add(field.getBoard().getField(2,y));
                }
            }
        }
        return fields;
    }

    public void castling_rook_move(){
        if(check_count == 0 && getMove_count() == 0){
            if(x == 2){
                field.getBoard().getField(0,y).getFigure().move(field.getBoard().getField(3,y));
            }else if(x == 6){
                field.getBoard().getField(7,y).getFigure().move(field.getBoard().getField(5,y));
            }

        }
    }

    public List<ChessField> can_attack_fields(){
        List<ChessField> fields = new ArrayList<>();
        for (int i = -1; i <= 1; i++ ){
            for (int j = -1; j <=1; j++){
                ChessField f;
                if ((f = field.getBoard().getField(field.getX() +i, field.getY() + j)) != null && f != field) {
                    fields.add(f);
                }
            }
        }
        return fields;
    }

    public boolean isCheck(){
        if(this.field.getBoard().getAttackedFields(this.get_second_color(color)).contains(field)){
            this.check_count++;
            return true;
        }
        return false;
    }
}
