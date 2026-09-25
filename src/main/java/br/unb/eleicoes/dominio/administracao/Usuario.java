package br.unb.eleicoes.dominio.administracao;

public class Usuario {
    private String nome;
    private String email;
    private String perfil;
    private boolean ativo;

    public Usuario(String nome, String email, String perfil, boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
        this.ativo = ativo;
    }
}
