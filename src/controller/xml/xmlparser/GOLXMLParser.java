package controller.xml.xmlparser;

import static model.StateGOL.ALIVE;
import static model.StateGOL.DEAD;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationGOL;
import model.State;

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
  public void initSimulation() {
    simulation = new SimulationGOL(sizeX,sizeY);
    super.initSimulation();
  }

  @Override
  public void initStateArray() {
    stateRange = 2;
    states = new State[stateRange];
    states[0] = DEAD;
    states[1] = ALIVE;
  }
}
