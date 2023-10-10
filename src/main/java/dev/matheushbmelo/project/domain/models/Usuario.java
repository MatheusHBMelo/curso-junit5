package dev.matheushbmelo.project.domain.models;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;

public class Usuario {
    private long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(long id, String nome, String email, String senha) {
        if (nome == null) throw new ValidationException("O nome não pode ser nulo!");
        if (email == null) throw new ValidationException("O email não pode ser nulo!");
        if (senha == null) throw new ValidationException("A senha não pode ser nula!");

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
