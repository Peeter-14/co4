package nye;

import java.io.IOException;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import  javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class FileHandler {

    public static void saveGame(Board board, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    writer.write(board.getCell(i, j));
                }
                writer.newLine();
            }
        }
    }

    public static Board loadGame(String filename) throws IOException {
        Board board = new Board(6, 7);
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    board.setCell(row, col, line.charAt(col) + "");
                }
                row++;
            }
        }
        return board;
    }

    public static void saveGameAsXML(Board board, String filename) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("game");
        doc.appendChild(rootElement);

        Element boardElement = doc.createElement("board");
        rootElement.appendChild(boardElement);

        for (int i = 0; i < board.getRows(); i++) {
            Element rowElement = doc.createElement("row");
            for (int j = 0; j < board.getColumns(); j++) {
                Element cellElement = doc.createElement("cell");
                cellElement.appendChild(doc.createTextNode(board.getCell(i, j)));
                rowElement.appendChild(cellElement);
            }
            boardElement.appendChild(rowElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
    }

    public static Board loadGameFromXML(String filename) throws Exception {
        Board board = new Board(6, 7);

        File file = new File(filename);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        NodeList rows = doc.getElementsByTagName("row");
        for (int i = 0; i < rows.getLength(); i++) {
            Node rowNode = rows.item(i);
            NodeList cells = rowNode.getChildNodes();
            for (int j = 0; j < cells.getLength(); j++) {
                if (cells.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element cell = (Element) cells.item(j);
                    board.setCell(i, j, cell.getTextContent());
                }
            }
        }

        return board;
    }
    File pdfFile = new File("C:/src/main/resources/tervezesi mintak.pdf");

}
