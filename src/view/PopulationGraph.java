package view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Rectangle;

public class PopulationGraph extends Path {

  private String name;
  private Color color;

  private double x;
  private double y;

  public PopulationGraph(MoveTo mt, String name, Color col) {
    super(mt);
    this.x = mt.getX();
    this.y = mt.getY();
    this.name = name;
    this.color = col;
    this.setStroke(this.color);
  }

  public String getName(){return this.name;}

  public double getX(){return this.x;}
  public double getY(){return this.y;}

  public void addNewLine(LineTo lt){
    this.getElements().add(lt);
    x= lt.getX();
    y= lt.getY();
  }

  public HBox getLabel(){
    HBox hbox = new HBox(3);
    Label label = new Label(name);
    Rectangle rec = new Rectangle(8,8);
    rec.setFill(this.color);
    hbox.getChildren().addAll(label,rec);
    return hbox;
  }



}
