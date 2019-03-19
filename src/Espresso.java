import java.io.*;

public class Espresso {
  public static void main(String[] args) {
    try {
      File espFile = new File(args[0]);
      if (!espFile.getName().contains(".esp")) {
        throw new IllegalArgumentException("Not an .esp file");
      }
      if (!espFile.exists()) {
        throw new FileNotFoundException("File doesnt exist");
      }
      Interpreter espInterpreter = new Interpreter(espFile);
      espInterpreter.init();
    } catch (ArrayIndexOutOfBoundsException e) {
      // ? Is thrown when the file has not been passed in
      System.out.println("\nThis is a Interpreter for Espresso!");
      System.out.println("You haven't passed a file in to be interpreted\n");
    } catch (IllegalArgumentException e) {
      // ? Is thrown when the file is not an .esp file
      System.out.println("\nThis is a Interpreter for Espresso!");
      System.out.println("You haven't passed in an .esp file! Please double check your file and try again!\n");
    } catch (FileNotFoundException e) {
      // ? Is thrown when the text passed in does not lead to a valid file
      System.out.println("\nThis is a Interpreter for Espresso!");
      System.out.println(
          "The file that you've passed in as an arguement either does not exist or could not be found.\nPlease double check your file and try again\n");
    }
  }
}