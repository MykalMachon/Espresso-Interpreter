public class Variable {
  private int value;
  private boolean initialized;

  /**
   * * Variable
   * 
   * The Constructor for the Variable class. Sets this.initialized to false to
   * signify the fact that the variable has not yet been initialized with a value
   */
  public Variable() {
    this.initialized = false;
  }

  /**
   * * setValue
   * 
   * setValue sets a Variable Instance's this.value to equal the passed in value.
   * 
   * @param value an integer value to set the variables value to
   * @return void
   */
  public void setValue(int value) {
    this.value = value;
    this.initialized = true;
  }

  /**
   * * getValue
   * 
   * getValue returns a integer value that the varaible is defined as. If the
   * variable is not initialized, it throws a new UndefinedVariableException
   * 
   * @return int of the variables value
   */
  public int getValue() throws UndefinedVariableException {
    if (this.initialized) {
      return this.value;
    } else {
      throw new UndefinedVariableException("Variable has not yet been initialized!");
    }
  }
}
