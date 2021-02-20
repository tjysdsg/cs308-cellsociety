package cellsociety;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainView;

public class Main extends Application {
  private String STYLESHEET = "test.css";

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Controller controller = new Controller();
    MainView view = new MainView(controller);
    Scene scene = view.createScene();
    scene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
    stage.setScene(scene);
    stage.setTitle("Cell Society");
    stage.show();

    controller.setView(view);
  }
}
