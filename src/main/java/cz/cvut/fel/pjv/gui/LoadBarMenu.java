package cz.cvut.fel.pjv.gui;

import cz.cvut.fel.pjv.ChessLoader.ChessPgnLoader;
import cz.cvut.fel.pjv.ChessLoader.ChessXmlLoader;
import cz.cvut.fel.pjv.game.ChessBoard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import java.net.URISyntaxException;

/**
 * Simple menu for Loading games InGame
 */
public class LoadBarMenu extends MenuButton {
    private ChessBoard board;
    public LoadBarMenu(cz.cvut.fel.pjv.game.ChessBoard board){
        this.board = board;
        getStyleClass().add("MenuButton");

        //add events for game loading
        getItems().add(new LoadBarMenu.item("Load xml game 1", e-> {
            try {
                load_game("/saved_games/save_1.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));
        getItems().add(new LoadBarMenu.item("Load xml game 2", e-> {
            try {
                load_game("/saved_games/save_2.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));
        getItems().add(new LoadBarMenu.item("Load xml game 3", e-> {
            try {
                load_game("/saved_games/save_3.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));

        getItems().add(new LoadBarMenu.item("Load pgn game 1", e-> {
            try {
                load_game_pgn("/pgn/pgn_save1.pgn");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));

        getItems().add(new LoadBarMenu.item("Load pgn game 2", e-> {
            try {
                load_game_pgn("/pgn/pgn_save2.pgn");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));

        getItems().add(new LoadBarMenu.item("Load pgn game 3", e-> {
            try {
                load_game_pgn("/pgn/pgn_save3.pgn");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));
    }

    /**
     * Method for Ingame loading of other games in xml
     * @param filename
     * @throws URISyntaxException
     */
    private void load_game(String filename) throws URISyntaxException {
        ChessXmlLoader LoadXml = new ChessXmlLoader(board);
        board.clear_board();//clear used board
        LoadXml.loadFromFile(board,filename); //set new board
    }

    /**
     * Method for Ingame pgn loading
     * @param filename
     */
    private void load_game_pgn(String filename){
        ChessXmlLoader LoadXml = new ChessXmlLoader(board);
        LoadXml.loadFromFile(board,"/saved_games/starter_board.xml");
        ChessPgnLoader pgnLoader = new ChessPgnLoader();
        pgnLoader.load_pgn_game(board,filename);
    }

    /**
     * private class for menuItems
     */
    private class item extends MenuItem {
        public item(String text, EventHandler<ActionEvent> event){
            setText(text);
            setOnAction(event);
        }
    }
}
