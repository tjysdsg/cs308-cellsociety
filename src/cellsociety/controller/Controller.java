package cellsociety.controller;

import Model.Simulation;
import cellsociety.controller.xml.FireXMLParser;
import cellsociety.controller.xml.GOLXMLParser;
import cellsociety.controller.xml.PercolationXMLParser;
import cellsociety.controller.xml.SimulationParser;
import cellsociety.controller.xml.WaTorXMLParser;
import cellsociety.controller.xml.XMLParser;

public class Controller {

  private XMLParser xmlParser;
  private SimulationParser xmlReader;
  private String configName;
  private Simulation simulation;

  public Controller() {
  }

  public void setView(){}
  public void step{

  }
  public void setConfig(String filename) {
    configName = filename;
    xmlReader = new SimulationParser(filename);
    String simulationType = xmlReader.getSimulationType();
    setXMLParser(simulationType);
    simulation = xmlParser.getSimulation();
    return;
  }


  private void setXMLParser(String type) {
    switch (type) {
      case "Fire":
        xmlParser = new FireXMLParser(configName);
        break;

      case "GOL":
        xmlParser = new GOLXMLParser(configName);
        break;

      case "Percolation":
        xmlParser = new PercolationXMLParser(configName);
        break;

      case "WaTor":
        xmlParser = new WaTorXMLParser(configName);
        break;

      default:
        break;
    }
  }

  public static void main(String[] args){
    String fileName= "gameconfig/Minecraft.xml";
    Controller tmp =new Controller();
    tmp.setConfig(fileName);
  }


}
