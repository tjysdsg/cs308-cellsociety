package cellsociety.controller.xml;

import static Model.StateFire.BURNING;
import static Model.StateFire.EMPTY;
import static Model.StateFire.TREE;

import Model.Simulation;
import Model.SimulationFire;
import Model.State;

public class FireXMLParser extends XMLParser {

  /**
   * Create parser for XML files of given fileName.
   *
   * @param fileName
   */


  public FireXMLParser(String fileName) throws XMLException {
    super(fileName);
    initStateArray();
  }

  @Override
  public void initStateArray() {
    stateRange = 3;
    states = new State[stateRange];
    states[0] = EMPTY;
    states[1] = TREE;
    states[2] = BURNING;
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSize();
    simulation = new SimulationFire(sizeX);
    initSimulation();
    return simulation;
  }

}
