package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationFire;
import model.State;
import model.StateEnum;
import model.StateEnumFire;

/**
 * XML writer class. Used to output the current state of the simulation
 *
 * @author Tinglong Zhu
 */
public class FireXMLParser extends XMLParser {

  public static final String PROB_CATCH_TAG ="probCatch";
  /**
   * Create parser for XML files of given fileName.
   *
   * @param fileName
   */

  private double probCatch;

  public FireXMLParser(String fileName) throws XMLException {
    super(fileName);
    initStateArray();
  }

  @Override
  public void initStateArray() {
    stateRange = StateEnumFire.ALL_VALS.length;
    states = new State[stateRange];
    for (int val : StateEnumFire.ALL_VALS) {
      states[val] = new State(StateEnumFire.fromInt(val));
    }
  }


  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSizeX();
    sizeY = getGridSizeY();
    simulation = new SimulationFire(sizeX,sizeY);
    initSimulation();
    return simulation;
  }

  @Override
  public void initSimulation() throws XMLException{
    simulation = new SimulationFire(sizeX,sizeY);
    super.initSimulation();
    initProbCatch();
  }

  private void initProbCatch() throws XMLException{
    try {
      probCatch=getDoubleTextValue(root, PROB_CATCH_TAG);
      simulation.setConfig(PROB_CATCH_TAG,probCatch);
      params.put(PROB_CATCH_TAG,probCatch);
    }catch (Exception e){
      throw new XMLException("Invalid probcatch");
    }
  }

  @Override
  public State newState(int val) {
    return new State(StateEnumFire.fromInt(val));
  }
}
