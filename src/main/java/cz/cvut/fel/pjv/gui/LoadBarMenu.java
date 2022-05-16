package cz.cvut.fel.pjv.gui;

import cz.cvut.fel.pjv.ChessLoader.ChessXmlLoader;
import cz.cvut.fel.pjv.game.ChessBoard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import java.net.URISyntaxException;

public class LoadBarMenu extends MenuButton {
    private ChessBoard board;
    public LoadBarMenu(cz.cvut.fel.pjv.game.ChessBoard board){
        this.board = board;
        getStyleClass().add("MenuButton");

        getItems().add(new LoadBarMenu.item("Load game 1", e-> {
            try {
                load_game("/saved_games/save_1.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));
        getItems().add(new LoadBarMenu.item("Load game 2", e-> {
            try {
                load_game("/saved_games/save_2.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));
        getItems().add(new LoadBarMenu.item("Load game 3", e-> {
            try {
                load_game("/saved_games/save_3.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));
    }

    private void load_game(String filename) throws URISyntaxException {
        ChessXmlLoader LoadXml = new ChessXmlLoader(board);
        board.clear_board();
        LoadXml.loadFromFile(board,filename);
    }

    private class item extends MenuItem {
        public item(String text, EventHandler<ActionEvent> event){
            setText(text);
            setOnAction(event);
        }
    }
}
