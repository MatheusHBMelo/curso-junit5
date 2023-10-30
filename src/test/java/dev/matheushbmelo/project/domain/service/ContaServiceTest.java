package dev.matheushbmelo.project.domain.service;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import dev.matheushbmelo.project.domain.models.Conta;
import dev.matheushbmelo.project.domain.models.builders.ContaBuilder;
import dev.matheushbmelo.project.domain.service.reporitories.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
// @MockitoSettings(strictness = Strictness.LENIENT)
public class ContaServiceTest {
    @Mock private ContaRepository repository;
    @InjectMocks private ContaService service;

    @Test
    public void deveSalvarContaComSucesso(){
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Mockito.when(repository.salvar(contaToSave)).thenReturn(ContaBuilder.umaConta().agora());

        Conta contaSalved = service.salvar(contaToSave);

        Assertions.assertNotNull(contaSalved.getId());
    }

    @Test
    public void deveRejeitarContaRepetida(){
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Mockito.when(repository.obterContasPorUsuario(contaToSave.getUsuario().getId())).thenReturn(Arrays.asList(ContaBuilder.umaConta().agora()));
//        Mockito.when(repository.salvar(contaToSave)).thenReturn(ContaBuilder.umaConta().agora());

        String message = Assertions.assertThrows(ValidationException.class,
                () -> service.salvar(contaToSave)
        ).getMessage();
        Assertions.assertEquals("Usuário já possui uma conta com este nome", message);
    }

    @Test
    public void deveSalvarContaMesmoJaExistindoOutras(){
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Mockito.when(repository.obterContasPorUsuario(contaToSave.getUsuario().getId())).thenReturn(Arrays.asList(ContaBuilder.umaConta().comNome("Outra conta").agora()));
        Mockito.when(repository.salvar(contaToSave)).thenReturn(ContaBuilder.umaConta().agora());

        Conta contaSalved = service.salvar(contaToSave);

        Assertions.assertNotNull(contaSalved.getId());
    }
}
