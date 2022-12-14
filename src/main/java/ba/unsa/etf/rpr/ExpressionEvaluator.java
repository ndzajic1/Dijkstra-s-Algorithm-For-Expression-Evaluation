package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.ExpressionValidation.ExpressionValidator;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.BiFunction;

public class ExpressionEvaluator {
    /*  These two stacks are the basic structures for the implementation of this algorithm.
        values to collect numbers found in expression, operations to collect strings
        resembling the operation to be done.
     */

    private static final Stack<Double> values = new Stack<>();
    private static final Stack<String> operations = new Stack<>();

    /* This attribute encapsulates operator symbols which will be read from the input string
       with their respective functions that are evaluated.
     */
     public static final HashMap<String, BiFunction<Double, Double, Double>> opsDomain = new HashMap<>();


    public ExpressionEvaluator() {

    }

    /* Initialize HashMap in the constructor.
           All valid operators will be put into the map as keys, and their respective operations
            will be the entry values. Exceptions are also handled here.
         */
    static {
        opsDomain.put("+", Double::sum);
        opsDomain.put("-", (Double x, Double y) -> (x - y));
        opsDomain.put("*", (Double x, Double y) -> (x * y));
        opsDomain.put("/",
                (Double x, Double y) -> {
                    if (y == 0)
                        throw new IllegalArgumentException("Illegal operation: division by zero");
                    return x / y;
                });
        /* sqrt is represented by BiFunction, although it clearly has only one argument.
        When evaluating, this second parameter will obviously be ignored.
         */
        opsDomain.put("sqrt", (Double x, Double y) -> {
            if (x < 0)
                throw new IllegalArgumentException("Illegal operation: square root of negative number");
            return Math.sqrt(x);
        });
    }

    @SuppressWarnings({"UnusedReturnValue", "StatementWithEmptyBody"})
    public static Double evaluate(String expr) {
        ExpressionValidator ev=new ExpressionValidator(expr);
        ev.checkAll();
        for (String s : expr.split(" ")) {
            if (opsDomain.containsKey(s))
                operations.push(s);
            else if (s.equals("(")) ;
            else if (s.equals(")")) {
                Double val = values.pop();
                String op = operations.pop();
                if (op.equals("sqrt"))
                    val = opsDomain.get(op).apply(val, val);
                else
                    val = opsDomain.get(op).apply(values.pop(), val);
                values.push(val);
            } else
                values.push(Double.parseDouble(s));
        }
        return values.pop();
    }

}
