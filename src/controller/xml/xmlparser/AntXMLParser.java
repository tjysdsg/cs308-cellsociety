package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationAnt;
import model.SimulationSegregation;
import model.State;
import model.StateAnt;
import model.StateEnumAnt;
import model.StateEnumPercolation;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AntXMLParser extends XMLParser{

  public static final String ANTSNUM = "antsnum";
  public static final String FOODSOURCE = "foodsource";
  public static final String NEST = "nest";
  public static final String TRUE = "true";

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

  @Override
  public void initCellByLocation() throws XMLException {
    NodeList stateList = root.getElementsByTagName(CELL_TAG);
    for (int i = 0; i < stateList.getLength(); i++) {
      try{
        Element cell = (Element) stateList.item(i);
        int row = getIntTextValue(cell, ROW_TAG);
        int col = getIntTextValue(cell, COL_TAG);
        int ants = getIntTextValue(cell, ANTSNUM);
        String isFoodSource= getTextValue(cell, FOODSOURCE);
        String isNest= getTextValue(cell, NEST);
        checkCoordinate(row,col);
        int stateNum = getIntTextValue(cell, STATE_TAG);
        checkStates(stateNum);
        State cellState = new StateAnt(StateEnumAnt.fromInt(stateNum), ants, isFoodSource.equals(
            TRUE), isNest.equals(TRUE));
        simulation.setState(row, col, cellState, true);
      }catch (Exception e){
        throw  new XMLException("Invalid Cell");
      }
    }
  }
}
