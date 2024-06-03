import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class StacksCalculator {
    // Method to process the expression from a file
    private String processExpressionFromFile(String inputFile, String outputFile) {
        Scanner sc = null;
        PrintWriter pw = null;
        String expression = "";
        Double result = 0.0;
        try {
            sc = new Scanner(new FileInputStream(inputFile));
            pw = new PrintWriter(new FileOutputStream(outputFile));
            while (sc.hasNextLine()) {
                expression = sc.nextLine();
                pw.println(expression + "=");
                result = evalExpression(expression);
                pw.print(result);
            }
            sc.close();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading or opening file.");
        }
    }

    // Method to evaluate the expression
    private void evalExpression(String exp) {
        MyStack<Integer> operands = new MyStack<>();
        MyStack<Character> operators = new MyStack<>();
        for (int i = 0; i < exp.length(); i++) {

        }

    }

    // Method to evaluate the expression
    private double performOperation(MyStack<Integer> operands, MyStack<Character> operations) {

    }

    //Method to evaluate precedence of operators
    public

    public static void main(String[] args) {

    }
}
