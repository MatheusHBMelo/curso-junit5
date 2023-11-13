package dev.matheushbmelo.project.domain.models;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;

import java.util.Objects;

public class Conta {
    private Long id;
    private String nome;
    private Usuario usuario;

    public Conta(Long id, String nome, Usuario usuario) {
        if (nome == null) {
            throw new ValidationException("O nome não pode ser nulo!");
        }
        if (usuario == null) {
            throw new ValidationException("O usuario não pode ser nulo!");
        }

        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(id, conta.id) && Objects.equals(nome, conta.nome) && Objects.equals(usuario, conta.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, usuario);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
