package dev.matheushbmelo.project.domain.models;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import dev.matheushbmelo.project.domain.models.builders.UsuarioBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.matheushbmelo.project.domain.models.builders.ContaBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ContaTest {

    @Test
    public void deveCriarUmaContaValida() {
        Conta conta = ContaBuilder.umaConta().agora();
        System.out.println(conta.getUsuario().getNome());
        System.out.println(UsuarioBuilder.umUsuario().agora().getNome());
        Assertions.assertAll("conta",
                () -> Assertions.assertEquals(1L, conta.getId()),
                () -> Assertions.assertEquals("Conta válida", conta.getNome()),
                () -> Assertions.assertEquals(UsuarioBuilder.umUsuario().agora(), conta.getUsuario())
        );
    }

    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    public void deveValidarCamposObrigatoriosDaConta(long id, String nome, Usuario usuario, String mensagem) {
        String error = Assertions.assertThrows(ValidationException.class,
                () -> ContaBuilder.umaConta().comId(id).comNome(nome).comUsuario(usuario).agora()
        ).getMessage();
        Assertions.assertEquals(mensagem, error);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, UsuarioBuilder.umUsuario().agora(), "O nome não pode ser nulo!"),
                Arguments.of(1L, "Conta válida", null, "O usuario não pode ser nulo!")
        );
    }
}
