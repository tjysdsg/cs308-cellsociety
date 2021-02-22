package controller.xml.xmlparser;

import controller.xml.XMLException;
import model.Simulation;
import model.SimulationSugar;
import model.State;
import model.StateEnumSugar;
import model.StateSugar;
import model.SugarAgent;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SugarXMLParser extends XMLParser{

  public static final String SUGARNUM = "sugar";
  public static final String SUGAR_GROW_BACK_RATE = "sugarGrowBackRate";
  public static final String SUGAR_GROW_BACK_INTERVAL = "sugarGrowBackInterval";
  public static final String MAX_SUGAR_PER_PATCH = "maxSugarPerPatch";
  private int sugarGrowBackRate;
  private int sugarGrowBackInterval;
  private int maxSugarPerPatch;


  /**
   * Create parser for XML files of given filename.
   *
   * @param fileName
   */
  public SugarXMLParser(String fileName) throws XMLException {
    super(fileName);
  }

  @Override
  public Simulation getSimulation() throws XMLException {
    root = getRootElement();
    sizeX = getGridSizeX();
    sizeY= getGridSizeY();
    simulation = new SimulationSugar(sizeX,sizeY);
    initSimulation();
    return simulation;
  }

  @Override
  public void initStateArray() {
    stateRange = StateEnumSugar.ALL_VALS.length;
    states = new State[stateRange];
    for (int val : StateEnumSugar.ALL_VALS) {
      // TODO: load other information of ant cell from config files
      states[val] = new StateSugar(StateEnumSugar.fromInt(val),0,new SugarAgent());
    }
  }

  private void initSugarParams() throws XMLException{
    try{
      sugarGrowBackRate= getIntTextValue(root, SUGAR_GROW_BACK_RATE);
      sugarGrowBackInterval = getIntTextValue(root, SUGAR_GROW_BACK_INTERVAL);
      maxSugarPerPatch= getIntTextValue(root, MAX_SUGAR_PER_PATCH);
      simulation.setConfig(SUGAR_GROW_BACK_RATE,sugarGrowBackRate);
      simulation.setConfig(SUGAR_GROW_BACK_INTERVAL,sugarGrowBackInterval);
      simulation.setConfig(MAX_SUGAR_PER_PATCH,maxSugarPerPatch);
      params.put(SUGAR_GROW_BACK_RATE,sugarGrowBackRate);
      params.put(SUGAR_GROW_BACK_INTERVAL,sugarGrowBackInterval);
      params.put(MAX_SUGAR_PER_PATCH,maxSugarPerPatch);
    }catch (Exception e){
      throw new XMLException("Invalid config for sugar!");
    }
  }

  @Override
  public void initSimulation() throws XMLException {
    simulation=new SimulationSugar(sizeX,sizeY);
    super.initSimulation();
    initSugarParams();
  }

  @Override
  public void initCellByLocation() throws XMLException {
    NodeList stateList = root.getElementsByTagName(CELL_TAG);
    for (int i = 0; i < stateList.getLength(); i++) {
      try{
        Element cell = (Element) stateList.item(i);
        int row = getIntTextValue(cell, ROW_TAG);
        int col = getIntTextValue(cell, COL_TAG);
        int sugar = getIntTextValue(cell, SUGARNUM);
        checkCoordinate(row,col);
        int stateNum = getIntTextValue(cell, STATE_TAG);
        checkStates(stateNum);
        State cellState = new StateSugar(StateEnumSugar.fromInt(stateNum),sugar,new SugarAgent());
        simulation.setState(row, col, cellState, true);
      }catch (Exception e){
        throw  new XMLException("Invalid Cell");
      }
    }
  }
}
