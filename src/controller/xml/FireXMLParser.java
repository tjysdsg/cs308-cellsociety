package controller.xml;

import static model.StateFire.BURNING;
import static model.StateFire.EMPTY;
import static model.StateFire.TREE;

import model.Simulation;
import model.SimulationFire;
import model.State;

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
