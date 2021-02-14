package controller;

import controller.xml.SegregationXMLParser;
import java.awt.dnd.DropTargetAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import model.Simulation;
import controller.xml.FireXMLParser;
import controller.xml.GOLXMLParser;
import controller.xml.PercolationXMLParser;
import controller.xml.SimulationParser;
import controller.xml.WaTorXMLParser;
import controller.xml.XMLParser;
import view.MainView;

public class Controller {

  private XMLParser xmlParser;
  private SimulationParser xmlReader;
  private String configName;
  private boolean pause;
  private Simulation simulation;
  private MainView view;
  private Timeline animation;
  public static final int FRAMES_PER_SECOND = 1;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final String DATA_GAMECONFIG="data/gameconfig/";

  public Controller() {
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> this.step());
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
  }

  public void setView(MainView view) {
    this.view = view;
  }

  public void setSpeed(double speed) {
    animation.setRate(speed);
  }

  public void setPause() {
    pause = true;
    animation.stop();
  }

  public void setResume() {
    if (view.getConfig()!= null && pause){
    pause = false;
    animation.play();}
  }

  public void setStart() {
    if(view.getConfig()==null){
      Alert alert = new Alert(AlertType.WARNING);
      alert.setContentText("Please select a config file to start");
      alert.show();
    } else if (!pause){
      return;
    } else {
      view.setGridPane(simulation.getGrid());
      view.displayStatus(simulation.getStatsMap());
      pause = false;
      animation.play();
    }
  }

  public void reset() {
    setPause();
    xmlParser.initSimulation();
    view.resetSimulation(simulation.getGrid(), simulation.getStatsMap());
  }

  public void step() {

    // sim update
    simulation.update();

    // view update
    Map<String, Object> mapToStatus=simulation.getStatsMap();
    xmlParser.addXMLDescription(mapToStatus);
    view.step(simulation.getGrid(), mapToStatus);

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

      case "Segregation":
        xmlParser = new SegregationXMLParser(configName);

      default:
        break;
    }
  }

  public <T> void changeConfig(String name, T value) {
    simulation.setConfig(name, value);
  }

  public ArrayList<String> getGameConfigFileNameList(){
    File tmp= new File(DATA_GAMECONFIG);
    File[] gameXMLS= tmp.listFiles();
    ArrayList<String> configList=new ArrayList<>();
    for(int i=0;i<gameXMLS.length;i++){
      configList.add(gameXMLS[i].toString().split(DATA_GAMECONFIG)[1]);
    }
    return configList;
  }

  public static void main(String[] args) {
    System.out.println((String) null);
  }


}
