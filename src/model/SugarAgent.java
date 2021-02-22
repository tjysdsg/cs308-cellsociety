package model;

public class SugarAgent {

  // TODO: init with random value

  private int sugar = 5;
  private int vision = 4;
  private int metabolism = 3;

  public SugarAgent() {
  }

  public SugarAgent(int sugar, int vision, int metabolism) {
    this.sugar = sugar;
    this.vision = vision;
    this.metabolism = metabolism;
  }

  public int getVision() {
    return vision;
  }

  public int getSugar() {
    return sugar;
  }

  public void setSugar(int sugar) {
    this.sugar = sugar;
  }

  public int getMetabolism() {
    return metabolism;
  }
}
