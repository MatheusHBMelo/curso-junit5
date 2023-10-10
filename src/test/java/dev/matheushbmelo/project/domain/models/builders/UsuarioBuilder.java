package dev.matheushbmelo.project.domain.models.builders;

import dev.matheushbmelo.project.domain.models.Usuario;

public class UsuarioBuilder {
    private long id;
    private String nome;
    private String email;
    private String senha;

    private UsuarioBuilder() {}

    public static UsuarioBuilder umUsuario(){
        UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
        inicializarUsuario(usuarioBuilder);
        return usuarioBuilder;
    }

    private static void inicializarUsuario(UsuarioBuilder usuarioBuilder) {
        usuarioBuilder.id = 1L;
        usuarioBuilder.nome = "Matheus";
        usuarioBuilder.email = "matheus@email.com";
        usuarioBuilder.senha = "12345678";
    }

    public UsuarioBuilder comId(Long id){
        this.id = id;
        return this;
    }

    public UsuarioBuilder comNome(String nome){
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder comEmail(String email){
        this.email = email;
        return this;
    }

    public UsuarioBuilder comSenha(String senha){
        this.senha = senha;
        return this;
    }

    public Usuario agora(){
        return new Usuario(id, nome, email, senha);
    }
}
