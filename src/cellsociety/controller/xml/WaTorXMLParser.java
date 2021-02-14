package cellsociety.controller.xml;

import static Model.StateWaTor.EMPTY;
import static Model.StateWaTor.FISH;
import static Model.StateWaTor.MOVED_FISH;
import static Model.StateWaTor.MOVED_SHARK;
import static Model.StateWaTor.SHARK;

import Model.Simulation;
import Model.SimulationWaTor;
import Model.State;

public class WaTorXMLParser extends XMLParser {

  public WaTorXMLParser(String fileName) {
    super(fileName);
    initStateArray();
  }


  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSize();
    simulation = new SimulationWaTor(sizeX);
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
}
