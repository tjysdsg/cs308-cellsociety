package cellsociety.controller.xml;

import Model.Simulation;

public class SimulationParser extends XMLParser {

  public SimulationParser(String fileName) {
    super(fileName);
    root = getRootElement();
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    return null;
  }

  @Override
  public void initStateArray() {

  }

  public String getSimulationType() {
    return getAttribute(root, "simulation");
  }

}
