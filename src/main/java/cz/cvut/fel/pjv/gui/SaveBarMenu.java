package cz.cvut.fel.pjv.gui;

import cz.cvut.fel.pjv.ChessLoader.ChessXmlSaver;
import cz.cvut.fel.pjv.game.ChessBoard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.net.URISyntaxException;

public class SaveBarMenu extends Menu {
    private ChessBoard board;
    public SaveBarMenu(ChessBoard board){
        this.board = board;

        getItems().add(new item("Save 1", e-> {
            try {
                save_game("/saved_games/save_1.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));
        getItems().add(new item("Save 2", e-> {
            try {
                save_game("/saved_games/save_2.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));
        getItems().add(new item("Save 3", e-> {
            try {
                save_game("/saved_games/save_3.xml");
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }));
    }

    private void save_game(String filename) throws URISyntaxException {
        File OutputFile = new File(getClass().getResource(filename).toURI());
        ChessXmlSaver SaveXml = new ChessXmlSaver();
        SaveXml.saveDataToFile(SaveXml.save(board), OutputFile);
    }

    private class item extends MenuItem{
        public item(String text, EventHandler<ActionEvent> event){
            setText(text);
            setOnAction(event);
        }
    }
}
