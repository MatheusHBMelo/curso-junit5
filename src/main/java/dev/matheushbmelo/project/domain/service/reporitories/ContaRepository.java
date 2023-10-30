package dev.matheushbmelo.project.domain.service.reporitories;

import dev.matheushbmelo.project.domain.models.Conta;

import java.util.List;

public interface ContaRepository {
    Conta salvar(Conta conta);

    List<Conta> obterContasPorUsuario(Long id);
}
