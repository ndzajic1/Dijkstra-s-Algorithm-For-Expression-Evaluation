package ba.unsa.etf.rpr;



public class ExpressionValidator {
    /* exprParts will contain all parts of the expression, so String[]
        is examined to check expression validity. */
    private final String[] exprParts;

    ExpressionValidator(String arg) {
        exprParts = arg.split(" ");
    }

    /*
        This method conducts the complete validation of the input string.
     */
    public void checkAll() {
        if (!checkAdjacentOperators())
            throw new RuntimeException("ERROR: Illegal input format");
    }

    /* Firstly, note that the expression is invalid if the two adjacent expression parts are
    both numbers, or both operators. There are exceptions for the two adjacent operators,
    and they are defined
     */
    private boolean checkAdjacentOperators() {
        for (int i = 0; i < exprParts.length - 1; i++) {
            if (invalidFormat(exprParts[i], exprParts[i + 1]))
                return false;
        }
        return true;
    }

    // Check whether string can be parsed into Double.
    private boolean isParseable(String s) {
        try {
            Double.parseDouble((s));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* Two adjacent numbers are illegal format.
       If the first one is a number, the second can be anything but "sqrt", "(" or ")".
       In case the second one is a number, the first one mustn't be either "sqrt" or ")".
     */
    private boolean invalidFormat(String exprPart1, String exprPart2) {
        if (isParseable(exprPart1) && isParseable(exprPart2))
            return true;
        else if (isParseable(exprPart1) && "sqrt(".contains(exprPart2))
            return true;
        else if (isParseable(exprPart1))
            return false;
        else if (isParseable(exprPart2) && "sqrt)".contains(exprPart1))
            return true;
        else if (isParseable(exprPart2))
            return false;
        else
            return invalidOperatorsPair(exprPart1, exprPart2);
    }

    /* Now, if two adjacent parts are both operators,
      valid combinations are listed here:
      *     ("(","(");
      *     (")",")");
      *     (any, "(")
      *     ("sqrt", "(")
      *     (any, "sqrt")
    */
    private boolean invalidOperatorsPair(String exprPart1, String exprPart2) {
        if (exprPart1.equals("(") && "sqrt(".contains(exprPart2))
            return false;
        else if (exprPart1.equals("("))
            return true;
        else if (exprPart1.equals(")") && "sqrt(".contains(exprPart2))
            return true;
        else if (exprPart1.equals(")"))
            return false;
        else return !exprPart2.equals("(") && !exprPart2.equals("sqrt");
    }


}
