package dev.matheushbmelo.project.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import static dev.matheushbmelo.project.domain.models.builders.UsuarioBuilder.umUsuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Entidade: Usuário")
public class UsuarioTest {

    @Test
    @DisplayName("Deve criar um usuário válido")
    public void deveCriarUmUsuarioValido(){
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
    public void deveRejeitarUsuarioSemNome(){
        ValidationException ex = Assertions.assertThrows(ValidationException.class,
                () -> umUsuario().comNome(null).agora()
        );
        assertEquals("O nome não pode ser nulo!", ex.getMessage());
    }
}
