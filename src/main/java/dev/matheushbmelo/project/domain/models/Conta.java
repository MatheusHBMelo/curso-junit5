package dev.matheushbmelo.project.domain.models;

import dev.matheushbmelo.project.domain.exceptions.ValidationException;

import java.util.Objects;

public class Conta {
    private Long id;
    private String nome;
    private Usuario usuario;

    public Conta(Long id, String nome, Usuario usuario) {
        if (nome == null){
            throw new ValidationException("O nome não pode ser nulo!");
        }
        if (usuario == null){
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
}
