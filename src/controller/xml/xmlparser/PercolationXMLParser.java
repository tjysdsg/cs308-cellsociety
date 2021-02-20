package controller.xml.xmlparser;

import static model.StatePercolation.BLOCKED;
import static model.StatePercolation.OPEN;
import static model.StatePercolation.PERCOLATED;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationPercolation;
import model.State;

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
    stateRange = 3;
    states = new State[stateRange];
    states[0] = BLOCKED;
    states[1] = OPEN;
    states[2] = PERCOLATED;
  }
}
