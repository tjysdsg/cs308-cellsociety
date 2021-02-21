package model;

public class StateWaTor extends State {

  private boolean moved = false;
  private int nDaysBreed = 0;
  private int nDaysStarve = 0;

  public StateWaTor(StateEnum stateType) {
    super(stateType);
  }

  public void setMoved(boolean moved) {
    this.moved = moved;
  }

  public int getNDaysBreed() {
    return nDaysBreed;
  }

  public void incrementNDaysBreed() {
    ++this.nDaysBreed;
  }

  public void resetNDaysBreed() {
    this.nDaysBreed = 0;
  }

  public int getNDaysStarve() {
    return nDaysStarve;
  }

  public void incrementNDaysStarve() {
    ++this.nDaysStarve;
  }

  public void resetNDaysStarve() {
    this.nDaysStarve = 0;
  }

  public boolean isMoved() {
    return moved;
  }
}
