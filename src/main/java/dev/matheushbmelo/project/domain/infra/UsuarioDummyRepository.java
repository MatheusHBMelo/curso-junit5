package dev.matheushbmelo.project.domain.infra;

import dev.matheushbmelo.project.domain.builder.UsuarioBuilder;
import dev.matheushbmelo.project.domain.models.Usuario;
import dev.matheushbmelo.project.domain.service.reporitories.UsuarioRepository;

import java.util.Optional;

public class UsuarioDummyRepository implements UsuarioRepository {
    @Override
    public Usuario salvar(Usuario usuario) {
        return UsuarioBuilder.umUsuario()
                .comNome(usuario.getNome())
                .comEmail(usuario.getEmail())
                .comSenha(usuario.getSenha())
                .agora();
    }

    @Override
    public Optional<Usuario> getUsuarioByEmail(String email) {
        if ("matheus@email.com".equals(email)) {
            return Optional.of(UsuarioBuilder.umUsuario().comEmail(email).agora());
        }
        return Optional.empty();
    }
}
