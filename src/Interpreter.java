import java.io.*;
import java.util.InputMismatchException;;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Stack;

public class Interpreter {
  private LinkedList<String> lines;
  private Variable[] variableTable = new Variable[123];

  private String currLine;
  private int currLineNum = 0;

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
    for (int i = 0; i <= 'z'; i++) {
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
      this.currLineNum++;
      this.currLine = this.lines.get(i);
      String[] currLineArr = this.currLine.split("\\s+");
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
      this.printOutput(Arrays.copyOfRange(line, 1, line.length));
    } else {
      this.assignValue(line);
    }
  }

  /**
   * * getInput
   * 
   * This method gets input from users and stores it in a variable. This method is
   * typically called as a result of read call in an espresso file
   * 
   * @param line an array of strings that contains the appropriate tokens for a
   *             read call
   * @return void
   */
  private void getInput(String[] line) {
    // ! Should check for line length to ensure the line ends after reading the
    // variable
    try {
      Scanner reader = new Scanner(System.in);
      char varToBeAssigned = line[0].charAt(0);
      System.out.print("Enter an integer number for variable " + varToBeAssigned + ": ");
      this.variableTable[varToBeAssigned].setValue(reader.nextInt());
    } catch (InputMismatchException e) {
      System.out.println("\nerror: The character entered was not an integer number. Run Espresso Again.\n");
      System.exit(0);
    }

  }

  /**
   * * printOutput
   * 
   * This method takes in an infix expression, calculates its value, and prints it
   * to the console.
   * 
   * @param line an array of strings that contains the appropriate tokens for a
   *             printOutput call. This is only an infix expression
   * @return void
   */
  private void printOutput(String[] line) {
    System.out.println(calcInfixExpression(line));
  }

  /**
   * * assignValue
   * 
   * This method takes in assignment statements, calculates the right hand side
   * (an infix expression) and assigns a value to the variable on the left hand
   * side (a variable in the variable table)
   * 
   * @param line an array of strings that contains the appropriate tokens for an
   *             assignment call. This inlcudes a variable name, an equals sign,
   *             and an infix expression
   * @return void
   */
  private void assignValue(String[] line) {
    char varToBeAssigned = line[0].charAt(0);
    if (line[1].charAt(0) == '=') {
      int variableValue = this.calcInfixExpression(Arrays.copyOfRange(line, 2, line.length));
      this.variableTable[varToBeAssigned].setValue(variableValue);
    } else {
      System.out.println("\nLine " + this.currLineNum + ". " + this.currLine);
      System.out.println("Syntax error.\n");
      System.exit(0);
    }
  }

  /**
   * * calcInfixExpression
   * 
   * This method takes in an infix expression in the form of an array of Strings
   * and first converts it into a postfix expression, then solves the postfix
   * expression, then returns the solved value.
   * 
   * @param exp an array of strings that contains an infix expression.
   * @return int: value of the solved infix expression.
   */
  private int calcInfixExpression(String[] exp) {
    String[] postfixTokens = convertInfixToPostfix(exp);
    int evaluatedPostfix = calcPostfixExpression(postfixTokens);
    return evaluatedPostfix;
  }

  /**
   * * convertInfixToPostfix
   * 
   * This method takes an infix expression and converts it to a postfix
   * expression. This is done using a stack and precedence and associativity laws.
   * 
   * @param infixExp an array of strings that contains an infix expression.
   * @return String[]: set of tokens in a postfix expression
   */
  private String[] convertInfixToPostfix(String[] infixExp) {
    Stack<String> operatorStack = new Stack<String>();
    String postfixString = "";
    try {
      for (int i = 0; i < infixExp.length; i++) {
        if (Character.isLetter(infixExp[i].charAt(0))) {
          try {
            postfixString += (this.variableTable[infixExp[i].charAt(0)].getValue() + " ");
          } catch (UndefinedVariableException e) {
            System.out.println("\nLine " + this.currLineNum + ". " + this.currLine);
            System.out.println("error: variable " + infixExp[i] + " is not defined.\n");
            System.exit(0);
          }
        } else if (Character.isDigit(infixExp[i].charAt(0))) {
          postfixString += (infixExp[i].charAt(0) + " ");
        } else if (infixExp[i].charAt(0) == '(') {
          operatorStack.push("(");
        } else if (infixExp[i].charAt(0) == '+' || infixExp[i].charAt(0) == '-' || infixExp[i].charAt(0) == '*'
            || infixExp[i].charAt(0) == '/') {
          if (operatorStack.isEmpty()) {
            operatorStack.push(infixExp[i]);
          } else {
            boolean greaterPrecFound = false;
            while ((!operatorStack.isEmpty()) && (!operatorStack.peek().equals("(")) && !greaterPrecFound) {
              if (checkPrecedence(operatorStack.peek(), infixExp[i])) {
                postfixString += (operatorStack.pop() + " ");
              } else {
                greaterPrecFound = true;
              }
            }
            operatorStack.push(infixExp[i]);
          }
        } else if (infixExp[i].charAt(0) == ')') {
          while (!operatorStack.peek().equals("(")) {
            postfixString += (operatorStack.pop() + " ");
          }
          // pop the final ( off of the stack
          operatorStack.pop();
        } else {
          System.out.println("\nLine " + this.currLineNum + ". " + this.currLine);
          System.out.println("Syntax error.\n");
          System.exit(0);
        }
        if (i + 1 == infixExp.length) {
          while (!operatorStack.isEmpty()) {
            postfixString += (operatorStack.pop() + " ");
          }
        }

      }
    } catch (Exception e) {
      System.out.println("\nLine " + this.currLineNum + ". " + this.currLine);
      System.out.println("Syntax error.\n");
      System.exit(0);
    }

    String[] postfixTokens = postfixString.split("\\s+");
    return postfixTokens;
  }

  /**
   * * checkPrecedence
   * 
   * Takes in two operators and determines if the current operator is of greater
   * or equal precedence to the new operator
   * 
   * @param opOne string containing an operator
   * @param opTwo string containing an operator
   * @return boolean true if currOp >= newOp, false if currOp < newOp
   */
  private boolean checkPrecedence(String opOne, String opTwo) {
    if (this.getPrecedence(opOne) >= this.getPrecedence(opTwo)) {
      return true;
    } else {
      return false;
    }
  }

  private int getPrecedence(String operator) {
    if (operator.equals("*") || operator.equals("/")) {
      return 2;
    } else if (operator.equals("+") || operator.equals("-")) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * * calcPostfixExpression
   * 
   * This method calculates a given postfix expression and returns the value as an
   * int.
   * 
   * @param exp an array of strings that contains an postfix expression
   * @return int: value of the solved postfix expression
   */
  private int calcPostfixExpression(String[] exp) {
    Stack<String> postfixStack = new Stack<String>();
    int returnValue = 0;
    try {
      for (int i = 0; i < exp.length; i++) {
        if (Character.isDigit(exp[i].charAt(0))) {
          postfixStack.push(exp[i]);
        } else if (exp[i].equals("*") || exp[i].equals("/") || exp[i].equals("+") || exp[i].equals("-")) {
          int rightHandSide = Integer.parseInt(postfixStack.pop());
          int leftHandSide = Integer.parseInt(postfixStack.pop());
          int resultInt;
          if (exp[i].equals("*")) {
            resultInt = leftHandSide * rightHandSide;
          } else if (exp[i].equals("/")) {
            resultInt = leftHandSide / rightHandSide;
          } else if (exp[i].equals("+")) {
            resultInt = leftHandSide + rightHandSide;
          } else if (exp[i].equals("-")) {
            resultInt = leftHandSide - rightHandSide;
          } else {
            // ! THIS IS AN ERROR
            resultInt = 0;
          }
          postfixStack.push(Integer.toString(resultInt));
        }
        if (i + 1 == exp.length) {
          if (postfixStack.size() == 1) {
            returnValue = Integer.parseInt(postfixStack.pop());
          } else {
            // ERROR OF SOME SORT
          }
        }
      }
      return returnValue;
    } catch (Exception e) {
      System.out.println("\nLine " + this.currLineNum + ". " + this.currLine);
      System.out.println("Syntax error.\n");
      System.exit(0);
      return 0;
    }

  }

}
