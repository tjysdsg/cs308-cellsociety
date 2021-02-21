package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationPercolation;
import model.State;
import model.StateEnumPercolation;

public class PercolationXMLParser extends XMLParser {

  public PercolationXMLParser(String fileName) {
    super(fileName);
    initStateArray();
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSizeX();
    sizeY=getGridSizeY();
    simulation = new SimulationPercolation(sizeX,sizeY);
    initSimulation();
    return simulation;
  }

  @Override
  public void initSimulation() {
    simulation = new SimulationPercolation(sizeX,sizeY);
    super.initSimulation();
  }

  @Override
  public void initStateArray() {
    stateRange = StateEnumPercolation.ALL_VALS.length;
    for (int val : StateEnumPercolation.ALL_VALS) {
      states[val] = new State(StateEnumPercolation.fromInt(val));
    }
  }
}
