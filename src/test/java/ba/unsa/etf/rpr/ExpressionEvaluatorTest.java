package ba.unsa.etf.rpr;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluatorTest {
    @Test
    void evaluateValid() {
        assertEquals(101, ExpressionEvaluator.evaluate("( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"));
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
}