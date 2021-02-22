package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationAnt;
import model.SimulationSegregation;
import model.State;
import model.StateAnt;
import model.StateEnumAnt;
import model.StateEnumPercolation;

public class AntXMLParser extends XMLParser{

  /**
   * Create parser for XML files of given filename.
   *
   * @param fileName
   */
  public AntXMLParser(String fileName) throws XMLException {
    super(fileName);
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSizeX();
    sizeY= getGridSizeY();
    simulation = new SimulationAnt(sizeX,sizeY);
    initSimulation();
    return simulation;
  }

  @Override
  public void initStateArray() {
    stateRange = StateEnumAnt.ALL_VALS.length;
    states = new State[stateRange];
    for (int val : StateEnumAnt.ALL_VALS) {
      // TODO: load other information of ant cell from config files
      states[val] = new StateAnt(StateEnumAnt.fromInt(val), 0, false, false);
    }
  }

  @Override
  public void initSimulation() throws XMLException {
    simulation=new SimulationAnt(sizeX,sizeY);
    super.initSimulation();
  }
}
