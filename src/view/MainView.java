package view;

import controller.Controller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainView {
  private Scene scene;
  private String STYLESHEET = "cssfiles/none.css";
  private Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN,};
  private final double gridHeight = 300.0;
  private final double gridWidth = 500.0;
  private String configFile;
  private Slider speed = new Slider(1, 30, 1);
  private Label speedValue;
  private Label speedLabel;
  private Pane root;
  private GridPane grid;
  private VBox statusbox;
  private VBox paramsbox;
  private ArrayList<ArrayList<Rectangle>> gridelements = new ArrayList<>();
  private int sec = 0;
  private Controller controller;
  private LabelResource labelResource;

  public MainView(Controller controller) {
    this.controller = controller;
    labelResource = this.controller.getLabelResource();
    speedValue = new Label(Double.toString(speed.getValue()));
    speedLabel = new Label(labelResource.getString("SpeedLabel"));
  }

  /**
   * @param grid       is a double array of integers, representing the states of each cell
   * @param statesMap, is a map of statistics, such as number of tree, etc. this step function is
   *                   called by the step function in controller.
   */
  public void step(List<List<Integer>> grid, Map<String, Object> statesMap) {
    sec++;
    displayStatus(statesMap);
    updateGridPane(grid);
  }

  public String getConfig() {
    return configFile;
  }

  /**
   * @param states,    double array of integers
   * @param statesMap, statistics called by reset in controller
   */
  public void resetSimulation(List<List<Integer>> states, Map<String, Object> statesMap) {
    sec = 0;
    grid.getChildren().clear();
    gridelements.clear();
    controller.setStart();
  }

  private void makeAllButtons() {
    ActionButton pausebtn = new ActionButton(labelResource.getString("PauseButton"), 30, 100, 40, 0, e -> controller.setPause());
    ActionButton resumebtn = new ActionButton(labelResource.getString("ResumeButton"), 30, 100, 40, 0, e -> controller.setResume());
    ActionButton exitbtn = new ActionButton(labelResource.getString("ExitButton"), 30, 100, 40, 0, e -> System.exit(0));
    HBox hbox1 = new HBox(15);
    hbox1.getChildren().addAll(pausebtn, resumebtn, exitbtn);

    ActionButton resetbtn = new ActionButton(labelResource.getString("ResetButton"), 30, 100, 40, 0, e -> controller.reset());
    ActionButton startbtn = new ActionButton(labelResource.getString("StartButton"), 30, 100, 40, 0, e -> controller.setStart());
    ActionButton ffbtn = new ActionButton(labelResource.getString("StepButton"), 30, 100, 40, 0, e -> { controller.setPause();controller.stepIsPressed();controller.step(); });
    HBox hbox2 = new HBox(15);
    hbox2.getChildren().addAll(resetbtn, startbtn, ffbtn);

    VBox allbtn = new VBox(15);
    allbtn.setTranslateX(400);
    allbtn.setTranslateY(500);
    allbtn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    allbtn.getChildren().addAll(hbox1, hbox2);
    root.getChildren().addAll(allbtn);
  }

  private HBox buildStatusItem(String label, String value) {
    HBox ret = new HBox(15);

    Text display_text = new Text();
    display_text.setText(value);
    display_text.setFont(Font.font(20));

    Label text_label = new Label(label);

    ret.getChildren().addAll(text_label, display_text);
    return ret;
  }

  private HBox buildParamsItem(String label, Spinner spinner){
    HBox ret = new HBox(5);
    Label label1 = new Label(label);
    ret.getChildren().addAll(label1,spinner);
    return ret;
  }


  /*public void displayControllableParams(List<ControllableParam> params){
    for (ControllableParam cp : params){
      // NOTE: make sure keys of paramsMap have values set in .properties file
      if (cp.getType().equals("Integer")){
      Spinner<Integer> spinner = new Spinner<>();
      SpinnerValueFactory<Integer> valueFactory = //
       new SpinnerValueFactory.IntegerSpinnerValueFactory( (int) cp.getMinVal(), (int) cp.getMaxVal(),
           (int) cp.getCurrVal());
      spinner.setValueFactory(valueFactory);
      spinner.valueProperty().addListener(new ChangeListener<Integer>() {
        @Override
        public void changed(ObservableValue<? extends Integer> observable, Integer oldValue,
            Integer newValue) {
          cp.setCurrent_val(newValue);
          controller.setConfig(cp);
        }
      });
      paramsbox.getChildren().addAll(buildParamsItem(labelResource.getString(cp.getName()),spinner));
      }
      else {
        Spinner<Double> spinner = new Spinner<>();
        SpinnerValueFactory<Double> valueFactory =//
        new SpinnerValueFactory.DoubleSpinnerValueFactory((double)  cp.getMinVal(), (double) cp.getMaxVal(),
            (double) cp.getCurrVal(), (double) cp.getAmount_to_step_by());
        spinner.setValueFactory(valueFactory);
        spinner.valueProperty().addListener(new ChangeListener<Double>() {
          @Override
          public void changed(ObservableValue<? extends Double> observable, Double oldValue,
              Double newValue) {
            cp.setCurrent_val(newValue);
            controller.setConfig(cp);
          }
        });
        paramsbox.getChildren().addAll(buildParamsItem(labelResource.getString(cp.getName()),spinner));
      }
      }

    }*/

  public void displayStatus(Map<String, Object> statesMap) {
    // time elapsed
    statusbox.getChildren().add(
        buildStatusItem(
            configFile + " " + labelResource.getString("TimeElapsed") + " ",
            Integer.toString(sec)
        ));

    // other status
    statusbox.getChildren().clear();
    for (Map.Entry<String,Object> entry : statesMap.entrySet()) {
      /// NOTE: make sure keys of statesMap have values set in .properties file

      String label = labelResource.getString(entry.getKey());
      Object v = entry.getValue();
      String value = "";
      if (v != null) {
        value = v.toString();
      }
      statusbox.getChildren().add(buildStatusItem(label, value));
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
    statusbox.setTranslateY(30 + gridHeight);
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


  private void makeConfigDropDownList() {
    File f = new File("data/gameconfig");
    ObservableList<String> options =
        FXCollections.observableArrayList(
            f.list()
        );
    ComboBox configlist = new ComboBox(options);
    HBox hbox4 = new HBox(10);
    Label configlabel = new Label(labelResource.getString("ConfigFiles"));
    hbox4.getChildren().addAll(configlabel, configlist);
    hbox4.setTranslateX(20 + gridWidth);
    hbox4.setTranslateY(200);
    configlist.valueProperty().addListener((observable, oldValue, newValue) -> {
      configFile = newValue.toString();
      if (newValue != oldValue) {
        controller.setPause();
        controller.setConfig(configFile);
        Alert alert = new Alert(AlertType.INFORMATION);
        if (oldValue != null) {
          alert.setContentText(labelResource.getString("PromptNewConfig"));
        } else {
          alert.setContentText(labelResource.getString("PromptStartSim"));
        }
        alert.show();
      }
    });
    root.getChildren().addAll(hbox4);
  }

  private void makeCSSDropDownList() {
    File f = new File("data/cssfiles");
    ObservableList<String> options =
        FXCollections.observableArrayList(
            f.list()
        );
    ComboBox csslist = new ComboBox(options);
    HBox hbox5 = new HBox(10);
    Label csslabel = new Label(labelResource.getString("CSSFiles"));
    hbox5.getChildren().addAll(csslabel, csslist);
    hbox5.setTranslateX(20 + gridWidth);
    hbox5.setTranslateY(300);
    csslist.valueProperty().addListener((observable, oldValue, newValue) -> {

        STYLESHEET = "cssfiles/"+newValue.toString();
        if (newValue.toString().equals("none.css")){
          scene.getStylesheets().clear();
        } else {
        scene.getStylesheets().add(getClass().getClassLoader().getResource(STYLESHEET).toExternalForm());}

    });
    root.getChildren().addAll(hbox5);
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

    // init controllable params box
    paramsbox = new VBox(5);
    root.getChildren().add(paramsbox);

    makeConfigDropDownList();
    makeCSSDropDownList();
    makeAllButtons();
    setSpeed();

    scene = new Scene(root);
    //scene.getStylesheets().add(getClass().getClassLoader().getResource(STYLESHEET).toExternalForm());
    return scene;
  }

}
