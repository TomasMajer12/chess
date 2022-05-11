package cz.cvut.fel.pjv.ChessLoader;
import cz.cvut.fel.pjv.figures.*;
import cz.cvut.fel.pjv.game.*;

import javax.xml.stream.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;



public class ChessXmlSaver {


    public byte[] save(ChessBoard board) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = null;
        try {
            writer = factory.createXMLStreamWriter(baos);
            writer.writeStartDocument();
            writer.writeStartElement("Board");
            writer.writeCharacters("\n");
            saveFigures(board.getFigures("white"), writer);
            saveFigures(board.getFigures("black"), writer);
            writer.writeEndElement();
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (XMLStreamException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }

    public void saveDataToFile(byte[] data, File file) {
        try {
            Files.write(Paths.get(file.toURI()), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFigures(List<Figure> figures, XMLStreamWriter writer) throws XMLStreamException {
        //writer.writeCharacters("\n");
        for (Figure figure : figures) {
            writer.writeCharacters("\t");
            writer.writeEmptyElement("Figure");
            writer.writeAttribute("Type", figure.getName());
            writer.writeAttribute("Color",figure.getColor());
            writer.writeAttribute("PosX",Integer.toString(figure.getX()));
            writer.writeAttribute("PosY",Integer.toString(figure.getY()));
            writer.writeCharacters("\n");
        }
        //writer.writeCharacters("\t");
    }

}
