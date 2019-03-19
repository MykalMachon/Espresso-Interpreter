public class UndefinedVariableException extends Exception {
  // Needed to Supress Errors
  private static final long serialVersionUID = 1L;

  /**
   * * UndefinedVariableException
   * 
   * This is the constrcutor for the UndefinedVaraibleException to be used with th
   * Variable class.
   * 
   * @param err a string describing the error and why it happened.
   */
  public UndefinedVariableException(String err) {
    super(err);
  }
}