package br.unb.eleicoes.dominio.pesquisas;

import java.util.ArrayList;
import java.util.List;

public class Pergunta {

    private String texto;
    private TipoPergunta tipo;
    private List<OpcaoResposta> opcoesResposta;

    public Pergunta(String texto, TipoPergunta tipo) {
        setTexto(texto);
        setTipo(tipo);
        this.opcoesResposta = new ArrayList<>();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(
                    "O texto da pergunta não pode ser vazio."
            );
        }

        this.texto = texto.trim();
    }

    public TipoPergunta getTipo() {
        return tipo;
    }

    public void setTipo(TipoPergunta tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException(
                    "O tipo da pergunta deve ser informado."
            );
        }

        this.tipo = tipo;
    }

    public List<OpcaoResposta> getOpcoesResposta() {
        return List.copyOf(opcoesResposta);
    }

    public void adicionarOpcaoResposta(OpcaoResposta opcao) {
        if (opcao == null) {
            throw new IllegalArgumentException(
                    "A opção de resposta não pode ser nula."
            );
        }

        opcoesResposta.add(opcao);
    }

    public void removerOpcaoResposta(OpcaoResposta opcao) {
        opcoesResposta.remove(opcao);
    }
}