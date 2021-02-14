package cellsociety.controller.xml;

import static Model.StateGOL.ALIVE;
import static Model.StateGOL.DEAD;

import Model.Simulation;
import Model.SimulationGOL;
import Model.State;

public class GOLXMLParser extends XMLParser {

  public GOLXMLParser(String fileName) {
    super(fileName);
    initStateArray();
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSize();
    simulation = new SimulationGOL(sizeX);
    initSimulation();
    return simulation;

  }

  @Override
  public void initStateArray() {
    stateRange = 2;
    states = new State[stateRange];
    states[0] = DEAD;
    states[1] = ALIVE;
  }
}
