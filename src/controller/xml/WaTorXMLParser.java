package controller.xml;

import static model.StateWaTor.EMPTY;
import static model.StateWaTor.FISH;
import static model.StateWaTor.MOVED_FISH;
import static model.StateWaTor.MOVED_SHARK;
import static model.StateWaTor.SHARK;

import model.Simulation;
import model.SimulationWaTor;
import model.State;

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
    stateRange = 5;
    states = new State[stateRange];
    states[0] = EMPTY();
    states[1] = SHARK();
    states[2] = FISH();
    states[3] = MOVED_SHARK();
    states[4] = MOVED_FISH();
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
  }
  private void initSharkBreedDuration(){
    sharkBreedDuration=getIntTextValue(root, SHARK_BREED_DURATION_TAG);
    simulation.setConfig(SHARK_BREED_DURATION_TAG,sharkBreedDuration);
  }
  private void initSharkStarveDuration(){
    sharkStarveDuration=getIntTextValue(root, SHARK_STARVE_DURATION_TAG);
    simulation.setConfig(SHARK_STARVE_DURATION_TAG,sharkStarveDuration);
  }
}
