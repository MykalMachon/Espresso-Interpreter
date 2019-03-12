public class Variable {
  private int value;
  private boolean initialized;

  public Variable() {
    this.initialized = false;
  }

  public void setValue(int value) {
    this.value = value;
    this.initialized = true;
  }

  public int getValue() throws UndefinedVariableException {
    if (this.initialized) {
      return this.value;
    } else {
      throw new UndefinedVariableException("Variable has not yet been initialized!");
    }
  }
}
