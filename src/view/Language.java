package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Language {

  private ActionButton en;
  private ActionButton ch;
  public Language(Controller controller, Stage stage) {
    this.en = new ActionButton("English", 50, 300, 100, 0, e -> {
      controller.setLanguage("English");
      controller.intializeView(stage);
    });
    this.ch = new ActionButton("Italiano", 50, 300, 100, 0, e -> {
      controller.setLanguage("Italiano");
      controller.intializeView(stage);
    });
  }

  public Scene getLanguageScene(){
    HBox hbox_lan = new HBox(50);
    hbox_lan.getChildren().addAll(en,ch);
    Pane root = new Pane();
    root.setPrefSize(1000, 600);
    root.getChildren().add(hbox_lan);
    Scene scene = new Scene(root);
    return scene;
  }
}
