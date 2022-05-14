package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class King extends Figure{
    public King(String color, String name, ChessField field) {
        super(color, name, field);
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
        return fields;
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
            return true;
        }
        return false;
    }
}
