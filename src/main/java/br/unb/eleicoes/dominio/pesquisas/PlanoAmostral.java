package br.unb.eleicoes.dominio.pesquisas;

public class PlanoAmostral {

    private String metodologia;
    private int quantidadePrevistaEntrevistados;

    public PlanoAmostral(
            String metodologia,
            int quantidadePrevistaEntrevistados) {

        setMetodologia(metodologia);
        setQuantidadePrevistaEntrevistados(
                quantidadePrevistaEntrevistados
        );
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        if (metodologia == null || metodologia.isBlank()) {
            throw new IllegalArgumentException(
                    "A metodologia do plano amostral não pode ser vazia."
            );
        }

        this.metodologia = metodologia.trim();
    }

    public int getQuantidadePrevistaEntrevistados() {
        return quantidadePrevistaEntrevistados;
    }

    public void setQuantidadePrevistaEntrevistados(
            int quantidadePrevistaEntrevistados) {

        if (quantidadePrevistaEntrevistados <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade prevista de entrevistados deve ser positiva."
            );
        }

        this.quantidadePrevistaEntrevistados =
                quantidadePrevistaEntrevistados;
    }
}