package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationWaTor;
import model.State;
import model.StateEnumWaTor;

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
      states[val] = new State(StateEnumWaTor.fromInt(val));
    }
  }

  @Override
  public void initSimulation() {
    simulation = new SimulationWaTor(sizeX,sizeY);
    super.initSimulation();
    initFishBreedDuration();
    initSharkBreedDuration();
    initSharkStarveDuration();
  }

  private void initFishBreedDuration(){
    fishBreedDuration= getIntTextValue(root, FISH_BREED_DURATION_TAG);
    simulation.setConfig(FISH_BREED_DURATION_TAG, fishBreedDuration);
    params.put(FISH_BREED_DURATION_TAG,fishBreedDuration);
  }
  private void initSharkBreedDuration(){
    sharkBreedDuration=getIntTextValue(root, SHARK_BREED_DURATION_TAG);
    simulation.setConfig(SHARK_BREED_DURATION_TAG,sharkBreedDuration);
    params.put(SHARK_BREED_DURATION_TAG,sharkBreedDuration);
  }
  private void initSharkStarveDuration(){
    sharkStarveDuration=getIntTextValue(root, SHARK_STARVE_DURATION_TAG);
    simulation.setConfig(SHARK_STARVE_DURATION_TAG,sharkStarveDuration);
    params.put(SHARK_STARVE_DURATION_TAG,sharkStarveDuration);
  }
}
