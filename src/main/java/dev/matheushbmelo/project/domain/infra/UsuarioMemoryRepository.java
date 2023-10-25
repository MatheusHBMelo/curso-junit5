package dev.matheushbmelo.project.domain.infra;

import dev.matheushbmelo.project.domain.models.Usuario;
import dev.matheushbmelo.project.domain.service.reporitories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioMemoryRepository implements UsuarioRepository {
    private List<Usuario> users;
    private Long currentId;

    public UsuarioMemoryRepository(){
        currentId = 0L;
        users = new ArrayList<>();
        salvar(new Usuario(null, "User #1", "user1@mail.com", "123456"));
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        Usuario newUser = new Usuario(nextId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
        users.add(newUser);
        return newUser;
    }

    @Override
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    private Long nextId(){
        return ++currentId;
    }

    public void printUsers(){
        System.out.println(users);
    }

    public static void main(String[] args) {
        UsuarioMemoryRepository repo = new UsuarioMemoryRepository();
        repo.printUsers();
        repo.salvar(new Usuario(null, "Usuário qualquer", "a@a.com", "aaa"));
        repo.printUsers();
    }
}
