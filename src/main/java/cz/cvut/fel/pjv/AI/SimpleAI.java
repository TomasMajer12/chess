package cz.cvut.fel.pjv.AI;

import cz.cvut.fel.pjv.figures.Figure;
import cz.cvut.fel.pjv.game.ChessBoard;
import cz.cvut.fel.pjv.game.ChessField;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleAI {
    private ChessBoard board;
    private String color;

    public SimpleAI(ChessBoard board, String color) {
        this.board = board;
        this.color = color;
    }

    public void make_move(){
        List<Figure> figures = board.getFigures(color);
        List<ChessField> fields = new ArrayList<>();

        for (Figure f: figures) {
            for(ChessField field: f.getAccessibleFields()){
                if(field.getFigure() != null && field.getFigure().getColor() == f.getColor()) {
                    continue;
                }
                fields.add(field);
            }
        }
        Random rand = new Random();
        ChessField randomField = fields.get(rand.nextInt(fields.size()));

        System.out.println("Random select field = " + randomField.getX() + " " + randomField.getY());
        for(Figure f: figures){
            for(ChessField field: f.getAccessibleFields()){
                if(field == randomField){
                    System.out.println("Moving " + f.getName() +" on x=" + field.getX() + " y=" + field.getY());
                    f.move(randomField);
                    return;
                }
            }
        }
    }
}
