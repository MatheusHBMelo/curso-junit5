package dev.matheushbmelo.project.domain.service;

import dev.matheushbmelo.project.domain.events.ContaEvent;
import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import dev.matheushbmelo.project.domain.models.Conta;
import dev.matheushbmelo.project.domain.service.reporitories.ContaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ContaService {
    private ContaRepository repository;
    private ContaEvent event;

    public ContaService(ContaRepository contaRepository, ContaEvent event){
        this.repository = contaRepository;
        this.event = event;
    }

    public Conta salvar(Conta conta){
        List<Conta> contas = repository.obterContasPorUsuario(conta.getUsuario().getId());
        contas.stream().forEach(contaExistente -> {
            if (conta.getNome().equalsIgnoreCase(contaExistente.getNome())){
                throw new ValidationException("Usuário já possui uma conta com este nome");
            }
        });
        Conta contaSaved = repository.salvar(new Conta(conta.getId(), conta.getNome() + LocalDateTime.now(), conta.getUsuario()));
        try {
            event.dispatch(contaSaved, ContaEvent.EventType.CREATED);
        } catch (Exception e){
            repository.deletar(contaSaved);
            throw new RuntimeException("Falha na criação da conta, tente novamente.");
        }
        return contaSaved;
    }
}
