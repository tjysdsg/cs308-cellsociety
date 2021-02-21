package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationGOL;
import model.State;
import model.StateEnumGOL;

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
    simulation = new SimulationGOL(sizeX,sizeY);
    initSimulation();
    return simulation;

  }

  @Override
  public void initSimulation() throws XMLException{
    simulation = new SimulationGOL(sizeX,sizeY);
    super.initSimulation();
  }

  @Override
  public void initStateArray() {
    stateRange = StateEnumGOL.ALL_VALS.length;
    states = new State[stateRange];
    for (int val : StateEnumGOL.ALL_VALS) {
      states[val] = new State(StateEnumGOL.fromInt(val));
    }
  }
}
