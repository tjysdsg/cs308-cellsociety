package controller.xml;

import java.util.Map;
import javafx.beans.binding.ObjectExpression;
import model.Simulation;
import model.State;
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
  public static final String ERROR_COORDINATE = "Coordinate is not valid row: %d col: %d";
  public static final String ERROR_STATE = "State is not valid %d";
  public static final String GRID_TAG = "grid";
  public static final String SIZEX_TAG = "sizex";
  public static final String AUTHOR_TAG = "author";
  public static final String SIZEY_TAG = "sizey";
  public static final String CELL_TAG = "cell";
  public static final String ROW_TAG = "row";
  public static final String COL_TAG = "col";
  public static final String STATE_TAG = "state";
  public static final String SPEED_TAG = "speed";
  public static final String _TAG = "title";
  public static final String TTILE_TAG = _TAG;
  public static final String DESCRIPTION_TAG = "description";
  public static final String DATA_GAMECONFIG = "data/gameconfig/";
  // name of root attribute that notes the type of file expecting to parse
  private final String FILENAME;
  // keep only one documentBuilder because it is expensive to make and can reset it before parsing
  private final DocumentBuilder DOCUMENT_BUILDER;
  public File xmlFile;
  public State[] states;
  public int sizeX;
  public int sizeY;
  public int stateRange;
  public String author;
  public String description;
  public String title;
  public Simulation simulation;
  public Element root;


  /**
   * Create parser for XML files of given filename.
   */
  public XMLParser(String fileName) throws XMLException {
    DOCUMENT_BUILDER = getDocumentBuilder();
    FILENAME = fileName;
    xmlFile = new File(DATA_GAMECONFIG + fileName);
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
//    initSpeed();
    getAuthor();
    getDescription();
    getTitle();
  }


  public void initCell() throws XMLException {
    NodeList stateList = root.getElementsByTagName(CELL_TAG);
    for (int i = 0; i < stateList.getLength(); i++) {
      Element cell = (Element) stateList.item(i);
      int row = getIntTextValue(cell, ROW_TAG);
      int col = getIntTextValue(cell, COL_TAG);
      checkCoordinate(row,col);
      int stateNum = getIntTextValue(cell, STATE_TAG);
      checkStates(stateNum);
      State cellState = states[stateNum];
      simulation.setState(row, col, cellState, true);
    }
  }

  public  void addXMLDescription(Map<String, Object> map){
    map.put(AUTHOR_TAG,author );
    map.put(TTILE_TAG, title);
    map.put(DESCRIPTION_TAG, description);
    return;
  }

  public void checkCoordinate(int row, int col) throws XMLException {
    if (row < 0 || row >= sizeX || col<0 || col >=sizeY) {
      throw new XMLException(ERROR_COORDINATE, row, col);
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
      return nodeList.item(0).getTextContent().trim();
    } else {
      // FIXME: empty string or exception? In some cases it may be an error to not find any text
      return "";
    }
  }

  public void getAuthor(){
    author = getTextValue(root, AUTHOR_TAG);
  }

  public void getTitle(){
    title = getTextValue(root, TTILE_TAG);
  }

  public void getDescription(){
    description = getTextValue(root, DESCRIPTION_TAG);
  }



  public int getGridSizeX() {
    Element gridSize = ((Element) root.getElementsByTagName(GRID_TAG).item(0));
    return getIntTextValue(gridSize, SIZEX_TAG);
  }

  public int getGridSizeY() {
    Element gridSize = ((Element) root.getElementsByTagName(GRID_TAG).item(0));
    return getIntTextValue(gridSize, SIZEY_TAG);
  }
  public void initSpeed() {
    double sp = getDoubleTextValue(root, SPEED_TAG);
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
