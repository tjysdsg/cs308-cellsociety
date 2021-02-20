package controller.xml.xmlparser;

import static model.StateSegregation.EMPTY;
import static model.StateSegregation.O;
import static model.StateSegregation.X;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationSegregation;
import model.State;

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
    stateRange=3;
    states= new State[stateRange];
    states[0]=EMPTY;
    states[1]=O;
    states[2]=X;
  }

  @Override
  public void initSimulation() {
    simulation = new SimulationSegregation(sizeX,sizeY);
    super.initSimulation();
    initThreshold();
  }

  private void initThreshold(){
    threshold = getDoubleTextValue(root, THRESHOLD_TAG);
    simulation.setConfig("threshold",threshold);
  }
}
