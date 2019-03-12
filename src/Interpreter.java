import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Interpreter {
  private int currLineIndex;
  private int currTokenIndex;
  private LinkedList<String> lines;

  public Interpreter(File espFile) throws FileNotFoundException {
    // Reads in the file and initializes this.lines;
    this.lines = new LinkedList<String>();
    this.currLineIndex = 0;
    this.currTokenIndex = 0;
    try {
      Scanner sc = new Scanner(espFile);
      while (sc.hasNextLine()) {
        String currLine = sc.nextLine();
        this.lines.add(currLine);
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public void init() {
    for (int i = 0; i < this.lines.size(); i++) {
      String currLine = this.lines.get(i);
      String[] currLineArr = currLine.split("\\s+");
      this.parseLine(currLineArr);
    }
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