package dev.matheushbmelo.project.domain.service;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;
import dev.matheushbmelo.project.domain.models.Usuario;
import dev.matheushbmelo.project.domain.service.reporitories.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        repository.getUsuarioByEmail(usuario.getEmail()).ifPresent(usuario1 -> {
            throw new ValidationException(String.format("O usuário %s já está cadastrado", usuario.getEmail()));
        });
        return repository.salvar(usuario);
    }
}
