package Model;

public class State {

  protected final int val;

  State(int val) {
    this.val = val;
  }

  int toInteger() {
    return this.val;
  }
}
