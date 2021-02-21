package cellsociety;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Language;
import view.MainView;

public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Controller controller = new Controller();
    Language lan_view = new Language(controller, stage);

    //MainView view = new MainView(controller);
    //Scene scene = view.createScene();
    stage.setScene(lan_view.getLanguageScene());
    stage.setTitle("Cell Society");
    stage.show();

    //controller.setView(view);
  }
}
