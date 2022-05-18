package cz.cvut.fel.pjv.ChessLoader;

import cz.cvut.fel.pjv.figures.*;
import cz.cvut.fel.pjv.game.ChessBoard;
import cz.cvut.fel.pjv.game.ChessField;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static cz.cvut.fel.pjv.ChessLoader.PGNViewer.*;

/**
 * class used for loading games in pgn format
 */
public class ChessPgnLoader {
    List<String> moves;

    /**
     * Method for getting all moves from PGN format and store it in array
     * @param Filename
     * @return
     */
    public List load_from_file(String Filename){
        List<String> moves = new ArrayList<>();
        try {
            File inputFile = new File(getClass().getResource(Filename).toURI());
            Scanner myReader = new Scanner(inputFile);
            String Input;
            StringBuilder game = new StringBuilder();

            while (myReader.hasNextLine()){
                Input = myReader.nextLine();
                if(Input.startsWith("[")){ //skip headers
                    continue;
                }
                game.append(Input + "\n"); //add next line and give back \n
            }

            char c;
            int emptySpace_count;
            for(int i = 0; i < game.length(); i++) {
                c = game.charAt(i);
                if(c == '.'){//move start
                    i+=2;
                    emptySpace_count = 0;
                    StringBuilder move = new StringBuilder();
                    while (emptySpace_count < 2 && i <game.length()){ //read whole move (white + black)
                        c = game.charAt(i);
                        move.append(c);
                        if(c == ' ' || c == '\n'){ //end of move
                            emptySpace_count++;
                        }
                        i++;
                    }
                    moves.add(move.toString()); //store double move in array
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        this.moves = getallmoves(moves); //get formated moves
        return getallmoves(moves);
    }

    /**
     * Method for making all moves in PGN file
     * @param board
     * @param Filename
     */
    public void load_pgn_game(ChessBoard board,String Filename){
        this.moves = load_from_file(Filename);
        make_all_moves(board);
    }

    /**
     * Check for castling move
     * @param move
     * @param board
     */
    public void castling_move(String move,ChessBoard board){
        King king = board.get_king(board.revertColor(board.getTurn()));
        if(move.length() >= 4){
            board.getField(king.getX(),king.getY()).getFigure().move(board.getField(6,king.getY()));
        }else{
            board.getField(king.getX(),king.getY()).getFigure().move(board.getField(2,king.getY()));
        }
    }

    /**
     * Method for calling all moves on board
     * @param board
     */
    private void make_all_moves(ChessBoard board){
        for (int i = 0 ; i<moves.size(); i++){
            if(moves.get(i).startsWith("O")){
                System.out.println("Castling move");
                castling_move(moves.get(i),board);
            }else{
                move_figure(moves.get(i),board);
            }
        }
    }

    /**
     * Field figure movement
     * @param move
     * @param board
     */
    private void move_figure( String move,ChessBoard board){
        int x = get_XCoords(move);
        int y = get_YCoords(move);
        List<Figure> figures = board.getFigures(board.revertColor(board.getTurn()));
        ChessField field = board.getField(x,y);
        for (Figure f:figures) {
            if(f.getAccessibleFields() == null){
                continue;
            }
            if(f.can_move_to(f.getAccessibleFields(), field)){
                switch (movingFigure(move)){
                    case 'K':
                        if(f instanceof King){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                        }
                        break;
                    case 'Q':
                        if(f instanceof Queen){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                        }
                        break;
                    case 'R':
                        if(f instanceof Rook){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                        }
                        break;
                    case 'N':
                        if(f instanceof Knight){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                        }
                        break;
                    case 'B':
                        if(f instanceof Bishop){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                        }
                        break;
                    default:
                        if(f instanceof Pawn){
                            board.getField(f.getX(),f.getY()).getFigure().move(board.getField(x,y));
                        }
                        break;
                }
            }
        }
    }

    /**
     * Method for getting formated list of moves
     * @param moves
     * @return
     */
    private List<String> getallmoves(List<String> moves){
        List<String> allmoves = new ArrayList<>();
        for (String doublemove:moves) {
            String[] m = doublemove.split(" ");
            for (String s:m) {
                allmoves.add(s.replaceAll("\\s+","")); //strip all empty spaces
            }
        }
        return allmoves;
    }

}
