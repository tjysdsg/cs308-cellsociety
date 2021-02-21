package controller;

import controller.xml.xmlparser.RPSXMLParser;
import controller.xml.xmlparser.SegregationXMLParser;
import controller.xml.xmlwriter.XMLWriter;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import model.Simulation;
import controller.xml.xmlparser.FireXMLParser;
import controller.xml.xmlparser.GOLXMLParser;
import controller.xml.xmlparser.PercolationXMLParser;
import controller.xml.xmlparser.SimulationTypeParser;
import controller.xml.xmlparser.WaTorXMLParser;
import controller.xml.xmlparser.XMLParser;
import view.MainView;

/**
 *
 */
public class Controller {

  private XMLParser xmlParser;
  private boolean stepIsPressedFlag = false;
  private SimulationTypeParser xmlReader;
  private String configName;
  private boolean pause;
  private Simulation simulation;
  private MainView view;
  private Timeline animation;
  public static final int FRAMES_PER_SECOND = 1;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final String DATA_GAMECONFIG="data/gameconfig/";

  /**
   *
   */
  public Controller() {
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> this.step());
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
  }

  public void setView(MainView view) {
    this.view = view;
  }

  /**
   * Set the speed of the simulation
   *
   * @param speed The speed of the simulation
   */
  public void setSpeed(double speed) {
    animation.setRate(speed);
  }

  /**
   * Pause the simulation
   */
  public void setPause() {
    pause = true;
    animation.stop();
  }

  /**
   * Resume the simulation
   */
  public void setResume() {
    if (view.getConfig()!= null && pause){
    pause = false;
    animation.play();
    if (stepIsPressedFlag){
      stepIsPressedFlag = false;
    }}
  }

  /**
   * Step over 1 step of the simulation
   */
  public void stepIsPressed(){
    stepIsPressedFlag = true;
  }

  /**
   * Start the simulation
   */
  public void setStart() {
    if(view.getConfig()==null){
      Alert alert = new Alert(AlertType.WARNING);
      alert.setContentText("Please select a config file to start");
      alert.show();
    } else if (!pause || stepIsPressedFlag){
      return;
    } else {
      view.setGridPane(simulation.getGrid());
      view.displayStatus(simulation.getStatsMap());
      pause = false;
      animation.play();
    }
  }

  /**
   * Reset the simulation
   */
  public void reset() {
    if (stepIsPressedFlag){
      return;
    }
    setPause();
    xmlParser.initSimulation();
    simulation=xmlParser.simulation;
    view.resetSimulation(simulation.getGrid(), simulation.getStatsMap());
  }

  /**
   * Single step(frame) of the simulation. The View will call it every single frame.
   */
  public void step() {

    // sim update
    simulation.update();

    // view update
    Map<String, Object> mapToStatus=simulation.getStatsMap();
    xmlParser.addXMLDescription(mapToStatus);
    view.step(simulation.getGrid(), mapToStatus);

  }

  /**
   * Set up the configuration based on the certain XML file.
   * @param filename XML file name
   */
  public void setConfig(String filename) {
    configName = filename;
    xmlReader = new SimulationTypeParser(filename);
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

      case "RPS":
        xmlParser= new RPSXMLParser(configName);
      default:
        break;
    }
  }

  public void XMLToFile(){
    XMLWriter writer= new XMLWriter();
    writer.XML2File(simulation.getGrid(),xmlParser.params,configName);
  }

  public static void main(String[] args) {
    System.out.println((String) null);
  }


}
