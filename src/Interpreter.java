import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;

public class Interpreter {
  private int currLineIndex;
  private int currTokenIndex;
  private LinkedList<String> lines;
  private Variable[] variableTable = new Variable[122];

  public Interpreter(File espFile) throws FileNotFoundException {
    // Reads in the file and initializes this.lines;
    this.lines = new LinkedList<String>();
    this.currLineIndex = 0;
    this.currTokenIndex = 0;
    for(int i = 0; i < 'z'; i++){
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

  public void init() {
    for (int i = 0; i < this.lines.size(); i++) {
      String currLine = this.lines.get(i);
      String[] currLineArr = currLine.split("\\s+");
      this.parseLine(currLineArr);
    }
  }

  private void parseLine(String[] line) {
    String initialToken = line[0];
    if(initialToken.equals("read")){
      this.getInput(Arrays.copyOfRange(line, 1, line.length));
    } else if (initialToken.equals("print")){
      // This is a print statement
      System.out.println("IS AN OUTPUT STATEMENT");
    } else {
      // This is a assignment statement
      System.out.println("IS AN ASSIGNMENT STATEMENT");
    }
  }

  private void assignValue(Variable val){
    
  }

  private void getInput(String[] line) {
    // ! Should check for line length to ensure the line ends after reading the variable
    Scanner reader = new Scanner(System.in);
    char varToBeAssigned = line[0].charAt(0);
    System.out.print("Enter an integer number for variable " + varToBeAssigned + ": ");
    this.variableTable[varToBeAssigned].setValue(reader.nextInt());
  }

  private void printOutput(String output) {

  }

  private int calcInfixExpressions(String[] exp){
    return 1;
  }
}