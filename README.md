# COMP 251 AB2 - Assignment #2

## Espresso Interpreter Project

**Mykal Machon : 300128330**

### Getting Started

If using a text editor and the command line :

1. Open a command line and navigate to the `./src` folder with this project in it.
2. Compile all the java files in the folder using the command`javac ./\*.java`
3. Drag in an espresso file (.esp) you wish to test the program with or use the included `test.esp` file
4. Run the program using the command `java espresso <path_to_espresso_file>` where `<path_to_espresso_file>` is a path to a .esp file
5. The interpreter should now write its output to the console!

If using Eclipse Compiler :

1. Open the eclipse editor and open the `./src` folder with this project in it
2. Go to the run menu and click "Run Configuration"
3. Select the arguements tab and type a path to the input file in the Program arguments box (if it is in the src folder you can just type the name of the file)

### File Layout

The following files were developed with the purpose of providing an Espresso Interpreter

- Espresso.java
- Interpreter.java
- UndefinedVariableException.java
- Variable.java

and for convenience, a test espresso file

- test.esp

Here is a brief explanation of each file. For more in-depth explanations of their inner workings see the comments above each method in each file

#### Espresso.java

Espresso.java acts as the "user interface" that allows the user to interact with the interpreter. You pass in the file throug the use of `args[0]` to the program and it then runs some initial checks to make sure that this is a valid file, if those pass, then the file is passed on to Interpreter.java

#### Interpreter.java

This is the primary class for this program and provides the majority of the functionality. This is what the class will typically do when interpreting a espresso file :

1. Read in a file and break it into lines
2. Each line is passed into a parser method which breaks it into tokens (by breaking the string on each space) and determines what that _type_ of line is being examined.
3. Depending on the type of statement/line is determined, the parser method will pass the line into one of three methods: `getInput` for input statements, `printOutput` for output statements, or `assignValue` for assignment statements.
4. both `printOutput` and `assignValue` will calculate the infix statements handed to them and either print them or assign them to a variable stored in an array of variables. They do this by converting the infix statement to a postfix statement and then calculating it.
5. `getInput` simply takes a user-selected integer and assigns it to a variable kept in an array of variables
6. Steps 2-5 are repeated until either 1) the file has been fully interpreted or 2) there is an error. Upon errors the program states the type of error and exits.

#### UndefinedVariableExceptio.java

This is simply a custom exception to be used with the Variable.java class. It is thrown when the `getValue` method is called on a Variable object that has not yet been initialized with a value;

#### Variable.java

This is similar to the class outlined in the assignment PDF.

#### test.esp

This is simply a test.esp file that was used for tesing the program throughout development. It is left in for the instructors convenience to copy and paste .esp codes into the program.
