package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationRPS;
import model.State;
import model.StateEnumGOL;
import model.StateEnumRPS;

/**
 * XML writer class. Used to output the current state of the simulation
 *
 * @author Tinglong Zhu
 */
public class RPSXMLParser extends XMLParser{
  public static final String THRESHOLD_TAG="threshold";
  private int threshold;


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
    root = getRootElement();
    sizeX = getGridSizeX();
    sizeY = getGridSizeY();
    simulation = new SimulationRPS(sizeX,sizeY);
    initSimulation();
    return simulation;
  }

  @Override
  public void initStateArray() {
    stateRange = StateEnumRPS.ALL_VALS.length;
    states = new State[stateRange];
    for (int val : StateEnumRPS.ALL_VALS) {
      states[val] = new State(StateEnumRPS.fromInt(val));
    }
  }

  @Override
  public void initSimulation() {
    try{
      super.initSimulation();
      threshold=getIntTextValue(root, THRESHOLD_TAG);
      simulation.setConfig(THRESHOLD_TAG,threshold);
      params.put(THRESHOLD_TAG,threshold);
    }catch (Exception e){
      System.out.println(e);
    }

  }
  @Override
  public State newState(int val) {
    return new State(StateEnumRPS.fromInt(val));
  }
}
