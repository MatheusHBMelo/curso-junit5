package dev.matheushbmelo.project.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Entidade: Usuário")
public class UsuarioTest {

    @Test
    @DisplayName("Deve criar um usuário válido")
    public void deveCriarUmUsuarioValido(){
        Usuario usuario = new Usuario(1L, "Matheus", "matheus@email.com", "12345");
        assertEquals(1L, usuario.getId());
        assertEquals("Matheus", usuario.getNome());
        assertEquals("matheus@email.com", usuario.getEmail());
        assertEquals("12345", usuario.getSenha());

        // Ou

        Assertions.assertAll("Usuario",
                () -> assertEquals(1L, usuario.getId()),
                () -> assertEquals("Matheus", usuario.getNome()),
                () -> assertEquals("matheus@email.com", usuario.getEmail()),
                () -> assertEquals("12345", usuario.getSenha())
        );
    }

    @Test
    @DisplayName("Deve rejeitar usuário com nome nulo")
    public void deveRejeitarUsuarioSemNome(){
        ValidationException ex = Assertions.assertThrows(ValidationException.class,
                () -> new Usuario(1L, null, "matheus@email.com", "12345")
        );
        assertEquals("O nome não pode ser nulo!", ex.getMessage());
    }
}
