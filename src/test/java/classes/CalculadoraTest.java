package classes;

import dev.matheushbmelo.classes.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraTest {
    @Test
    public void testMetodoDeSomarTrue(){
        Calculadora calc = new Calculadora();
        Assertions.assertTrue(calc.soma(2, 2) == 4);
    }

    @Test
    public void testMetodoDeSomarEquals(){
        Calculadora calc = new Calculadora();
        Assertions.assertEquals(7, calc.soma(2, 5));
    }

    @Test
    public void testAssertivas(){
        Assertions.assertTrue("casa" == "casa");
        Assertions.assertTrue("casa".equalsIgnoreCase("Casa"));
        Assertions.assertFalse("casa" == "Casa");
        Assertions.assertEquals(5, 5);
        Assertions.assertNotEquals(2, 3);

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = null;

        Assertions.assertSame(list1, list1); // Compara as referências - Devem ser as mesmas.
        Assertions.assertNotSame(list1, list3);
        Assertions.assertNull(list3);
        Assertions.assertNotNull(list2);
        // Assertions.fail("Uma falha ocorreu");
    }
}
