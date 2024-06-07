import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class StacksCalculatorDemo {
    public static void main(String[] args) {
        StacksCalculator calculator = new StacksCalculator();
        Scanner sc = null;
        PrintWriter pw = null;
        String expression = "";
        Double result = 0.0;
        try {
            sc = new Scanner(new FileInputStream("inputFile.txt"));
            pw = new PrintWriter(new FileOutputStream("outputFile.txt"));
            while (sc.hasNextLine()) {
                expression = sc.nextLine();
                pw.println(expression + " = ");
                result = calculator.evalExpression(expression);
                pw.println("Result: " + result);
            }
            sc.close();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading or opening file.");
        }

    }
}
