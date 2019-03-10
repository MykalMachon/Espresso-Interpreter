import java.io.*;

public class Interpreter {
  private int currLine;
  private int currToken;
  private File espFile;

  public Interpreter(File espFile) {
    this.espFile = espFile;
    // Convert File into an array of line strings.
    // Convert line strings into tokens broken apart by spaces.
    // For each set of tokens, parseLine(line)
  }

  public void init() {
    // Initializes the class
  }

  private void parseLine(String[] line) {
    // Read the tokens and execute what is instructed
  }

  private void getInput(String variableValue) {
    // Should return an instantiated variable with the value taken from the user
    // Get an integer from the user
    // Assign it to the variable name
    // Return the variable
  }

  private void printOutput(String output) {
    // Should print out the output stirng
  }
}