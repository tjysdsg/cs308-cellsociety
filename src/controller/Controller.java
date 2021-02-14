package controller;

import model.Simulation;
import controller.xml.FireXMLParser;
import controller.xml.GOLXMLParser;
import controller.xml.PercolationXMLParser;
import controller.xml.SimulationParser;
import controller.xml.WaTorXMLParser;
import controller.xml.XMLParser;

public class Controller {

  private XMLParser xmlParser;
  private SimulationParser xmlReader;
  private String configName;
  private boolean pause;
  private boolean stepMode;
  private Simulation simulation;

  public Controller() {
  }

  public void setView(){}

  public void setPause(){
    pause=true;
  }

  public void setStart(){
    pause=false;
  }

  public void setStepMode(){
    pause=false;
    stepMode=true;
  }

  public void reset(){
    xmlParser.initSimulation();
  }

  public void step(){
    if(pause)return;

    if(stepMode){
      pause=true;
    }

    simulation.update();

    //view update

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

  public <T> void changeConfig(String name, T value){
    switch (name){
      case "reset":
        reset();
        return;
      case "stepMode":
        setStepMode();
        return;
      case "pause":
        setPause();
        return;
      case "start":
        setStart();
        return;
    }
    simulation.setConfig(name,value);
  }

  public static void main(String[] args){
    String fileName= "gameconfig/Minecraft.xml";
    Controller tmp =new Controller();
    tmp.setConfig(fileName);
  }


}
