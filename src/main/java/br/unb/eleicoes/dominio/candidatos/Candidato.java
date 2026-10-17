package br.unb.eleicoes.dominio.candidatos;

public class Candidato {

    private String codigo;
    private String nome;
    private String numero;
    private String cargo;
    private Partido partido;

    public Candidato(
            String codigo,
            String nome,
            String numero,
            String cargo,
            Partido partido) {
        setCodigo(codigo);
        setNome(nome);
        setNumero(numero);
        setCargo(cargo);
        setPartido(partido);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = validarTexto(codigo, "Código");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = validarTexto(nome, "Nome");
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = validarTexto(numero, "Número eleitoral");
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = validarTexto(cargo, "Cargo");
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        if (partido == null) {
            throw new IllegalArgumentException(
                    "O partido do candidato não pode ser nulo."
            );
        }
        this.partido = partido;
    }

    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    campo + " não pode ser vazio."
            );
        }
        return valor.trim();
    }
}