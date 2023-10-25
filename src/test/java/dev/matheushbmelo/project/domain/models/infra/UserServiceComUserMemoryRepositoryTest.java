package dev.matheushbmelo.project.domain.models.infra;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import dev.matheushbmelo.project.domain.infra.UsuarioMemoryRepository;
import dev.matheushbmelo.project.domain.models.Usuario;
import dev.matheushbmelo.project.domain.models.builders.UsuarioBuilder;
import dev.matheushbmelo.project.domain.service.UsuarioService;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceComUserMemoryRepositoryTest {
    private static UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());

    @Test
    @Order(1)
    public void deveSalvarUsuarioValido(){
        Usuario user = service.salvar(UsuarioBuilder.umUsuario().comId(null).agora());
        Assertions.assertNotNull(user.getId());
    }

    @Test
    @Order(2)
    public void deveRejeitarUsuarioExistente(){
        ValidationException ex = Assertions.assertThrows(ValidationException.class,
                () -> service.salvar(UsuarioBuilder.umUsuario().comId(null).agora())
        );
        Assertions.assertEquals("O usuário matheus@email.com já está cadastrado", ex.getMessage());
    }
}
