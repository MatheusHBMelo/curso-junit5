package dev.matheushbmelo.project.domain.service.reporitories;

import dev.matheushbmelo.project.domain.models.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Usuario salvar(Usuario usuario);

    Optional<Usuario> getUsuarioByEmail(String email);
}
