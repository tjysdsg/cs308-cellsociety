package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainView extends Application {
  private Color[] colors = {Color.BLACK,  Color.RED,Color.BLUE, Color.GREEN,};
  private int numRow =10;
  private int numCol =10;
  private HashMap<String,Integer> StatesMap = new HashMap<>(); // number of different states, dead or alive or ,
  private final double gridHeight = 400.0;
  private  final double gridWidth = 600.0;
  private String configFile;
  private Slider speed = new Slider(1,30,1);
  private Label speedValue = new Label(Double.toString(speed.getValue()));
  private Label speedLabel = new Label("speed:");
  private Pane root;
  private GridPane grid;
  private Boolean animationIsStopped = true;
  private VBox statusbox;
  private ArrayList<ArrayList<Rectangle>> gridelements = new ArrayList<>();
  private Timeline animation;
  private int sec=0;
  public static final int FRAMES_PER_SECOND = 1;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;



  @Override
  public void start(Stage stage){
    Scene scene = new Scene(createContent());
    stage.setScene(scene);
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step());
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    stage.show();
  }

  private void step(){
    sec++;
    DisplayStatus();
    UpdateGridPane();
  }

  private void StartSimulation(){
    if (configFile ==null){
      Alert alert = new Alert(AlertType.WARNING);
      alert.setContentText("Please select a config file to start");
      alert.show();
    }
    else if (!animationIsStopped){
      return;
    } else if (root.getChildren().contains(grid)){
      return;
    }
    else {
      numRow = sample_controller.getnumRow(configFile);
      numCol = sample_controller.getnumCol(configFile);
      SetGridPane(numRow, numCol);
      DisplayStatus();
      animation.play();
      animationIsStopped =false;
    }
  }

  private void PauseSimulation(){
    animation.stop();
    animationIsStopped = true;
  }

  private void ResumeSimulation(){
    if (configFile!=null && animationIsStopped){
    animation.play();
    animationIsStopped = false;}
  }

  private void ResetSimulation(){
    sec=0;
    PauseSimulation();
    if (root.getChildren().contains(grid)){
    root.getChildren().remove(grid);
    gridelements.clear();}
    StartSimulation();
  }

  private void StepSimulation(){
    PauseSimulation();
    step();
  }

  private void MakeAllButtons(){
    makeButton pausebtn = new makeButton("pause", 30, 100, 40, 0, e->PauseSimulation());
    makeButton resumebtn = new makeButton("resume", 30, 100, 40, 0, e->ResumeSimulation());
    makeButton exitbtn = new makeButton("exit", 30, 100, 40, 0, e->System.exit(0));
    HBox hbox1 = new HBox(15);
    hbox1.getChildren().addAll(pausebtn,resumebtn, exitbtn);

    makeButton resetbtn = new makeButton("reset", 30, 100, 40, 0, e->ResetSimulation());
    makeButton startbtn = new makeButton("start", 20, 100, 40, 0, e->StartSimulation());
    makeButton ffbtn = new makeButton("step", 20, 150, 40, 0, e->StepSimulation());
    HBox hbox2 = new HBox(15);
    hbox2.getChildren().addAll(resetbtn,startbtn,ffbtn);

    VBox allbtn = new VBox(15);
    allbtn.setTranslateX(400);
    allbtn.setTranslateY(500);
    allbtn.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY,
        BorderWidths.DEFAULT)));
    allbtn.getChildren().addAll(hbox1,hbox2);
    root.getChildren().addAll(allbtn);
  }

  private void DisplayStatus(){
    StatesMap = sample_controller.getStatisticsMap(configFile);
    StatesMap.put(configFile+" Time Elapsed: ", sec);
    statusbox.getChildren().clear();
    for (String s : StatesMap.keySet()){
      HBox temp = new HBox(15);
      Text display_text = new Text();
      Label text_lable = new Label(s);
      display_text.setText(Integer.toString(StatesMap.get(s)));
      display_text.setFont(display_text.getFont().font(20));
      temp.getChildren().addAll(text_lable,display_text);
      statusbox.getChildren().add(temp);
    }
  }

  private void SetSpeed(){
    speed.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        speedValue.setText(Double.toString(newValue.doubleValue()));
        animation.setRate(newValue.doubleValue());
      }
    });
    HBox hbox3 = new HBox(15);
    hbox3.getChildren().addAll(speedLabel,speed,speedValue);
    hbox3.setTranslateX(700);
    hbox3.setTranslateY(100);
    root.getChildren().addAll(hbox3);
  }

  private void SetGridPane(int r, int c){
    ArrayList<ArrayList<Integer>> doublearray = sample_controller.getGrid(configFile);
    grid = new GridPane();
    grid.setTranslateX(100);
    grid.setTranslateY(100);
    for (int i=0; i<r; i++){
      ArrayList<Rectangle> temp = new ArrayList<>();
      for (int j=0; j<c;j++){
        int n = doublearray.get(i).get(j);
        Rectangle rec = new Rectangle();
        rec.setFill(colors[n]);
        rec.setWidth(gridWidth/c);
        rec.setHeight(gridHeight/r);
        GridPane.setRowIndex(rec, i);
        GridPane.setColumnIndex(rec,j);
        grid.getChildren().add(rec);
        temp.add(rec);
      }
      gridelements.add(temp);
    }
    root.getChildren().addAll(grid);
  }

  private void UpdateGridPane(){
    ArrayList<ArrayList<Integer>> doublearray = sample_controller.getGrid(configFile);
    for (int i =0; i<10; i++){
      for (int j =0; j<10; j++) {
        int n = doublearray.get(i).get(j);
        gridelements.get(i).get(j).setFill(colors[n]);
      }
    }
  }



  private void MakeComboBox(){
    ObservableList<String> options =
        FXCollections.observableArrayList(
            "config 1",
            "config 2",
            "config 3"
        );
    ComboBox configlist = new ComboBox(options);
    HBox hbox4 = new HBox(15);
    Label configlabel = new Label("config files: ");
    hbox4.getChildren().addAll(configlabel,configlist);
    hbox4.setTranslateX(800);
    hbox4.setTranslateY(500);
    configlist.valueProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        configFile = newValue.toString();
        if (newValue!=oldValue && oldValue!=null){
          PauseSimulation();
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setContentText("You have selected a different config file, press RESET to run it\n "
              + "but current progress will be cleared");
          alert.show();
        }
      }
    });
    root.getChildren().addAll(hbox4);
  }



  private Parent createContent(){
    root = new Pane();
    root.setPrefSize(1000,600);

    statusbox = new VBox(15);;
    statusbox.setTranslateX(50);
    statusbox.setTranslateY(500);
    root.getChildren().add(statusbox);

    MakeComboBox();
    MakeAllButtons();
    SetSpeed();


    return root;

  }

  public static void main(String[] args) {
    launch(args);
  }

}
