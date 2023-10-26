package dev.matheushbmelo.project.domain.service;

import dev.matheushbmelo.project.domain.builder.UsuarioBuilder;
import dev.matheushbmelo.project.domain.infra.UsuarioDummyRepository;
import dev.matheushbmelo.project.domain.models.Usuario;
import dev.matheushbmelo.project.domain.service.reporitories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UsuarioServiceTest {
    private UsuarioService service;

    @Test
    public void deveSalvarUsuarioComSucesso(){
        service = new UsuarioService(new UsuarioDummyRepository());
        Usuario user = UsuarioBuilder.umUsuario().comId(null).comEmail("outro@email.com").agora();
        Usuario saveUser = service.salvar(user);
        Assertions.assertNotNull(saveUser.getId());
    }

    // Usando o mockito
    @Test
    public void deveRetornarEmptyQuandoUsuarioInexistir(){
        UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService(repository);

        Mockito.when(repository.getUsuarioByEmail("matheus@email.com")).thenReturn(Optional.empty());

        Optional<Usuario> user = repository.getUsuarioByEmail("matheus@email.com");
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    public void deveRetornarUsuarioPorEmail(){
        UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService(repository);

        Mockito.when(repository.getUsuarioByEmail("matheus@email.com")).thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));

        Optional<Usuario> user = repository.getUsuarioByEmail("matheus@email.com");
        Assertions.assertFalse(user.isEmpty());
    }
}
