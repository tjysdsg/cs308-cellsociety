package view;

import javafx.scene.shape.LineTo;

public class Line extends LineTo {
  private int life ;
  public Line(double x, double y, int l) {
    super(x, y);
    life = l;

  }


  public void decreaseLife(){
    life = life -1;
  }

  public boolean isDead(){
    return life<0;
  }

}
