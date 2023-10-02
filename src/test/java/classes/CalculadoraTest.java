package classes;

import dev.matheushbmelo.classes.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    @Test
    public void testMetodoDeSomarTrue(){
        Calculadora calc = new Calculadora();
        Assertions.assertTrue(calc.soma(2, 5) == 4);
    }

    @Test
    public void testMetodoDeSomarEquals(){
        Calculadora calc = new Calculadora();
        Assertions.assertEquals(7, calc.soma(2, 5));
    }
}
