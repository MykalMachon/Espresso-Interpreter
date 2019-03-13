import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

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
    for (int i = 0; i < line.length; i++) {
      String currToken = line[i];
      if(i == 0){
        if(currToken.equals("read")){
          // This is a read statement
          System.out.println("IS AN INPUT STATEMENT");
        } else if (currToken.equals("print")){
          // This is a print statement
          System.out.println("IS AN OUTPUT STATEMENT");
        } else {
          // This is a assignment statement
          System.out.println("IS AN ASSIGNMENT STATEMENT");
        }
      }
    }
  }

  private void assignValue(Variable val){
    
  }

  private void getInput(Variable val) {

  }

  private void printOutput(String output) {

  }

  private int calcInfixExpressions(String[] exp){
    return 1;
  }
}