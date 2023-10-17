package dev.matheushbmelo.project.domain.models;

import dev.matheushbmelo.project.domain.exceptions.AtributeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste da entidade: Person")
public class PersonTest {

    /*
    Escreva um teste em JUnit 5 para verificar se o método isAdult retorna true quando a idade da pessoa é igual a 18, e false quando a idade é menor que 18.

    Escreva um teste em JUnit 5 para verificar se o método setName está configurando o nome corretamente na instância da classe Person.

    Escreva um teste em JUnit 5 para verificar se o método setAge está configurando a idade corretamente na instância da classe Person.

    Escreva um teste em JUnit 5 para verificar se o construtor da classe Person está configurando corretamente o nome e a idade.

    Escreva um teste em JUnit 5 que verifica se o método getName retorna o nome corretamente.

    Escreva um teste em JUnit 5 que verifica se o método getAge retorna a idade corretamente.
     */

    @Test
    @DisplayName("Verifica se isAdult retorna true com idade >= 18")
    public void verificaSeIsAdultRetornaTrueComIdadeMaiorQue18() {
        Person person = new Person("Matheus", 18);
        Assertions.assertTrue(person.isAdult());
    }

    @Test
    @DisplayName("Verifica se isAdult retorna false com idade < 18")
    public void verificaSeIsAdultRetornaFalseComIdadeMaiorQue18() {
        Person person = new Person("Matheus", 17);
        Assertions.assertFalse(person.isAdult());
    }

    @Test
    @DisplayName("Verifica se setName seta o nome corretamente")
    public void verificaSeSetNameConfiguraNomeCorretamente() {
        Person person = new Person("Matheus", 17);
        person.setName("Gabriel");
        Assertions.assertEquals("Gabriel", person.getName());
    }

    @Test
    @DisplayName("Verifica se setAge seta a idade corretamente")
    public void verificaSeSetAgeConfiguraIdadeCorretamente() {
        Person person = new Person("Matheus", 17);
        person.setAge(21);
        Assertions.assertEquals(21, person.getAge());
    }

    @Test
    @DisplayName("Verifica se o construtor seta os atributos corretamente")
    public void verificaSeConstrutorConfiguraOsAtributosCorretamente() {
        Person person = new Person("Matheus", 17);
        Assertions.assertEquals("Matheus", person.getName());
        Assertions.assertEquals(17, person.getAge());
    }

    @Test
    @DisplayName("Verifica se getName retorna o nome corretamente")
    public void verificaSeGetNameRetornaNomeCorretamente() {
        Person person = new Person("Matheus", 17);
        Assertions.assertEquals("Matheus", person.getName());
    }

    @Test
    @DisplayName("Verifica se getAge retorna a idade corretamente")
    public void verificaSeGetAgeRetornaIdadeCorretamente() {
        Person person = new Person("Matheus", 17);
        Assertions.assertEquals(17, person.getAge());
    }

    @Test
    @DisplayName("Deve rejeitar usuario com nome nulo")
    public void verificarSeRetornaExceptionComNomeNulo() {
        AtributeException ae = Assertions.assertThrows(AtributeException.class,
                () -> new Person(null, 21)
        );
        Assertions.assertEquals("O nome não pode ser nulo", ae.getMessage());
    }

    @Test
    @DisplayName("Deve rejeitar usuario com idade nula")
    public void verificarSeRetornaExceptionComIdadeNula() {
        AtributeException ae = Assertions.assertThrows(AtributeException.class,
                () -> new Person("Matheus", null)
        );
        Assertions.assertEquals("A idade não pode ser nula", ae.getMessage());
    }
}
