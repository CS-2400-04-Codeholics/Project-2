
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Driver Test for Calculator
 */
class CalculatorTest
{

    @Test
    void calculatorCompute()
    {
        assertEquals(33, Calculator.calculatorCompute());
    }
}