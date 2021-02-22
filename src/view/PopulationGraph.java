package view;

import java.util.ArrayList;
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
  private double init_x;
  private double y;
  private int totallife;
  private ArrayList<Line> prev_lines = new ArrayList<>();

  public PopulationGraph(MoveTo mt, String name, Color col, int totallife) {
    super(mt);
    this.x = mt.getX();
    this.y = mt.getY();
    this.init_x = mt.getX();
    this.name = name;
    this.color = col;
    this.setStroke(this.color);
    this.totallife = totallife;
  }

  public String getName(){return this.name;}

  public double getX(){return this.x;}
  public double getY(){return this.y;}

  public void addNewLine(Line lt){
    this.getElements().add(lt);
    this.prev_lines.add(lt);
    boolean flag =false;
    this.x = lt.getX();
    this.y = lt.getY();
    for (Line l : prev_lines){
      l.decreaseLife();
      if (l.isDead()){
        flag =true;
      }
      if (flag){
      this.getElements().clear();break;}
    }
    if (flag){
      prev_lines.clear();
      this.x = this.init_x;
      this.getElements().add(new MoveTo(this.x, this.y));
    }
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
