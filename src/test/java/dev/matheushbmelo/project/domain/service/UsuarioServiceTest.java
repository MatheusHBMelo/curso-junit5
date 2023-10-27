package dev.matheushbmelo.project.domain.service;

import dev.matheushbmelo.project.domain.builder.UsuarioBuilder;
import dev.matheushbmelo.project.domain.infra.UsuarioDummyRepository;
import dev.matheushbmelo.project.domain.models.Usuario;
import dev.matheushbmelo.project.domain.service.reporitories.UsuarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UsuarioServiceTest {
    private UsuarioService service;
    private UsuarioRepository repository;

    @BeforeEach
    public void setup(){
        repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService(repository);
    }

    @AfterEach
    public void tearDown(){
        Mockito.verifyNoMoreInteractions(repository);
    }

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
        Mockito.when(repository.getUsuarioByEmail("matheus@email.com")).thenReturn(Optional.empty());

        Optional<Usuario> user = repository.getUsuarioByEmail("matheus@email.com");
        Assertions.assertTrue(user.isEmpty());
        Mockito.verify(repository).getUsuarioByEmail("matheus@email.com");
    }

    @Test
    public void deveRetornarUsuarioPorEmail(){
        // Sem repetição em mock
        Mockito.when(repository.getUsuarioByEmail("matheus@email.com")).thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));

        // Com repetição em mock
        Mockito.when(repository.getUsuarioByEmail("matheus@email.com")).thenReturn(
                Optional.of(UsuarioBuilder.umUsuario().agora()),
                Optional.of(UsuarioBuilder.umUsuario().agora()),
                Optional.of(UsuarioBuilder.umUsuario().agora())
        );

        Optional<Usuario> user = repository.getUsuarioByEmail("matheus@email.com");
        Assertions.assertTrue(user.isPresent());

        Mockito.verify(repository, Mockito.times(1)).getUsuarioByEmail("matheus@email.com");
        Mockito.verify(repository, Mockito.atLeast(1)).getUsuarioByEmail("matheus@email.com");
        Mockito.verify(repository, Mockito.atLeastOnce()).getUsuarioByEmail("matheus@email.com");
        Mockito.verify(repository, Mockito.never()).getUsuarioByEmail("matttheus@email.com");
    }

    @Test
    public void deveSalvarUsuarioValido(){
        Usuario novoUsuario = UsuarioBuilder.umUsuario().comId(null).agora();

        Mockito.when(repository.getUsuarioByEmail(novoUsuario.getEmail())).thenReturn(Optional.empty());
        Mockito.when(repository.salvar(novoUsuario)).thenReturn(UsuarioBuilder.umUsuario().agora());

        Usuario usuarioSalvo = service.salvar(novoUsuario);
        Assertions.assertNotNull(usuarioSalvo.getId());

        Mockito.verify(repository).getUsuarioByEmail(novoUsuario.getEmail());
        Mockito.verify(repository).salvar(novoUsuario);
    }
}
