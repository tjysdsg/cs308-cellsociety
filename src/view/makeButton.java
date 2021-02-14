package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class makeButton extends StackPane {
  private Text text;

  public makeButton(String name, int font , int w, int h, double angle,
      EventHandler<MouseEvent> handler){
    text = new Text(name);
    text.setFont(text.getFont().font(font));
    text.setFill(Color.WHITE);
    Rectangle bg = new Rectangle(w,h);
    bg.setOpacity(0.6);
    bg.setFill(Color.BLACK);
    GaussianBlur blur= new GaussianBlur(3.5);
    bg.setEffect(blur);
    setAlignment(Pos.CENTER);
    setRotate(-1*angle);
    getChildren().addAll(bg,text);
    setOnMouseEntered(e ->{
      bg.setTranslateX(10);
      text.setTranslateX(10);
      bg.setFill(Color.GREY);
      text.setFill(Color.BLACK);
    });

    setOnMouseExited(e ->{
      bg.setTranslateX(0);
      text.setTranslateX(0);
      bg.setFill(Color.BLACK);
      text.setFill(Color.WHITE);
    });

    DropShadow drop = new DropShadow(50, Color.BLUE);
    drop.setInput(new Glow());
    setOnMousePressed(e -> setEffect(drop));
    setOnMouseReleased(e -> setEffect(null));
    setOnMouseClicked(handler);
  }
}
