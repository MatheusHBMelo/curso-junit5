package dev.matheushbmelo.project.domain.service;

import dev.matheushbmelo.project.domain.builder.ContaBuilder;
import dev.matheushbmelo.project.domain.builder.TransacaoBuilder;
import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import dev.matheushbmelo.project.domain.models.Conta;
import dev.matheushbmelo.project.domain.models.Transacao;
import dev.matheushbmelo.project.domain.service.reporitories.TransacaoRepository;
import dev.matheushbmelo.project.domain.utils.CurrentTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

//@EnabledIf(value = "checkTime")
@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
    @InjectMocks
    private TransacaoService service;
    @Mock
    private TransacaoRepository repository;
    @Mock
    private CurrentTime currentTime;

    @BeforeEach
    public void setup() {
        Mockito.when(currentTime.getCurrentTime()).thenReturn(LocalDateTime.of(2023, 11, 8, 4, 30, 28));
    }

    @Test
    public void deveSalvarTransacaoValida() {
        // Assumptions.assumeTrue(LocalDateTime.now().getHour() < 8);

        Transacao transacaoToSave = TransacaoBuilder.umaTransacao().comId(null).agora();
        Mockito.when(repository.salvar(transacaoToSave)).thenReturn(TransacaoBuilder.umaTransacao().agora());

        LocalDateTime dataDesejada = LocalDateTime.of(2023, 11, 8, 7, 30, 20);

        Transacao trasacaoSaved = service.salvar(transacaoToSave);
        Assertions.assertEquals(TransacaoBuilder.umaTransacao().agora(), trasacaoSaved);
        Assertions.assertAll("Transação",
                () -> Assertions.assertEquals(1L, trasacaoSaved.getId()),
                () -> Assertions.assertEquals("Transação válida", trasacaoSaved.getDescricao()),
                () -> {
                    Assertions.assertAll("Conta",
                            () -> Assertions.assertEquals("Conta válida", trasacaoSaved.getConta().getNome()),
                            () -> {
                                Assertions.assertAll("Usuario",
                                        () -> Assertions.assertEquals("Matheus", trasacaoSaved.getConta().getUsuario().getNome()),
                                        () -> Assertions.assertEquals("12345678", trasacaoSaved.getConta().getUsuario().getSenha())
                                );
                            }
                    );
                }
        );
        //Assertions.assertNotNull(trasacaoSaved.getId());
    }

    @ParameterizedTest(name = "{6}")
    @MethodSource(value = "camposObrigatorios")
    public void deveValidarCamposObrigatorios(Long id, String descricao, Double valor, Conta conta, LocalDate data, Boolean status, String message) {
        String exMensagem = Assertions.assertThrows(ValidationException.class, () -> {
            Transacao transacao = TransacaoBuilder.umaTransacao().comId(id).comDescricao(descricao).comValor(valor).comConta(conta).comData(data).comStatus(status).agora();
            service.salvar(transacao);
        }).getMessage();
        Assertions.assertEquals(message, exMensagem);
    }

    public static Stream<Arguments> camposObrigatorios() {
        return Stream.of(
                Arguments.of(1L, null, 10.0, ContaBuilder.umaConta().agora(), LocalDate.now(), true, "Descrição inexistente"),
                Arguments.of(1L, "Descrição", null, ContaBuilder.umaConta().agora(), LocalDate.now(), true, "Valor inexistente"),
                Arguments.of(1L, "Descrição", 10.0, null, LocalDate.now(), true, "Conta inexistente"),
                Arguments.of(1L, "Descrição", 10.0, ContaBuilder.umaConta().agora(), null, true, "Data inexistente")
        );
    }

//    public static boolean checkTime(){
//        return LocalDateTime.now().getHour() > 8;
//    }

    @Test
    void testForProductionEnvironment() {
        String env = System.getenv("env");
        Assumptions.assumeTrue("env".equals(env));
        // Este teste será executado apenas se a variável de ambiente "env" for definida como "prod".
        // Caso contrário, o teste será ignorado.
    }

    @Test
    void testForLinux() {
        Assumptions.assumeTrue(OS.LINUX.isCurrentOs());
        // Este teste será executado apenas em sistemas Linux.
        // Se não for Linux, o teste será ignorado.
    }

//    @Test
//    void testFor64BitSystem() {
//        Assumptions.assumeTrue(System.getProperty("os.arch").contains("64"));
//        // Este teste será executado apenas em sistemas de 64 bits.
//        // Caso contrário, o teste será ignorado.
//    }
}
