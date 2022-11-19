package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluatorTest {

    @Test
    void evaluateValid() {

        assertEquals(101, ExpressionEvaluator.evaluate("( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"));
    }

    @Test
    void evaluateValidAgain() {

        assertEquals(5, ExpressionEvaluator.evaluate("( 1 + ( 1 + ( 1 + ( 1 + ( 1 / 1 ) ) ) ) )"));
    }

    @Test
    void evaluateValidUsingInstance() {
        ExpressionEvaluator ev = new ExpressionEvaluator();
        assertEquals(2.2,
                ev.evaluate("( 1 + ( ( 3 / 2 ) * ( 4 / 5 ) ) )"));
    }

    // Checking if the exception is thrown for division by zero.
    @Test
    void checkExceptionDivByZero() {
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("( 1 + ( ( 2 + sqrt ( 1 ) ) * ( 4 / 0 ) ) ) "));
    }

    // Checking if the exception is thrown for the sqrt of negative number.
    @Test
    void checkExceptionSqrtNegative() {
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("( 1 + ( ( 2 + sqrt ( -5 ) ) * ( 4 / 3 ) ) ) "));
    }

    @Test
    void invalidInputAdjacentOperators() {
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("( 1 + ( ( 2 + / 7 ) * ( 4 / 3 ) ) )"));
    }

    @Test
    void invalidInput2() {
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("( 2 2 2 2 * 7 / 3 ) ) )"));
    }

    @Test
    void invalidInput3() {
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("( 0.5 ( 3 * sqrt ( 0.5 ) )"));
    }
    @Test
    void invalidInput4() {
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("( 0.5 ( 3 * sqrt ( 0.5 ) )"));
    }


}