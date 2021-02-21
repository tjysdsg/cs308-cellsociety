package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationRPS;
import model.State;
import model.StateEnumRPS;

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
    super.initSimulation();
    threshold=getIntTextValue(root, THRESHOLD_TAG);
    simulation.setConfig(THRESHOLD_TAG,threshold);
    params.put(THRESHOLD_TAG,threshold);
  }
}
