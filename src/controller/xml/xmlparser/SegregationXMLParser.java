package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationSegregation;
import model.State;
import model.StateEnumSegregation;

/**
 * XML writer class. Used to output the current state of the simulation
 *
 * @author Tinglong Zhu
 */
public class SegregationXMLParser extends XMLParser{

  public static final String THRESHOLD_TAG = "threshold";
  private double threshold;

  public SegregationXMLParser(String fileName){
    super(fileName);
    initStateArray();
  }
  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSizeX();
    sizeY= getGridSizeY();
    simulation = new SimulationSegregation(sizeX,sizeY);
    initSimulation();
    return simulation;
  }

  @Override
  public void initStateArray() {
    stateRange = StateEnumSegregation.ALL_VALS.length;
    states = new State[stateRange];
    for (int val : StateEnumSegregation.ALL_VALS) {
      states[val] = new State(StateEnumSegregation.fromInt(val));
    }
  }

  @Override
  public void initSimulation() throws XMLException{
    simulation = new SimulationSegregation(sizeX,sizeY);
    super.initSimulation();
    initThreshold();
  }

  private void initThreshold() throws  XMLException{
    try {
      threshold = getDoubleTextValue(root, THRESHOLD_TAG);
      simulation.setConfig("threshold",threshold);
      params.put(THRESHOLD_TAG,threshold);
    }catch (Exception e){
      throw new XMLException("Invalid threshold");
    }
  }
}
