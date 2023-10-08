package classes;

import dev.matheushbmelo.classes.Calculadora;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraTest {

    @BeforeEach
    public void setup(){
        System.out.println("---- Teste antes de todos ----");
    }

    @BeforeAll
    public static void setup2(){
        System.out.println("---- Teste inicio uma vez! ----");
    }

    @AfterEach
    public void finish(){
        System.out.println("---- Teste fim de todos ----");
    }
    @AfterAll
    public static void finish2(){
        System.out.println("---- Teste fim uma vez ----");
    }

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

    @Test
    public void testDivisaoPositivos(){
        Calculadora calc = new Calculadora();
        float resultado = calc.divisao(6, 3);
        Assertions.assertEquals(2, resultado);
    }

    @Test
    public void testDivisaoNegativos(){
        Calculadora calc = new Calculadora();
        float resultado = calc.divisao(6, -3);
        Assertions.assertEquals(-2, resultado);
    }

    @Test
    public void testDivisaoFlutuantes(){
        Calculadora calc = new Calculadora();
        float resultado = calc.divisao(10, 3);
        Assertions.assertEquals(3.33, resultado, 0.01);
    }

    @Test
    public void testDivisaoComZeroNumerador(){
        Calculadora calc = new Calculadora();
        float resultado = calc.divisao(0, 5);
        Assertions.assertEquals(0, resultado);
    }

    @Test
    public void testRetornaExcecaoDivisaoPorZeroJunit4(){
        Calculadora calc = new Calculadora();
        try {
            float resultado = 10 / 0;
            Assertions.fail("FALHA - O teste esperava por uma exceção.");
        } catch (ArithmeticException ae) {
        Assertions.assertEquals("/ by zero", ae.getMessage());
        }
    }

    @Test
    public void testRetornaExcecaoDivisaoPorZeroJunit5(){
        ArithmeticException arithmeticException = Assertions.assertThrows(ArithmeticException.class, () -> {
            float resultado = 10 / 0;
        });
        Assertions.assertEquals("/ by zero", arithmeticException.getMessage());
    }
}
