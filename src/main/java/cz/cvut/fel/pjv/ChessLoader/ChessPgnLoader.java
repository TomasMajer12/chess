package cz.cvut.fel.pjv.ChessLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
King = "K"
Queen = "Q"
Rook = "R"
Knight = "N"
Pawn = ""
 */

public class ChessPgnLoader {
    public List load_from_file(String Filename){
        List<String> moves = new ArrayList<>();
        try {
            File inputFile = new File(getClass().getResource(Filename).toURI());
            Scanner myReader = new Scanner(inputFile);
            String Input;
            StringBuilder game = new StringBuilder();

            while (myReader.hasNextLine()){
                Input = myReader.nextLine();
                if(Input.startsWith("[")){
                    continue;
                }
                game.append(Input + "\n");
            }

            char c;
            int emptySpace_count;
            for(int i = 0; i < game.length(); i++) {
                c = game.charAt(i);
                if(c == '.'){//move start
                    i+=2;
                    emptySpace_count = 0;
                    StringBuilder move = new StringBuilder();
                    while (emptySpace_count < 2 && i <game.length()){
                        c = game.charAt(i);
                        move.append(c);
                        if(c == ' ' || c == '\n'){
                            emptySpace_count++;
                        }
                        i++;
                    }
                    moves.add(move.toString());
                }
            }
            /*for (String s:moves) {
                System.out.println(s);
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return moves;
    }
}
