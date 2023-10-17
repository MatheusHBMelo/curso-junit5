package dev.matheushbmelo.classes;

public class Calculadora {
    public int soma(int a, int b) {
        return a + b;
    }

    public float divisao(int num, int den) {
        return (float) num / den;
    }

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        System.out.println(calc.soma(2, 2) == 4);
        System.out.println(calc.soma(5, 6) == 10);
        System.out.println(calc.soma(1, 8) == 9);
    }
}
