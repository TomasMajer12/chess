package cz.cvut.fel.pjv.game;

import cz.cvut.fel.pjv.figures.*;

import javafx.scene.layout.GridPane;
import java.util.*;


public class ChessBoard extends GridPane {

    private ChessField[] fields = new ChessField[64];
    private Set<ChessField> WhiteAttackedFields = new HashSet<>();
    private Set<ChessField> BlackAttackedFields = new HashSet<>();
    private Map<String, Set<ChessField>> attackedFields = new HashMap<>();
    public int Turn_counter;
    public ChessBoard() {
        for (int i = 0; i < 64; i++) {
            int x = getX(i);
            int y = getY(i);
            ChessField field = new ChessField(this, x, y);
            add(field, x, y);
            fields[i] = field;
        }
        Turn_counter = 0;
    }

    public ChessField getField(int x, int y) {
        return x < 0 || x > 7 || y < 0 || y > 7 ? null : fields[y * 8 + x];
    }

    public void next_turn(){
        updateAttackedFields();
        state_test();
        Turn_counter++;
        updateAttackedFields();
        state_test();
    }

    public String getTurn() {
        return Turn_counter % 2 == 0 ? "black" : "white";
    }

    public void state_test(){
        King king = get_king(getTurn());
        if (king != null) {
            if (king.isCheck()){
                System.out.println("check" + getTurn());
            }
        }
    }

    private int getX(int index) {
        return index % 8;
    }

    private int getY(int index) {
        return (index - getX(index)) / 8;
    }

    public void updateAttackedFields(){
        if(Turn_counter % 2 == 0){
            WhiteAttackedFields = new HashSet<>();
            for(ChessField f : fields){
                if (f.figure != null && f.figure.getColor() == "white"){
                    if(f.figure instanceof Pawn && ((Pawn) f.figure).can_attack_fields() != null){
                        WhiteAttackedFields.addAll(((Pawn) f.figure).can_attack_fields());
                    }else if(f.figure instanceof King && ((King) f.figure).can_attack_fields() != null){
                        WhiteAttackedFields.addAll(((King) f.figure).can_attack_fields());
                    } else if(f.getFigure().getAccessibleFields() != null){
                        WhiteAttackedFields.addAll(f.getFigure().getAccessibleFields());
                    }
                }
            }
        }else{
            BlackAttackedFields = new HashSet<>();
            for(ChessField f : fields){
                if (f.figure != null && f.figure.getColor() == "black"){
                    if(f.figure instanceof Pawn && ((Pawn) f.figure).can_attack_fields() != null) {
                        BlackAttackedFields.addAll(((Pawn) f.figure).can_attack_fields());
                    }else if(f.figure instanceof King && ((King) f.figure).can_attack_fields() != null){
                        BlackAttackedFields.addAll(((King) f.figure).can_attack_fields());
                    }else if(f.getFigure().getAccessibleFields() != null){
                        BlackAttackedFields.addAll(f.getFigure().getAccessibleFields());
                    }
                }
            }
        }
    }

    public Set<ChessField> getAttackedFields(String color) {
        if(color == "white"){
            return WhiteAttackedFields;
        }
        return BlackAttackedFields;
    }

    public List<Figure> getFigures(String color) {
        List<Figure> figures = new ArrayList<>();
        for(ChessField field : fields){
            if(field.figure != null && field.figure.getColor() == color){
                figures.add(field.figure);
            }
        }
        return figures;
    }

    public King get_king(String color){
        for (ChessField field : fields){
            if(field.figure instanceof King && field.figure.getColor() == color){
                return (King) field.figure;
            }
        }
        return null;
    }

}
