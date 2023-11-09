package dev.matheushbmelo.project.domain.service;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import dev.matheushbmelo.project.domain.models.Transacao;
import dev.matheushbmelo.project.domain.service.reporitories.TransacaoRepository;
import dev.matheushbmelo.project.domain.utils.CurrentTime;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransacaoService {
    private TransacaoRepository repository;
    private CurrentTime currentTime;

    public Transacao salvar(Transacao transacao){
        if (currentTime.getCurrentTime().getHour() > 8){
            throw new RuntimeException("Tente novamente amanhã");
        }

        validation(transacao);
        if(transacao.getStatus() == null) transacao.setStatus(false);

        return repository.salvar(transacao);
    }

    private static void validation(Transacao transacao) {
        if(transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if(transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if(transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        if(transacao.getData() == null) throw new ValidationException("Data inexistente");
    }
}
