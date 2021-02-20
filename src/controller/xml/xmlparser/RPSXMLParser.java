package controller.xml.xmlparser;

import static model.StateRPS.ROCK;
import static model.StateRPS.ROCK;
import static model.StateRPS.ROCK;

import controller.xml.XMLException;
import model.Simulation;
import model.State;

public class RPSXMLParser extends XMLParser{


  /**
   * Create parser for XML files of given filename.
   *
   * @param fileName
   */
  public RPSXMLParser(String fileName) throws XMLException {
    super(fileName);
    initStateArray();
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    return null;
  }

  @Override
  public void initStateArray() {
    stateRange = 3;
    states = new State[stateRange];
    states[0] = ROCK;
    states[1] = TREE;
    states[2] = BURNING;
  }
}
