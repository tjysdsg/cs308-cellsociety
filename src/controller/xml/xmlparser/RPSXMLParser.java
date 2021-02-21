package controller.xml.xmlparser;

import static model.StateRPS.PAPER;
import static model.StateRPS.ROCK;
import static model.StateRPS.SCISSORS;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationRPS;
import model.State;

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
    stateRange = 3;
    states = new State[stateRange];
    states[0] = ROCK;
    states[1] = PAPER;
    states[2] = SCISSORS;
  }

  @Override
  public void initSimulation() {
    super.initSimulation();
    threshold=getIntTextValue(root, THRESHOLD_TAG);
    simulation.setConfig(THRESHOLD_TAG,threshold);
    params.put(THRESHOLD_TAG,threshold);
  }
}
