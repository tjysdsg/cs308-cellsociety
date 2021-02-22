package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationLangton;
import model.SimulationPercolation;
import model.State;
import model.StateEnumLangton;
import model.StateEnumPercolation;

/**
 * XML writer class. Used to output the current state of the simulation
 *
 * @author Tinglong Zhu
 */
public class LangtonXMLParser extends XMLParser{

  /**
   * Create parser for XML files of given filename.
   *
   * @param fileName file name
   */
  public LangtonXMLParser(String fileName) throws XMLException {
    super(fileName);
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSizeX();
    sizeY=getGridSizeY();
    simulation = new SimulationLangton(sizeX,sizeY);
    initSimulation();
    return simulation;

  }

  @Override
  public void initSimulation() throws XMLException {
    simulation= new SimulationLangton(sizeX,sizeY);
    super.initSimulation();
  }

  @Override
  public void initStateArray() {
    stateRange = StateEnumLangton.ALL_VALS.length;
    states = new State[stateRange];
    for (int val : StateEnumLangton.ALL_VALS) {
      states[val] = new State(StateEnumLangton.fromInt(val));
    }
  }
}
