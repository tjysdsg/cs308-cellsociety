package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationGOL;
import model.State;
import model.StateGOL;

public class GOLXMLParser extends XMLParser {

  public GOLXMLParser(String fileName) {
    super(fileName);
    initStateArray();
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSizeX();
    sizeY = getGridSizeY();
    simulation = new SimulationGOL(sizeX, sizeY);
    initSimulation();
    return simulation;

  }

  @Override
  public void initSimulation() {
    simulation = new SimulationGOL(sizeX, sizeY);
    super.initSimulation();
  }

  @Override
  public void initStateArray() {
    stateRange = StateGOL.ALL_VALS.length;
    for (int val : StateGOL.ALL_VALS) {
      states[val] = new State(StateGOL.fromInt(val));
    }
  }

}
