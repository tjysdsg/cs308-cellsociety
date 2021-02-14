package controller.xml;

import static model.StatePercolation.BLOCKED;
import static model.StatePercolation.OPEN;
import static model.StatePercolation.PERCOLATED;

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