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

  public String getSimulationType() {
    return getAttribute(root, "simulation");
  }

}
