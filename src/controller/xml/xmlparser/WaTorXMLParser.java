package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationWaTor;
import model.State;
import model.StateEnumWaTor;
import model.StateWaTor;

/**
 * XML writer class. Used to output the current state of the simulation
 *
 * @author Tinglong Zhu
 */
public class WaTorXMLParser extends XMLParser {

  public static final String FISH_BREED_DURATION_TAG = "fishBreedDuration";
  public static final String SHARK_BREED_DURATION_TAG = "sharkBreedDuration";
  public static final String SHARK_STARVE_DURATION_TAG = "sharkStarveDuration";
  private int fishBreedDuration;
  private int sharkBreedDuration;
  private int sharkStarveDuration;

  public WaTorXMLParser(String fileName) {
    super(fileName);
    initStateArray();
  }


  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSizeX();
    sizeY= getGridSizeY();
    simulation = new SimulationWaTor(sizeX,sizeY);
    initSimulation();
    return simulation;
  }

  @Override
  public void initStateArray() {
    stateRange = StateEnumWaTor.ALL_VALS.length;
    states = new State[stateRange];
    for (int val : StateEnumWaTor.ALL_VALS) {
      states[val] = new StateWaTor(StateEnumWaTor.fromInt(val));
    }
  }

  @Override
  public void initSimulation() throws XMLException{
    simulation = new SimulationWaTor(sizeX,sizeY);
    super.initSimulation();
    initFishBreedDuration();
    initSharkBreedDuration();
    initSharkStarveDuration();
  }

  private void initFishBreedDuration() throws XMLException{
    try{
      fishBreedDuration= getIntTextValue(root, FISH_BREED_DURATION_TAG);
      simulation.setConfig(FISH_BREED_DURATION_TAG, fishBreedDuration);
      params.put(FISH_BREED_DURATION_TAG,fishBreedDuration);
    }catch (Exception e){
      throw new XMLException("Invalid fish breed duration");
    }
  }
  private void initSharkBreedDuration() throws XMLException{
    try{
    sharkBreedDuration=getIntTextValue(root, SHARK_BREED_DURATION_TAG);
    simulation.setConfig(SHARK_BREED_DURATION_TAG,sharkBreedDuration);
    params.put(SHARK_BREED_DURATION_TAG,sharkBreedDuration);
    }catch (Exception e){
      throw new XMLException("Invalid shark breed duration");
    }
  }
  private void initSharkStarveDuration() throws XMLException{
    try{
    sharkStarveDuration=getIntTextValue(root, SHARK_STARVE_DURATION_TAG);
    simulation.setConfig(SHARK_STARVE_DURATION_TAG,sharkStarveDuration);
    params.put(SHARK_STARVE_DURATION_TAG,sharkStarveDuration);
    }catch (Exception e){
      throw new XMLException("Invalid shark starve duration");
    }
  }
}
