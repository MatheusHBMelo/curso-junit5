package dev.matheushbmelo.project.domain.builder;

import java.time.LocalDate;
import dev.matheushbmelo.project.domain.models.Conta;
import dev.matheushbmelo.project.domain.models.Transacao;

public class TransacaoBuilder {
    private Transacao elemento;

    private TransacaoBuilder(){}

    public static TransacaoBuilder umaTransacao() {
        TransacaoBuilder builder = new TransacaoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    private static void inicializarDadosPadroes(TransacaoBuilder builder) {
        builder.elemento = new Transacao();
        Transacao elemento = builder.elemento;

        elemento.setId(1L);
        elemento.setDescricao("Transação válida");
        elemento.setValor(10.0);
        elemento.setConta(ContaBuilder.umaConta().agora());
        elemento.setData(LocalDate.now());
        elemento.setStatus(false);
    }

    public TransacaoBuilder comId(Long id) {
        elemento.setId(id);
        return this;
    }

    public TransacaoBuilder comDescricao(String descricao) {
        elemento.setDescricao(descricao);
        return this;
    }

    public TransacaoBuilder comValor(Double valor) {
        elemento.setValor(valor);
        return this;
    }

    public TransacaoBuilder comConta(Conta conta) {
        elemento.setConta(conta);
        return this;
    }

    public TransacaoBuilder comData(LocalDate data) {
        elemento.setData(data);
        return this;
    }

    public TransacaoBuilder comStatus(Boolean status) {
        elemento.setStatus(status);
        return this;
    }

    public Transacao agora() {
        return elemento;
    }
}
