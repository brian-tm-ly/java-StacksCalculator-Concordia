import java.util.EmptyStackException;

public class StacksCalculator {
    private MyStack<Double> valueStack;
    private MyStack<String> operatorStack;

    public StacksCalculator() {
        valueStack = new MyStack<>();
        operatorStack = new MyStack<>();
    }

    //Method to evaluate precedence of operators
    public int precedence(String operator) {
        switch (operator) {
            case "^":
                return 6; // Highest precedence for '^'
            case "*":
            case "/":
                return 5;
            case "+":
            case "-":
                return 4;
            case ">":
            case ">=":
            case "<=":
            case "<":
                return 3;
            case "==":
            case "!=":
                return 2;
            default:
                return -1;
        }
    }

    //Method to apply the operator on the operands
    public double applyOperator(String operator, double operand1, double operand2) {
        switch (operator) {
            case "^":
                return (double) Math.pow(operand1, operand2); //operand1^operand2
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case ">":
                return operand1 > operand2 ? 1 : 0; //Return 1 if true, 0 if false, for comparison operators
            case ">=":
                return operand1 >= operand2 ? 1 : 0;
            case "<=":
                return operand1 <= operand2 ? 1 : 0;
            case "<":
                return operand1 < operand2 ? 1 : 0;
            case "==":
                return operand1 == operand2 ? 1 : 0;
            case "!=":
                return operand1 != operand2 ? 1 : 0;
            default:
                throw new IllegalArgumentException("Error: Invalid operator"); //Throw exception if invalid operator

        }
    }

    //Method to do operations
    public void doOperation() {
        double x = valueStack.pop(); //Pop the operands from the value stack
        double y = valueStack.pop();
        String operator = operatorStack.pop(); //Pop the operator from the operator stack
        double result = applyOperator(operator, y, x); //Apply the operator on the operands
        valueStack.push(result); //Push the result to the value stack
    }

    //Method to evaluate the expression
    public double evalExpression(String expression) {
        expression = expression.replace("(", " ( ").replace(")", " ) "); // Add spaces around parentheses
        String[] tokens = expression.split("\\s+"); //Split the expression into tokens

        //For loop to evaluate expression depending on whether token is a number, operator or parenthesis
        for (String token : tokens) {
            if (isNumber(token)) //If token is number, push onto value stack
                valueStack.push(Double.parseDouble(token));
            else if (token.equals("(")) { //if token is opening parentheses, push onto operator stack
                operatorStack.push(token);
            } else if (token.equals(")")) { //If token is closing parentheses, do operations until opening parentheses is found on operator stack
                while (!operatorStack.isEmpty() && !operatorStack.top().equals("(")) {
                    doOperation();
                }
                //Remove the opening parentheses from operator stack
                if (!operatorStack.isEmpty())
                    operatorStack.pop();
                else {
                    throw new IllegalArgumentException("Mismatched parentheses in expression");
                }
            } else { //If token is operator 
                while (!operatorStack.isEmpty() && valueStack.size() > 1
                        && precedence(token) <= precedence(operatorStack.top())) {
                    doOperation();
                }
                operatorStack.push(token);
            }
        }

        //Repeat operations until operator stack is empty
        while (valueStack.size() > 1 && !operatorStack.isEmpty()) {
            doOperation();
        }

        // Return the result
        if (!valueStack.isEmpty()) {
            double result = valueStack.top();
            // Empty the valueStack
            while (!valueStack.isEmpty()) {
                valueStack.pop();
            }

            // Empty the operatorStack
            while (!operatorStack.isEmpty()) {
                operatorStack.pop();
            }
            return result;
        } else {
            throw new IllegalArgumentException("Invalid expression");
        }
    }

    //Method to check if token is number
    public boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
