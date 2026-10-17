package br.unb.eleicoes.dominio.pesquisas;

import java.time.LocalDate;
import java.util.List;

public class TestePesquisa {

    public static void main(String[] args) {

        System.out.println("=== TESTE DA CLASSE PESQUISA ===");

        // 1. Criar uma pesquisa com dados válidos
        Pesquisa pesquisa = new Pesquisa(
                "P001",
                "Pesquisa demonstrativa",
                "Cargo fictício",
                TipoPesquisa.INTENCAO_DE_VOTO,
                SituacaoPesquisa.PLANEJADA
        );

        System.out.println("\n1. Dados iniciais:");
        System.out.println("Código: " + pesquisa.getCodigo());
        System.out.println("Nome: " + pesquisa.getNome());
        System.out.println("Cargo: " + pesquisa.getCargo());
        System.out.println("Tipo: " + pesquisa.getTipo());
        System.out.println("Situação: " + pesquisa.getSituacao());

        // 2. Testar datas
        pesquisa.setDataInicioColeta(LocalDate.of(2026, 9, 20));
        pesquisa.setDataFimColeta(LocalDate.of(2026, 9, 25));

        System.out.println("\n2. Período da coleta:");
        System.out.println("Início: " + pesquisa.getDataInicioColeta());
        System.out.println("Fim: " + pesquisa.getDataFimColeta());

        // 3. Testar abrangência
        pesquisa.setAbrangencia(List.of("Região fictícia A", "Região fictícia B"));

        System.out.println("\n3. Abrangência:");
        System.out.println(pesquisa.getAbrangencia());

        // 4. Testar quantidade prevista no plano amostral
        PlanoAmostral plano = new PlanoAmostral(
                "Amostragem fictícia",
                500);

        pesquisa.setPlanoAmostral(plano);

        System.out.println("\n4. Quantidade prevista:");
        System.out.println(
                pesquisa.getPlanoAmostral().getQuantidadePrevistaEntrevistados()
        );
        // 5. Testar validação de nome vazio
        System.out.println("\n5. Teste de nome vazio:");

        try {
            pesquisa.setNome(" ");
            System.out.println("ERRO: o nome vazio foi aceito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 6. Testar período inválido
        System.out.println("\n6. Teste de período inválido:");

        try {
            pesquisa.setDataFimColeta(LocalDate.of(2026, 9, 19));
            System.out.println("ERRO: o período inválido foi aceito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 7. Testar quantidade inválida
        System.out.println("\n7. Teste de quantidade inválida:");

        try {
            new PlanoAmostral("Amostragem fictícia", 0);
            System.out.println("ERRO: a quantidade zero foi aceita.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 8. Testar código vazio
        System.out.println("\n8. Teste de código vazio:");

        try {
            pesquisa.setCodigo(" ");
            System.out.println("ERRO: o código vazio foi aceito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 9. Testar situação nula
        System.out.println("\n9. Teste de situação nula:");

        try {
            pesquisa.setSituacao(null);
            System.out.println("ERRO: a situação nula foi aceita.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 10. Testar abrangência vazia
        System.out.println("\n10. Teste de abrangência vazia:");

        try {
            pesquisa.setAbrangencia(List.of());
            System.out.println("ERRO: a abrangência vazia foi aceita.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 11. Testar proteção da lista de abrangência
        System.out.println("\n11. Teste de encapsulamento:");

        try {
            pesquisa.getAbrangencia().add("Região fictícia C");
            System.out.println("ERRO: a lista retornada pôde ser modificada.");
        } catch (UnsupportedOperationException e) {
            System.out.println("Encapsulamento funcionando: a lista não pode ser modificada.");
        }


        // 12. Testar região nula dentro da lista
        System.out.println("\n12. Teste de região nula:");

        try {
            pesquisa.setAbrangencia(
                    java.util.Arrays.asList("Região fictícia A", null)
            );
            System.out.println("ERRO: a região nula foi aceita.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 13. Testar datas nulas
        System.out.println("\n13. Teste de datas nulas:");

        try {
            pesquisa.setDataInicioColeta(null);
            pesquisa.setDataFimColeta(null);

            System.out.println("As datas nulas foram aceitas.");
            System.out.println("Início: " + pesquisa.getDataInicioColeta());
            System.out.println("Fim: " + pesquisa.getDataFimColeta());

        } catch (IllegalArgumentException e) {
            System.out.println("Datas rejeitadas: " + e.getMessage());
        }

        // 14. Testar se a lista original pode alterar a pesquisa
        System.out.println("\n14. Teste de proteção da lista original:");

        java.util.List<String> regioes =
                new java.util.ArrayList<>();

        regioes.add("Região fictícia A");
        regioes.add("Região fictícia B");

        pesquisa.setAbrangencia(regioes);

        regioes.add("Região fictícia C");

        System.out.println("Lista original: " + regioes);
        System.out.println("Lista na pesquisa: "
                + pesquisa.getAbrangencia());

        if (pesquisa.getAbrangencia().size() == 2) {
            System.out.println(
                    "Encapsulamento funcionando: a lista original "
                            + "não alterou a pesquisa.");
        } else {
            System.out.println(
                    "ERRO: a lista original alterou a pesquisa.");
        }


        System.out.println("\n=== FIM DOS TESTES ===");
    }
}