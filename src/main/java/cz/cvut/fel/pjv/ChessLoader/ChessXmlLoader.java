package cz.cvut.fel.pjv.ChessLoader;


import cz.cvut.fel.pjv.figures.*;
import cz.cvut.fel.pjv.game.ChessBoard;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class ChessXmlLoader {
    ChessBoard board;

    public ChessXmlLoader(ChessBoard board) {
        this.board = board;
    }

    public void loadFromFile(ChessBoard board, String Filename) {

        try {

            File inputFile = new File(getClass().getResource(Filename).toURI());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Figure");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Figure figure = createFigure(eElement.getAttribute("Color"),eElement.getAttribute("Type"),
                                    eElement.getAttribute("PosX"),eElement.getAttribute("PosY"));
                    board.getField(figure.getX(),figure.getY()).setFigure(figure);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Figure createFigure(String color,String type, String PosX, String PosY){
        int x = Integer.parseInt(PosX);
        int y = Integer.parseInt(PosY);

        if(color.contains("c")){
            color = "black";
        }else{
            color = "white";
        }

        Figure figure;
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("Position not on board:<" + PosX + "><" + PosY + ">");
        }

        switch (type.toLowerCase()) {
            case "rook":
                figure = new Rook(color,type, board.getField(x, y));
                break;
            case "knight":
                figure = new Knight(color,type, board.getField(x, y));
                break;
            case "bishop":
                figure = new Bishop(color,type, board.getField(x, y));
                break;
            case "queen":
                figure = new Queen(color,type, board.getField(x, y));
                break;
            case "king":
                figure = new King(color,type, board.getField(x, y));
                break;
            case "pawn":
                figure = new Pawn(color,type, board.getField(x, y));
                break;
            default:
                throw new IllegalArgumentException("Unknown Figure:" + type);
        }
        return figure;
    }
}
