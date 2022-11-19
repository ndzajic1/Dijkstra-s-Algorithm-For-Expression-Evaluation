package ba.unsa.etf.rpr.ExpressionValidation;


import ba.unsa.etf.rpr.ExpressionEvaluator;

public class ExpressionValidator {
    /* exprParts will contain all parts of the expression, so String[]
        is examined to check expression validity.  expr will contain the original expression.*/
    protected final String[] exprParts;
    private final String expr;

    // Exception will be thrown immediately if the input is nonsense.
    public ExpressionValidator(String arg) {
        expr = arg;
        exprParts = arg.split(" ");
        basicCheck(exprParts);
    }

    private void basicCheck(String[] str) {
        for (String s : str) {
            if (!ExpressionEvaluator.opsDomain.containsKey(s) && !s.equals("(") && !s.equals(")") && !isParseable(s))
                throw new RuntimeException("Illegal input format");
        }
    }

    /*
        This method conducts the complete validation of the input string.
     */
    public void checkAll() {
        OrderValidator ev = new OrderValidator(expr);
        BracketsValidator bv = new BracketsValidator(expr);
        if (!ev.orderCheck() || !bv.integralBracketsCheck())
            throw new RuntimeException("ERROR: Illegal input format");
    }

    protected static boolean isParseable(String s) {
        try {
            Double.parseDouble((s));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
