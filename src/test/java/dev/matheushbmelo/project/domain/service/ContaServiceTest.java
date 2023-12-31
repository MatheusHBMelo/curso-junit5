package dev.matheushbmelo.project.domain.service;

import dev.matheushbmelo.project.domain.events.ContaEvent;
import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import dev.matheushbmelo.project.domain.models.Conta;
import dev.matheushbmelo.project.domain.models.builders.ContaBuilder;
import dev.matheushbmelo.project.domain.service.reporitories.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
// @MockitoSettings(strictness = Strictness.LENIENT)
public class ContaServiceTest {
    @Mock
    private ContaRepository repository;
    @InjectMocks
    private ContaService service;
    @Mock
    private ContaEvent event;
    @Captor
    private ArgumentCaptor<Conta> contaCaptor;

    @Test
    public void deveSalvarContaComSucesso() throws Exception {
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Mockito.when(repository.salvar(Mockito.any(Conta.class))).thenReturn(ContaBuilder.umaConta().agora());
        Mockito.doNothing().when(event).dispatch(ContaBuilder.umaConta().agora(), ContaEvent.EventType.CREATED);

        Conta contaSalved = service.salvar(contaToSave);

        Assertions.assertNotNull(contaSalved.getId());

        Mockito.verify(repository).salvar(contaCaptor.capture());
        Assertions.assertTrue(contaCaptor.getValue().getNome().startsWith("Conta válida"));
    }

    @Test
    public void deveRejeitarContaRepetida() {
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Mockito.when(repository.obterContasPorUsuario(contaToSave.getUsuario().getId())).thenReturn(Arrays.asList(ContaBuilder.umaConta().agora()));
//        Mockito.when(repository.salvar(contaToSave)).thenReturn(ContaBuilder.umaConta().agora());

        String message = Assertions.assertThrows(ValidationException.class,
                () -> service.salvar(contaToSave)
        ).getMessage();
        Assertions.assertEquals("Usuário já possui uma conta com este nome", message);
    }

    @Test
    public void deveSalvarContaMesmoJaExistindoOutras() throws Exception {
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Mockito.when(repository.obterContasPorUsuario(contaToSave.getUsuario().getId())).thenReturn(Arrays.asList(ContaBuilder.umaConta().comNome("Outra conta").agora()));
        Mockito.when(repository.salvar(Mockito.any(Conta.class))).thenReturn(ContaBuilder.umaConta().agora());

        Conta contaSalved = service.salvar(contaToSave);

        Assertions.assertNotNull(contaSalved.getId());
    }

    @Test
    public void naoDeveManterContaSemEvento() throws Exception {
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Conta contaSalva = ContaBuilder.umaConta().agora();
        Mockito.when(repository.salvar(Mockito.any(Conta.class))).thenReturn(contaSalva);
        Mockito.doThrow(new Exception("Falha catastrofica")).when(event).dispatch(contaSalva, ContaEvent.EventType.CREATED);

        String message = Assertions.assertThrows(Exception.class,
                () -> service.salvar(contaToSave)
        ).getMessage();
        Assertions.assertEquals("Falha na criação da conta, tente novamente.", message);

        Mockito.verify(repository).deletar(contaSalva);
    }
}
