package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;

public class SugarXMLParser extends XMLParser{

  /**
   * Create parser for XML files of given filename.
   *
   * @param fileName
   */
  public SugarXMLParser(String fileName) throws XMLException {
    super(fileName);
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    return null;
  }

  @Override
  public void initStateArray() {

  }
}
