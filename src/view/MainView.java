package view;

import controller.Controller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MainView {

  private Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN,};
  private final double gridHeight = 300.0;
  private final double gridWidth = 500.0;
  private String configFile;
  private Slider speed = new Slider(1, 30, 1);
  private Label speedValue = new Label(Double.toString(speed.getValue()));
  private Label speedLabel = new Label("speed:");
  private Pane root;
  private GridPane grid;
  private Boolean animationIsStopped = true;
  private VBox statusbox;
  private ArrayList<ArrayList<Rectangle>> gridelements = new ArrayList<>();
  private int sec = 0;
  private Controller controller;

  public MainView(Controller controller) {
    this.controller = controller;
  }

  /**
   *
   * @param grid is a double array of integers, representing the states of each cell
   * @param statesMap, is a map of statistics, such as number of tree, etc.
   *                   this step function is called by the step function in controller.
   */
  public void step(List<List<Integer>> grid, Map<String, Object> statesMap) {
    sec++;
    displayStatus(statesMap);
    updateGridPane(grid);
  }

  /**
   *
   * @param states is a double array of integers, representing the states of each cell
   * @param statesMap  is a map of statistics, such as number of tree, etc.
   *                   called by the setStart in controller.
   */

  public void startSimulation(List<List<Integer>> states, Map<String, Object> statesMap) {
    if (configFile == null) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setContentText("Please select a config file to start");
      alert.show();
    } else if (!animationIsStopped) {
      return;
    }
    else {
      setGridPane(states);
      displayStatus(statesMap);
      animationIsStopped = false;
    }
  }

  public String getConfig(){
    return configFile;
  }

  /**
   * called by setPause in controller
   */

  public void pauseSimulation() {
    animationIsStopped = true;
  }

  /**
   * called by setResume in controller
   */

  public void resumeSimulation() {
    if (configFile != null && animationIsStopped) {
      animationIsStopped = false;
    }
  }

  /**
   *
   * @param states, double array of integers
   * @param statesMap, statistics
   * called by reset in controller
   */

  public void resetSimulation(List<List<Integer>> states, Map<String, Object> statesMap) {
    sec = 0;
    grid.getChildren().clear();
    gridelements.clear();
    //startSimulation(states, statesMap);
    controller.setStart();
  }



  private void makeAllButtons() {
    makeButton pausebtn = new makeButton("Pause", 30, 100, 40, 0,
        e -> controller.setPause()
    );
    makeButton resumebtn = new makeButton("Resume", 30, 100, 40, 0,
        e -> controller.setResume()
    );
    makeButton exitbtn = new makeButton("Exit", 30, 100, 40, 0,
        e -> System.exit(0)
    );
    HBox hbox1 = new HBox(15);
    hbox1.getChildren().addAll(pausebtn, resumebtn, exitbtn);

    makeButton resetbtn = new makeButton("Reset", 30, 100, 40, 0,
        e -> controller.reset()
    );
    makeButton startbtn = new makeButton("Start", 30, 100, 40, 0,
        e -> controller.setStart()
    );
    makeButton ffbtn = new makeButton("Step", 30, 100, 40, 0,
        e -> {
          controller.setPause();
          controller.step();
        }
    );
    HBox hbox2 = new HBox(15);
    hbox2.getChildren().addAll(resetbtn, startbtn, ffbtn);

    VBox allbtn = new VBox(15);
    allbtn.setTranslateX(400);
    allbtn.setTranslateY(500);
    allbtn.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY,
        BorderWidths.DEFAULT)));
    allbtn.getChildren().addAll(hbox1, hbox2);
    root.getChildren().addAll(allbtn);
  }

  public void displayStatus(Map<String, Object> statesMap) {
    statesMap.put(configFile+" time elapsed: ", sec);
    //statesMap.put("Authors: Andre Wang, Jiyang Tang, Tinglong Zhu", null);
    statusbox.getChildren().clear();
    for (String s : statesMap.keySet()) {
      HBox temp = new HBox(15);
      Text display_text = new Text();
      Label text_lable = new Label(s);
      if (statesMap.get(s) != null){
      display_text.setText(statesMap.get(s).toString());}
      else {
        display_text.setText("");
      }
      display_text.setFont(display_text.getFont().font(20));
      temp.getChildren().addAll(text_lable, display_text);
      statusbox.getChildren().add(temp);
    }
  }

  private void setSpeed() {
    speed.valueProperty().addListener((observable, oldValue, newValue) -> {
      speedValue.setText(Double.toString(newValue.doubleValue()));
      controller.setSpeed(newValue.doubleValue());
    });
    HBox hbox3 = new HBox(15);
    hbox3.getChildren().addAll(speedLabel, speed, speedValue);
    hbox3.setTranslateX(700);
    hbox3.setTranslateY(100);
    root.getChildren().addAll(hbox3);
  }

  public void setGridPane(List<List<Integer>> states) {
    int r = states.size();
    int c = states.get(0).size();
    grid.setTranslateX(10);
    grid.setTranslateY(10);
    for (int i = 0; i < r; i++) {
      ArrayList<Rectangle> temp = new ArrayList<>();
      for (int j = 0; j < c; j++) {
        int n = states.get(i).get(j);
        Rectangle rec = new Rectangle();
        rec.setFill(colors[n]);
        rec.setWidth(gridWidth / c);
        rec.setHeight(gridHeight / r);
        GridPane.setRowIndex(rec, i);
        GridPane.setColumnIndex(rec, j);
        grid.getChildren().add(rec);
        temp.add(rec);
      }
      gridelements.add(temp);
    }
    statusbox.setTranslateX(15);
    statusbox.setTranslateY(30+gridHeight);
    //root.getChildren().addAll(grid);
  }

  private void updateGridPane(List<List<Integer>> states) {
    int r = states.size();
    int c = states.get(0).size();
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        int n = states.get(i).get(j);
        gridelements.get(i).get(j).setFill(colors[n]);
      }
    }
  }

  private void makeComboBox() {
    File f = new File("data/gameconfig");
    ObservableList<String> options =
        FXCollections.observableArrayList(
            f.list()
        );
    ComboBox configlist = new ComboBox(options);
    HBox hbox4 = new HBox(10);
    Label configlabel = new Label("config files: ");
    hbox4.getChildren().addAll(configlabel, configlist);
    hbox4.setTranslateX(100+gridWidth);
    hbox4.setTranslateY(200);
    configlist.valueProperty().addListener((observable, oldValue, newValue) -> {
      configFile = newValue.toString();
      if (newValue != oldValue) {
        controller.setPause();
        controller.setConfig(configFile);
        Alert alert = new Alert(AlertType.INFORMATION);
        if (oldValue !=null){
          alert.setContentText("You have selected a different config file, press RESET to run it\n "
            + "but current progress will be cleared");
        } else {
          alert.setContentText("press START to begin simulation");
        }
        alert.show();
      }
    });
    root.getChildren().addAll(hbox4);
  }

  public Scene createScene() {
    root = new Pane();
    root.setPrefSize(1000, 600);

    // init grid
    grid = new GridPane();
    root.getChildren().addAll(grid);

    // init status box
    statusbox = new VBox(5);
    root.getChildren().add(statusbox);

    makeComboBox();
    makeAllButtons();
    setSpeed();

    Scene scene = new Scene(root);
    return scene;

  }

}
