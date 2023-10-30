package dev.matheushbmelo.project.domain.service;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import dev.matheushbmelo.project.domain.models.Conta;
import dev.matheushbmelo.project.domain.service.reporitories.ContaRepository;

import java.util.List;

public class ContaService {
    private ContaRepository repository;

    public ContaService(ContaRepository contaRepository){
        this.repository = contaRepository;
    }

    public Conta salvar(Conta conta){
        List<Conta> contas = repository.obterContasPorUsuario(conta.getUsuario().getId());
        contas.stream().forEach(contaExistente -> {
            if (conta.getNome().equalsIgnoreCase(contaExistente.getNome())){
                throw new ValidationException("Usuário já possui uma conta com este nome");
            }
        });
        return repository.salvar(conta);
    }
}
