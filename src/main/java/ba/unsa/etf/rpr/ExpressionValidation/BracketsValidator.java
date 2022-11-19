package ba.unsa.etf.rpr.ExpressionValidation;

import java.util.Stack;

public class BracketsValidator extends ExpressionValidator {
    /* This class will cover invalid inputs regarding number of brackets, number of ops in brackets,
        and check if all brackets are properly closed.
     */
    public BracketsValidator(String arg) {
        super(arg);
    }

    // This method will perform all methods implemented in this class.
    public boolean integralBracketsCheck() {
        return checkEnds() && validBracketsOrder() && validNumberOfOpsInBrackets();
    }

    /* Expression must start with "(", and end with ")".
     Also, it must be longer than three parts.
     */
    private boolean checkEnds() {
        if (exprParts.length < 4)
            return false;
        return exprParts[0].equals("(") && exprParts[exprParts.length - 1].equals(")");
    }

    /*
        Famous algorithm to check if all brackets are closed in a proper way.
     */
    private boolean validBracketsOrder() {
        Stack<String> stack = new Stack<>();
        for (String s : exprParts) {
            if (s.equals("("))
                stack.push(s);
            else if (s.equals(")")) {
                stack.pop();
            }
        }
        return stack.empty();
    }

    /* Number of () pairs must be equal to the number of performed ops.
    sqrt will not make a problem here, as it must be followed by a pair of brackets.
    This will also eliminate the danger of redundant pairs of brackets.
     */
    private boolean validNumberOfOpsInBrackets() {
        int countOps = count("+", exprParts) + count("-", exprParts) + count("*", exprParts)
                + count("/", exprParts) + count("sqrt", exprParts);
        return count("(", exprParts) == countOps;
    }

    /*
        Counting array entry.
     */
    private static int count(String entry, String[] array) {
        int i = 0;
        for (String s : array) {
            if (s.equals(entry))
                i++;
        }
        return i;
    }
}



