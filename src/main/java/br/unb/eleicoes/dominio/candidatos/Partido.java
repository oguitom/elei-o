package br.unb.eleicoes.dominio.candidatos;

public class Partido {

    private String codigo;
    private String sigla;
    private String nome;

    public Partido(String codigo, String sigla, String nome) {
        setCodigo(codigo);
        setSigla(sigla);
        setNome(nome);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = validarTexto(codigo, "Código");
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = validarTexto(sigla, "Sigla");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = validarTexto(nome, "Nome");
    }

    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    campo + " do partido não pode ser vazio."
            );
        }

        return valor.trim();
    }
}