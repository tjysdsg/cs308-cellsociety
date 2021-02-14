package cellsociety;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainView;

public class Main extends Application {

  public static void main(String[] args) {
    System.out.println("Hello world");
  }

  @Override
  public void start(Stage stage) throws Exception {
    Controller controller = new Controller();
    MainView view = new MainView(controller);
    Scene scene = view.createScene();
    stage.setScene(scene);
    stage.show();

    controller.setView(view);
  }
}
