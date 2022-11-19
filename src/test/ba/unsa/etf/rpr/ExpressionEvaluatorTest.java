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

    @Test
    void checkMain(){
        assertThrows(RuntimeException.class, ()->{
            String[] str=new String[1];
            str[0]="( 1 + sqrt 4 ) )";
            App.main(str);
        });
}
@Test
    void invalidBrackets1(){
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("( 3 * ( sqrt ( 0.5 ) + 5 )"));
    }
    @Test
    void invalidBrackets2(){
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("( 0.5 / ( 3 + 5 ) ("));
    }
    @Test
    void invalidBrackets3(){
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("( 0.5 )"));
    }
    @Test
    void invalidBrackets4(){
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("0.5 * 0.5"));
    }
    @Test
    void invalidBrackets5(){
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("( 1 + ( 2 * 3 / 4 ) )"));
    }
    @Test
    void invalidInput5(){
        assertThrows(RuntimeException.class, () ->
                ExpressionEvaluator.evaluate("( 1 + ( 2 * rpr / 4 ) )"));
    }


}