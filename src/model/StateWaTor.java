package model;

public class StateWaTor extends State {

  private boolean moved = false;

  /**
   * The number of days since last breeding
   */
  private int nDaysBreed = 0;

  /**
   * The number of days since last eating
   */
  private int nDaysStarve = 0;

  /**
   * @see State#State(StateEnum)
   */
  public StateWaTor(StateEnumWaTor stateType) {
    super(stateType);
  }

  /**
   * Mark this FISH/SHARK as moved
   */
  public void setMoved(boolean moved) {
    this.moved = moved;
  }

  /**
   * @see StateWaTor#nDaysBreed
   */
  public int getNDaysBreed() {
    return nDaysBreed;
  }

  /**
   * Increase {@link StateWaTor#nDaysBreed} by 1
   */
  public void incrementNDaysBreed() {
    ++this.nDaysBreed;
  }

  /**
   * Set {@link StateWaTor#nDaysBreed} to 0
   */
  public void resetNDaysBreed() {
    this.nDaysBreed = 0;
  }

  /**
   * @see StateWaTor#nDaysStarve
   */
  public int getNDaysStarve() {
    return nDaysStarve;
  }

  /**
   * Increase {@link StateWaTor#nDaysStarve} by 1
   */
  public void incrementNDaysStarve() {
    ++this.nDaysStarve;
  }

  /**
   * Set {@link StateWaTor#nDaysStarve} to 0
   */
  public void resetNDaysStarve() {
    this.nDaysStarve = 0;
  }

  /**
   * Is this FISH/SHARK as moved?
   */
  public boolean isMoved() {
    return moved;
  }
}
