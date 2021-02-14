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

  private String STYLESHEET = "default.css";
  private Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN,};
  private final double gridHeight = 400.0;
  private final double gridWidth = 600.0;
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

  public void step(List<List<Integer>> grid, Map<String, Number> statesMap) {
    sec++;
    displayStatus(statesMap);
    updateGridPane(grid);
  }

  public void startSimulation(List<List<Integer>> states, Map<String, Number> statesMap) {
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

  public void pauseSimulation() {
    animationIsStopped = true;
  }

  public void resumeSimulation() {
    if (configFile != null && animationIsStopped) {
      animationIsStopped = false;
    }
  }

  public void resetSimulation(List<List<Integer>> states, Map<String, Number> statesMap) {
    sec = 0;
    pauseSimulation();
    if (root.getChildren().contains(grid)) {
      grid.getChildren().clear();
      gridelements.clear();
    }
    startSimulation(states, statesMap);
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

  private void displayStatus(Map<String, Number> statesMap) {
    //statesMap.put(configFile+" time elapsed: ", sec);
    statusbox.getChildren().clear();
    for (String s : statesMap.keySet()) {
      HBox temp = new HBox(15);
      Text display_text = new Text();
      Label text_lable = new Label(s);
      display_text.setText(statesMap.get(s).toString());
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

  private void setGridPane(List<List<Integer>> states) {
    int r = states.size();
    int c = states.get(0).size();
    grid.setTranslateX(100);
    grid.setTranslateY(100);
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
    //root.getChildren().addAll(grid);
  }

  private void updateGridPane(List<List<Integer>> grid) {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        int n = grid.get(i).get(j);
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
    HBox hbox4 = new HBox(15);
    Label configlabel = new Label("config files: ");
    hbox4.getChildren().addAll(configlabel, configlist);
    hbox4.setTranslateX(600);
    hbox4.setTranslateY(350);
    configlist.valueProperty().addListener((observable, oldValue, newValue) -> {
      configFile = newValue.toString();
      if (newValue != oldValue) {
        controller.setPause();
        controller.setConfig(configFile);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("You have selected a different config file, press RESET to run it\n "
            + "but current progress will be cleared");
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
    statusbox = new VBox(15);
    statusbox.setTranslateX(50);
    statusbox.setTranslateY(500);
    root.getChildren().add(statusbox);

    makeComboBox();
    makeAllButtons();
    setSpeed();

    Scene scene = new Scene(root);
    //scene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
    return scene;

  }

}
