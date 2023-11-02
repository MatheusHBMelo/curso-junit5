package dev.matheushbmelo.project.domain.service.reporitories;

import dev.matheushbmelo.project.domain.models.Transacao;

public interface TransacaoRepository {

    Transacao salvar(Transacao transacao);
}
