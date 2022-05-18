package cz.cvut.fel.pjv.gui;

import cz.cvut.fel.pjv.ChessLoader.ChessXmlSaver;
import cz.cvut.fel.pjv.game.ChessBoard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import java.io.File;
import java.net.URISyntaxException;

/**
 * Simple menu for saving games InGame
 */
public class SaveBarMenu extends MenuButton {
    private ChessBoard board;
    public SaveBarMenu(ChessBoard board){
        this.board = board;
        getStyleClass().add("MenuButton");


        getItems().add(new item("Save game to save 1", e-> {
            try {
                save_game("/saved_games/save_1.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));
        getItems().add(new item("Save game to save 2", e-> {
            try {
                save_game("/saved_games/save_2.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));
        getItems().add(new item("Save game to save 3", e-> {
            try {
                save_game("/saved_games/save_3.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));
    }

    /**
     * Save game in given file
     * @param filename
     * @throws URISyntaxException
     */
    private void save_game(String filename) throws URISyntaxException {
        File OutputFile = new File(getClass().getResource(filename).toURI());
        ChessXmlSaver SaveXml = new ChessXmlSaver();
        SaveXml.saveDataToFile(SaveXml.save(board), OutputFile);
    }

    /**
     * private class for menuItems
     */
    private class item extends MenuItem{
        public item(String text, EventHandler<ActionEvent> event){
            setText(text);
            setOnAction(event);
        }
    }
}
