package dev.matheushbmelo.classes;

import dev.matheushbmelo.classes.SaudacaoDiaria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaudacaoDiariaTest {
    @Spy
    private SaudacaoDiaria saudacao;

    @Test
    public void deveRetornarMensagemDeBomDia() {
        Mockito.when(saudacao.saudacao(6)).thenReturn("Bom dia");
        String mensagem = saudacao.saudacao(6);
        Assertions.assertEquals("Bom dia", mensagem);
        Mockito.verify(saudacao).saudacao(6);
    }

    @Test
    public void deveRetornarMensagemDeBoaTarde() {
        Mockito.when(saudacao.saudacao(14)).thenReturn("Boa tarde");
        String mensagem = saudacao.saudacao(14);
        Assertions.assertEquals("Boa tarde", mensagem);
        Mockito.verify(saudacao).saudacao(14);
    }

    @Test
    public void deveRetornarMensagemDeBoaNoite() {
        Mockito.when(saudacao.saudacao(20)).thenReturn("Boa noite");
        String mensagem = saudacao.saudacao(20);
        Assertions.assertEquals("Boa noite", mensagem);
        Mockito.verify(saudacao).saudacao(20);
    }

    @Test
    public void deveRetornarExceptionDeHoraInvalida() {
        String message = Assertions.assertThrows(RuntimeException.class,
                () -> saudacao.saudacao(-5)
        ).getMessage();
        Assertions.assertEquals("Horário invalido", message);
    }
}
