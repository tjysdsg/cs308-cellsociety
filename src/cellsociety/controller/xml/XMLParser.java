package cellsociety.controller.xml;

import Model.Simulation;
import Model.State;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * This class handles parsing XML files and returning a completed object.
 *
 * @author Rhondu Smithwick
 * @author Robert C. Duvall
 */
public abstract class XMLParser {

  // Readable error message that can be displayed by the GUI
  public static final String ERROR_MESSAGE = "XML file does not represent %s";
  public static final String ERROR_SPEED = "Speed is not valid %ld";
  public static final String ERROR_COORDINATE = "Coordinate is not valid %d";
  public static final String ERROR_STATE = "State is not valid %d";
  // name of root attribute that notes the type of file expecting to parse
  private final String FILENAME;
  // keep only one documentBuilder because it is expensive to make and can reset it before parsing
  private final DocumentBuilder DOCUMENT_BUILDER;
  public File xmlFile;
  public State[] states;
  public int sizeX;
  public int stateRange;
  public Simulation simulation;
  public Element root;


  /**
   * Create parser for XML files of given filename.
   */
  public XMLParser(String fileName) throws XMLException {
    DOCUMENT_BUILDER = getDocumentBuilder();
    FILENAME = fileName;
    xmlFile = new File("data/" + fileName);
  }


  /**
   * get simulation model
   *
   * @return
   * @throws XMLException
   */
  public abstract Simulation getSimulation() throws XMLException;

  public abstract void initStateArray();


  public void initSimulation() {
    initCell();
    initSpeed();
  }


  public void initCell() throws XMLException {
    NodeList stateList = root.getElementsByTagName("cell");
    for (int i = 0; i < stateList.getLength(); i++) {
      Element cell = (Element) stateList.item(i);
      int row = getIntTextValue(cell, "row");
      int col = getIntTextValue(cell, "col");
      checkCoordinate(row);
      checkCoordinate(col);
      int stateNum = getIntTextValue(cell, "state");
      checkStates(stateNum);
      State cellState = states[stateNum];
      simulation.setState(row, col, cellState, true);
    }
  }


  public void checkCoordinate(int n) throws XMLException {
    if (n < 0 || n >= sizeX) {
      throw new XMLException(ERROR_COORDINATE, n);
    }
  }

  public void checkStates(int n) throws XMLException {
    if (n < 0 || n >= stateRange) {
      throw new XMLException(ERROR_STATE, n);
    }
  }

  public int getIntTextValue(Element e, String tagName) {
    return Integer.parseInt(getTextValue(e, tagName));
  }


  // get root element of an XML file
  public Element getRootElement() throws XMLException {
    try {
      DOCUMENT_BUILDER.reset();
      Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
      return xmlDocument.getDocumentElement();
    } catch (SAXException | IOException e) {
      throw new XMLException(e);
    }
  }


  // get value of Element's attribute
  public String getAttribute(Element e, String attributeName) {
    return e.getAttribute(attributeName);
  }

  // get value of Element's text
  public String getTextValue(Element e, String tagName) {
    NodeList nodeList = e.getElementsByTagName(tagName);
    if (nodeList != null && nodeList.getLength() > 0) {
      return nodeList.item(0).getTextContent();
    } else {
      // FIXME: empty string or exception? In some cases it may be an error to not find any text
      return "";
    }
  }


  public int getGridSize() {
    Element gridSize = ((Element) root.getElementsByTagName("grid").item(0));
    return getIntTextValue(gridSize, "sizex");
  }

  public void initSpeed() {
    double sp = getDoubleTextValue(root, "speed");
    checkSpeed(sp);
    simulation.setConfig("gameSpeed", sp);
  }

  public void checkSpeed(double a) throws XMLException {
    if (a <= 0) {
      throw new XMLException(ERROR_SPEED, a);
    }
  }

  public double getDoubleTextValue(Element e, String tagName) {
    return Double.parseDouble(getTextValue(e, tagName));
  }

  // boilerplate code needed to make a documentBuilder
  private DocumentBuilder getDocumentBuilder() throws XMLException {
    try {
      return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new XMLException(e);
    }
  }
}
