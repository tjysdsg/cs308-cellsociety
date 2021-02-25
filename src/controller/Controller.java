package controller;

import controller.xml.XMLException;
import controller.xml.xmlparser.AntXMLParser;
import controller.xml.xmlparser.LangtonXMLParser;
import controller.xml.xmlparser.RPSXMLParser;
import controller.xml.xmlparser.SegregationXMLParser;
import controller.xml.xmlparser.SugarXMLParser;
import controller.xml.xmlwriter.XMLWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Simulation;
import view.ControllableParam;
import view.LabelResource;
import controller.xml.xmlparser.FireXMLParser;
import controller.xml.xmlparser.GOLXMLParser;
import controller.xml.xmlparser.PercolationXMLParser;
import controller.xml.xmlparser.SimulationTypeParser;
import controller.xml.xmlparser.WaTorXMLParser;
import controller.xml.xmlparser.XMLParser;
import view.MainView;

/**
 * Controller class
 *
 * @author Tinglong Zhu
 */
public class Controller {

  private String language;
  private XMLParser xmlParser;
  private boolean stepIsPressedFlag = false;
  private SimulationTypeParser xmlReader;
  private String configName;
  private boolean pause;
  private SettingReader settingReader;
  private Simulation simulation;
  private MainView view;
  private Timeline animation;
  private String simulationType;
  public static final int FRAMES_PER_SECOND = 1;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final String DATA_GAMECONFIG = "data/gameconfig/";
  private LabelResource labelResource;

  /**
   * Constructor of the controller.
   */
  public Controller() {
    labelResource = new LabelResource("English"); // TODO: allow selection of language
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> this.step());
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
  }

  public String getCurrentTime() {
    return animation.getCurrentTime().toString();
  }


  public void intializeView(Stage stage) {
    MainView view = new MainView(this);
    view.setLanguage(language);
    Scene scene = view.createScene();
    stage.setScene(scene);
    this.setView(view);
  }

  private void makePopulationGraph() {
    view.makePopulationGraph(simulation.getStatsNames());
  }

  public void setView(MainView view) {
    this.view = view;
  }

  public void setLanguage(String lan) {
    this.language = lan;
  }


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
    if (view.getConfig() != null && pause) {
      pause = false;
      animation.play();
      if (stepIsPressedFlag) {
        stepIsPressedFlag = false;
      }
    }
  }

  /**
   * Step over 1 step of the simulation
   */
  public void stepIsPressed() {
    stepIsPressedFlag = true;
  }

  /**
   * Start the simulation
   */
  public void setStart() {
//    Alert alert = new Alert(AlertType.WARNING);
    if (view.getConfig() == null) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setContentText(view.getLabelResource().getString("NoConfigFileWarning"));
      alert.show();
    } else if (!pause || stepIsPressedFlag) {
      return;
    } else {
      view.displayControllableParams(getSettingConfigs());
      view.setGridPane(simulation.getGrid());
      view.displayStatus(simulation.getStatsMap());
      view.makeVisibilityButton();
      view.makeInVisibilityButton();
      view.makeSaveButton();
      pause = false;
      animation.play();
      makePopulationGraph();
    }
  }

  /**
   * Reset the simulation
   */
  public void reset() {
    if (stepIsPressedFlag) {
      return;
    }
    setPause();
    xmlParser.initSimulation();
    simulation = xmlParser.simulation;
    view.resetSimulation(simulation.getGrid(), simulation.getStatsMap());
  }

  /**
   * Single step(frame) of the simulation. The View will call it every single frame.
   */
  public void step() {

    // sim update
    simulation.update();

    // view update
    Map<String, Object> mapToStatus = simulation.getStatsMap();
    xmlParser.addXMLDescription(mapToStatus);
    view.step(simulation.getGrid(), mapToStatus);
  }

  /**
   * Set up the configuration based on the certain XML file.
   *
   * @param filename XML file name
   */
  public void setConfig(String filename) {
    try {
      configName = filename;
      xmlReader = new SimulationTypeParser(filename);
      simulationType = xmlReader.getSimulationType();
      settingReader = new SettingReader(simulationType);
      setXMLParser(simulationType);
      simulation = xmlParser.getSimulation();
    } catch (Exception e) {
      System.out.println(e);
      view.catchError(labelResource.getString("ConfigFileError"));
    }
    return;
  }


  private void setXMLParser(String type) throws XMLException {
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
        break;

      case "RPS":
        xmlParser = new RPSXMLParser(configName);
        break;

      case "Ant":
        xmlParser = new AntXMLParser(configName);
        break;

      case "Langton":
        xmlParser = new LangtonXMLParser(configName);
        break;

      case "SugarScape":
        xmlParser = new SugarXMLParser(configName);

      default:
        break;
    }
  }

  /**
   * Provide the setting list for view to modify them
   *
   * @return Setting configs for display buttons to change them
   */
  public List<ControllableParam> getSettingConfigs() {
    List<ControllableParam> settingList = settingReader.getSettings();
    for (int i = 0; i < settingList.size(); i++) {
      String name = settingList.get(i).getName();
      Object val = xmlParser.params.get(name);

      // if val is null, don't override the default value specified in files in simulationControl/
      if (val != null) {
        settingList.get(i).setCurrent_val(val);
      }
    }
    return settingList;
  }

  /**
   * Set config Values. Calling model API
   *
   * @param name name of the config
   * @param val  value of the config to update
   */
  public void setConfigValues(String name, Object val) {
    simulation.setConfig(name, val);
  }

  public void XMLToFile(String filename) {
    XMLWriter writer = new XMLWriter();
    writer.XML2File(simulation.getGrid(), xmlParser.params, simulationType,
        filename + simulationType);
  }

  public Map<String, Object> ConfigSettings() {
    return xmlParser.params;
  }

  public static void main(String[] args) {
    System.out.println((String) null);
  }


}
