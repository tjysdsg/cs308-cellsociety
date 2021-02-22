package cellsociety;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Language;
import view.MainView;

public class Main extends Application {
  private int counter =0;
  public static void main(String[] args) {
    launch(args);
  }

  public void runOneSimulation(Stage newstage){
    counter++;
    Controller controller = new Controller();
    Language lan_view = new Language(controller, newstage);
    newstage.setScene(lan_view.getLanguageScene());
    newstage.setTitle("Cell Society Simulation "+counter);
    newstage.show();
  }

  @Override
  public void start(Stage stage) throws Exception {
    runOneSimulation(stage);
  }


}
