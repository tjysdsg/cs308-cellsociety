package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;

public class SimulationTypeParser extends XMLParser {

  public SimulationTypeParser(String fileName) {
    super(fileName);
    root = getRootElement();
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    return null;
  }

  /**
   * No needed for this parser
   */
  @Override
  public void initStateArray() {
    return;
  }

  public String getSimulationType() throws  XMLException {
    try{
      return getAttribute(root, "simulation");
    }catch (Exception e){
      throw new XMLException("Invalid simulation type");
    }
  }

}
