package br.unb.eleicoes.dominio.pesquisas;

import java.util.List;

public class TesteIntegracaoPesquisa {

    public static void main(String[] args) {

        System.out.println("=== TESTE DE INTEGRACAO ===");

        // 1. Criar a pesquisa
        Pesquisa pesquisa = new Pesquisa(
                "P002",
                "Pesquisa demonstrativa",
                "Presidente",
                TipoPesquisa.INTENCAO_DE_VOTO,
                SituacaoPesquisa.PLANEJADA
        );

        // 2. Associar o plano amostral
        PlanoAmostral plano = new PlanoAmostral(
                "Amostragem fictícia",
                500
        );

        pesquisa.setPlanoAmostral(plano);

        System.out.println("\n1. Plano amostral:");
        System.out.println(
                "Metodologia: "
                        + pesquisa.getPlanoAmostral().getMetodologia()
        );
        System.out.println(
                "Quantidade prevista: "
                        + pesquisa.getPlanoAmostral()
                        .getQuantidadePrevistaEntrevistados()
        );

        // 3. Criar uma pergunta com opções
        Pergunta pergunta = new Pergunta(
                "Em qual candidato você votaria?",
                TipoPergunta.UNICA_ESCOLHA
        );

        pergunta.adicionarOpcaoResposta(
                new OpcaoResposta("Candidato fictício A")
        );

        pergunta.adicionarOpcaoResposta(
                new OpcaoResposta("Candidato fictício B")
        );

        // 4. Associar a pergunta à pesquisa
        pesquisa.adicionarPergunta(pergunta);

        System.out.println("\n2. Perguntas da pesquisa:");

        for (Pergunta p : pesquisa.getPerguntas()) {
            System.out.println("Pergunta: " + p.getTexto());

            for (OpcaoResposta opcao : p.getOpcoesResposta()) {
                System.out.println("- " + opcao.getTexto());
            }
        }

        // 5. Verificar a quantidade de perguntas
        System.out.println("\n3. Total de perguntas: "
                + pesquisa.getPerguntas().size());

        System.out.println("\n=== FIM DO TESTE ===");
    }
}