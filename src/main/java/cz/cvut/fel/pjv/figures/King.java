package cz.cvut.fel.pjv.figures;

import cz.cvut.fel.pjv.game.ChessField;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * King class extending Figure abstract class
 */
public class King extends Figure{
    public int check_count;
    private String tag = "K";
    public King(String color, String name, ChessField field) {
        super(color, name, field);
        this.check_count = 0;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public List<ChessField> AccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        Set<ChessField> attackedFields;
        attackedFields = field.getBoard().getAttackedFields(field.getBoard().getTurn()); //get all fields accesibble by oponent color


        for (int i = -1; i <= 1; i++ ){
            for (int j = -1; j <=1; j++){
                ChessField f;
                if ((f = field.getBoard().getField(field.getX() +i, field.getY() + j)) != null //field is on the board
                        && f != field  //not the same field
                        && !attackedFields.contains(f) //not in check
                        && (f.getFigure() == null //not figure
                        || f.getFigure() != null && f.getFigure().color != color)) {//oponent figure
                    fields.add(f);
                }
            }
        }

        //castling
        if(this.getMove_count() == 0 && check_count == 0){
            //right
            ChessField rookfield = field.getBoard().getField(7,y);
            if(rookfield.getFigure() instanceof Rook && rookfield.getFigure().getMove_count() == 0){ //rook in on the right and hasnt moved
                boolean canRight = true;
                for (int i = x+1; i < 7; i++){
                    ChessField currentfield = field.getBoard().getField(i,y);
                    if(currentfield.getFigure() != null || attackedFields.contains(currentfield)){ //field not in check and without figure
                        canRight = false;
                    }
                }
                if(canRight){
                    fields.add(field.getBoard().getField(6,y)); //we can make castling
                }
            }
            //left
            rookfield = field.getBoard().getField(0,y);
            if(rookfield.getFigure() instanceof Rook && rookfield.getFigure().getMove_count() == 0){//rook in on the left and hasnt moved
                boolean canLeft = true;
                for (int i = x-1; i > 0; i--){
                    ChessField currentfield = field.getBoard().getField(i,y);
                    if(currentfield.getFigure() != null || attackedFields.contains(currentfield)){//field not in check and without figure
                        canLeft  = false;
                    }
                }
                if(canLeft){
                    fields.add(field.getBoard().getField(2,y));//we can make castling
                }
            }
        }
        return fields;
    }

    /**
     * post turn action --> if castling move rook
     */
    public void castling_rook_move(){
        if(check_count == 0 && getMove_count() == 0){
            if(x == 2){
                field.getBoard().addToPGN("O-O-O");
                field.getBoard().getField(0,y).getFigure().move(field.getBoard().getField(3,y),true);
                field.getBoard().Turn_counter--;
            }else if(x == 6){
                field.getBoard().addToPGN("O-O");
                field.getBoard().getField(7,y).getFigure().move(field.getBoard().getField(5,y),true);
                field.getBoard().Turn_counter--;
            }

        }
    }

    /**
     * all fields that can be attacked by king
     * @return
     */
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


    /**
     * Checking if king is in check
     * @return
     */
    public boolean isCheck(){
        if(this.field.getBoard().getAttackedFields(this.get_second_color(color)).contains(field)){
            this.check_count++;
            return true;
        }
        return false;
    }
}
