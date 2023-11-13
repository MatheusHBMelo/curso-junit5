package dev.matheushbmelo.project.domain.models;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static dev.matheushbmelo.project.domain.models.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes de entidade: Usuário")
public class UsuarioTest {

    @Test
    @DisplayName("Deve criar um usuário válido")
    public void deveCriarUmUsuarioValido() {
        Usuario usuario = umUsuario().agora();
        assertEquals(1L, usuario.getId());
        assertEquals("Matheus", usuario.getNome());
        assertEquals("matheus@email.com", usuario.getEmail());
        assertEquals("12345678", usuario.getSenha());

        // Ou

        Assertions.assertAll("Usuario",
                () -> assertEquals(1L, usuario.getId()),
                () -> assertEquals("Matheus", usuario.getNome()),
                () -> assertEquals("matheus@email.com", usuario.getEmail()),
                () -> assertEquals("12345678", usuario.getSenha())
        );
    }

    @Test
    @DisplayName("Deve rejeitar usuário com nome nulo")
    public void deveRejeitarUsuarioSemNome() {
        ValidationException ex = Assertions.assertThrows(ValidationException.class,
                () -> umUsuario().comNome(null).agora()
        );
        assertEquals("O nome não pode ser nulo!", ex.getMessage());
    }

    @Test
    @DisplayName("Deve rejeitar usuario com email nulo")
    public void deveRejeitarUsuarioComEmailNulo() {
        ValidationException ex = assertThrows(ValidationException.class,
                () -> umUsuario().comEmail(null).agora()
        );
        assertEquals("O email não pode ser nulo!", ex.getMessage());
    }

    @Test
    @DisplayName("Deve rejeitar usuario com senha nula")
    public void deveRejeitarUsuarioComSenhaNula() {
        ValidationException ex = assertThrows(ValidationException.class,
                () -> umUsuario().comSenha(null).agora()
        );
        assertEquals("A senha não pode ser nula!", ex.getMessage());
    }

    // Os três testes anteriores com parameterized test
    @ParameterizedTest(name = "[{0}] - {4}")
    @CsvSource(value = {
            "1, NULL, user@email.com, 12345, O nome não pode ser nulo!",
            "2, usuario, NULL, 12345, O email não pode ser nulo!",
            "3, usuario, user@email.com, NULL, A senha não pode ser nula!"
    }, nullValues = "NULL")
    public void verificarCamposObrigatoriosDoUsuario(Long id, String nome, String email, String senha, String mensagem) {
        ValidationException ex = Assertions.assertThrows(ValidationException.class,
                () -> umUsuario().comId(id).comNome(nome).comEmail(email).comSenha(senha).agora()
        );
        assertEquals(mensagem, ex.getMessage());
    }

    // O parameterized test com dados externos
    @ParameterizedTest(name = "[{0}] - {4}")
    @CsvFileSource(files = "src\\camposObrigatoriosUsuario.csv", nullValues = "NULL", numLinesToSkip = 1)
    public void verificarCamposObrigatorioDoUsuarioComArquivoExterno(Long id, String nome, String email, String senha, String mensagem) {
        ValidationException ex = Assertions.assertThrows(ValidationException.class,
                () -> umUsuario().comId(id).comNome(nome).comEmail(email).comSenha(senha).agora()
        );
        assertEquals(mensagem, ex.getMessage());
    }
}
