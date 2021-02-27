package model;

public class SugarAgent {

  // TODO: init with random value

  /**
   * Agent sugar level
   */
  private int sugar = 5;

  /**
   * How many tiles along a direction the agent can see
   */
  private int vision = 4;

  /**
   * How many sugar an agent consumes per turn
   */
  private int metabolism = 3;

  /**
   * Default constructor
   */
  public SugarAgent() {
  }

  /**
   * Constructor
   *
   * @param sugar      {@link SugarAgent#sugar}
   * @param vision     {@link SugarAgent#vision}
   * @param metabolism {@link SugarAgent#metabolism}
   */
  public SugarAgent(int sugar, int vision, int metabolism) {
    this.sugar = sugar;
    this.vision = vision;
    this.metabolism = metabolism;
  }

  /**
   * @see SugarAgent#vision
   */
  public int getVision() {
    return vision;
  }

  /**
   * @see SugarAgent#sugar
   */
  public int getSugar() {
    return sugar;
  }

  /**
   * Set {@link SugarAgent#sugar}
   */
  public void setSugar(int sugar) {
    this.sugar = sugar;
  }

  /**
   * @see SugarAgent#metabolism
   */
  public int getMetabolism() {
    return metabolism;
  }
}
