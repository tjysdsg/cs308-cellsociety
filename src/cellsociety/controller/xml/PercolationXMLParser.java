package cellsociety.controller.xml;

import static Model.StatePercolation.BLOCKED;
import static Model.StatePercolation.OPEN;
import static Model.StatePercolation.PERCOLATED;

import Model.Simulation;
import Model.SimulationPercolation;
import Model.State;

public class PercolationXMLParser extends XMLParser {

  public PercolationXMLParser(String fileName) {
    super(fileName);
    initStateArray();
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSize();
    simulation = new SimulationPercolation(sizeX);
    initSimulation();
    return simulation;
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
