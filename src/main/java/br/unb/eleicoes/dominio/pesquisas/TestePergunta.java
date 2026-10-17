package br.unb.eleicoes.dominio.pesquisas;

public class TestePergunta {

    public static void main(String[] args) {

        System.out.println("=== TESTE DA CLASSE PERGUNTA ===");

        // 1. Criar uma pergunta
        Pergunta pergunta = new Pergunta(
                "Em qual candidato você votaria?",
                TipoPergunta.UNICA_ESCOLHA
        );

        System.out.println("\n1. Dados iniciais:");
        System.out.println("Texto: " + pergunta.getTexto());
        System.out.println("Tipo: " + pergunta.getTipo());

        // 2. Adicionar opções de resposta
        OpcaoResposta opcao1 = new OpcaoResposta("Candidato fictício A");
        OpcaoResposta opcao2 = new OpcaoResposta("Candidato fictício B");

        pergunta.adicionarOpcaoResposta(opcao1);
        pergunta.adicionarOpcaoResposta(opcao2);

        System.out.println("\n2. Opções de resposta:");
        for (OpcaoResposta opcao : pergunta.getOpcoesResposta()) {
            System.out.println(opcao.getTexto());
        }

        // 3. Testar texto vazio
        System.out.println("\n3. Teste de texto vazio:");

        try {
            pergunta.setTexto("   ");
            System.out.println("ERRO: o texto vazio foi aceito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 4. Testar opção nula
        System.out.println("\n4. Teste de opção nula:");

        try {
            pergunta.adicionarOpcaoResposta(null);
            System.out.println("ERRO: a opção nula foi aceita.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 5. Testar proteção da lista
        System.out.println("\n5. Teste de encapsulamento:");

        try {
            pergunta.getOpcoesResposta().clear();
            System.out.println("ERRO: a lista pôde ser modificada.");
        } catch (UnsupportedOperationException e) {
            System.out.println("Encapsulamento funcionando.");
        }

        // 6. Testar remoção de opção
        System.out.println("\n6. Teste de remoção:");

        pergunta.removerOpcaoResposta(opcao1);

        System.out.println(
                "Quantidade de opções após remoção: "
                        + pergunta.getOpcoesResposta().size()
        );

        System.out.println("\n=== FIM DOS TESTES ===");
    }
}