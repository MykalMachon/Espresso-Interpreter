import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;

public class Interpreter {
  private LinkedList<String> lines;
  private Variable[] variableTable = new Variable[122];

  /**
   * * Interpreter
   * 
   * This is a constructor for an instance of interpreter. This method does the
   * following 1. Takes the passed in espFile and breaks it into an String array
   * of lines 2. Creates a 124 character variableTable array to store variables
   * from a-z && A-Z
   * 
   * @param espFile A File that references the file passed in from Espresso.java
   *                (the user interface)
   * @throws FileNotFoundException when espFile is not found
   * @return void
   */
  public Interpreter(File espFile) throws FileNotFoundException {
    // Reads in the file and initializes this.lines;
    this.lines = new LinkedList<String>();
    for (int i = 0; i < 'z'; i++) {
      this.variableTable[i] = new Variable();
    }
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

  /**
   * * init
   * 
   * This intializes the interpreter instance. It loops through each line, breaks
   * the line into "tokens" by splitting them on " " and then passes the lines
   * tokens into parseLine() as an array of strings
   * 
   * @return void
   */
  public void init() {
    for (int i = 0; i < this.lines.size(); i++) {
      String currLine = this.lines.get(i);
      String[] currLineArr = currLine.split("\\s+");
      this.parseLine(currLineArr);
    }
  }

  /**
   * * parseLine
   * 
   * This takes in a single line of input from an espresso file and decides what
   * types of line it has, and then runs the line through corresponding methods
   * depending on the type line.
   * 
   * @param line an array of Strings that contain an individual lines tokens
   * @return void
   */
  private void parseLine(String[] line) {
    String initialToken = line[0];
    if (initialToken.equals("read")) {
      this.getInput(Arrays.copyOfRange(line, 1, line.length));
    } else if (initialToken.equals("print")) {
      // This is a print statement
      System.out.println("IS AN OUTPUT STATEMENT");
    } else {
      // This is a assignment statement
      System.out.println("IS AN ASSIGNMENT STATEMENT");
    }
  }

  private void assignValue(Variable val) {

  }

  /**
   * getInput
   * 
   * This method gets input from users and stores it in a variable. This method is
   * typically called as a result of read call in an espresso file
   * 
   * @param line an array of strings that contains the appropriate tokens for a
   *             read call
   */
  private void getInput(String[] line) {
    // ! Should check for line length to ensure the line ends after reading the
    // variable
    Scanner reader = new Scanner(System.in);
    char varToBeAssigned = line[0].charAt(0);
    System.out.print("Enter an integer number for variable " + varToBeAssigned + ": ");
    this.variableTable[varToBeAssigned].setValue(reader.nextInt());
  }

  private void printOutput(String[] line) {
    System.out.println(calcInfixExpression(line));
  }

  private int calcInfixExpression(String[] exp) {
    return 1;
  }
}