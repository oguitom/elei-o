package br.unb.eleicoes.dominio.pesquisas;

public class OpcaoResposta {

    private String texto;

    public OpcaoResposta(String texto) {
        setTexto(texto);
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(
                    "O texto da opção de resposta não pode ser vazio."
            );
        }

        this.texto = texto.trim();
    }
}