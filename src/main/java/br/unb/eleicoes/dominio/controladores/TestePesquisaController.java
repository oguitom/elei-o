package br.unb.eleicoes.controladores;

import br.unb.eleicoes.dominio.pesquisas.Pesquisa;
import br.unb.eleicoes.dominio.pesquisas.TipoPesquisa;
import br.unb.eleicoes.dominio.pesquisas.SituacaoPesquisa;

public class TestePesquisaController {

    public static void main(String[] args) {

        System.out.println("=== TESTE DO PESQUISACONTROLLER ===");

        PesquisaController controller = new PesquisaController();

        // 1. Cadastrar uma pesquisa
        Pesquisa pesquisa = new Pesquisa(
                "P001",
                "Pesquisa demonstrativa",
                "Presidente",
                TipoPesquisa.INTENCAO_DE_VOTO,
                SituacaoPesquisa.PLANEJADA
        );

        controller.cadastrarPesquisa(pesquisa);

        System.out.println("\n1. Cadastro:");
        System.out.println("Pesquisa cadastrada: "
                + controller.buscarPorCodigo("P001").getNome());

        // 2. Listar pesquisas
        System.out.println("\n2. Listagem:");
        System.out.println("Total de pesquisas: "
                + controller.listarPesquisas().size());

        // 3. Buscar código inexistente
        System.out.println("\n3. Busca inexistente:");

        Pesquisa resultado = controller.buscarPorCodigo("P999");

        if (resultado == null) {
            System.out.println("Pesquisa não encontrada.");
        } else {
            System.out.println(resultado.getNome());
        }

        // 4. Testar código duplicado
        System.out.println("\n4. Teste de código duplicado:");

        try {
            Pesquisa duplicada = new Pesquisa(
                    "P001",
                    "Outra pesquisa",
                    "Governador",
                    TipoPesquisa.OUTRO,
                    SituacaoPesquisa.PLANEJADA
            );

            controller.cadastrarPesquisa(duplicada);

            System.out.println("ERRO: o código duplicado foi aceito.");

        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 5. Testar cadastro nulo
        System.out.println("\n5. Teste de pesquisa nula:");

        try {
            controller.cadastrarPesquisa(null);
            System.out.println("ERRO: a pesquisa nula foi aceita.");

        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 6. Testar atualização de pesquisa
        System.out.println("\n6. Teste de atualização:");

        Pesquisa atualizada = new Pesquisa(
                "P001",
                "Pesquisa atualizada",
                "Presidente",
                TipoPesquisa.INTENCAO_DE_VOTO,
                SituacaoPesquisa.EM_COLETA
        );

        controller.atualizarPesquisa(atualizada);

        System.out.println(
                "Nome após atualização: "
                        + controller.buscarPorCodigo("P001").getNome()
        );

        System.out.println(
                "Situação após atualização: "
                        + controller.buscarPorCodigo("P001").getSituacao()
        );

        // 7. Testar remoção de pesquisa
        System.out.println("\n7. Teste de remoção:");

        boolean removida = controller.removerPesquisa("P001");

        System.out.println("Pesquisa removida: " + removida);
        System.out.println(
                "Total após remoção: "
                        + controller.listarPesquisas().size()
        );

        // 8. Testar remoção de código inexistente
        System.out.println("\n8. Teste de remoção inexistente:");

        boolean inexistente = controller.removerPesquisa("P999");

        System.out.println("Pesquisa removida: " + inexistente);


        // 9. Cadastrar pesquisas para testar os filtros
        System.out.println("\n9. Preparação dos filtros:");

        Pesquisa p1 = new Pesquisa(
                "P101",
                "Pesquisa de intenção A",
                "Presidente",
                TipoPesquisa.INTENCAO_DE_VOTO,
                SituacaoPesquisa.EM_COLETA
        );

        Pesquisa p2 = new Pesquisa(
                "P102",
                "Pesquisa de rejeição",
                "Presidente",
                TipoPesquisa.REJEICAO,
                SituacaoPesquisa.PLANEJADA
        );

        Pesquisa p3 = new Pesquisa(
                "P103",
                "Pesquisa de intenção B",
                "Governador",
                TipoPesquisa.INTENCAO_DE_VOTO,
                SituacaoPesquisa.PLANEJADA
        );

        controller.cadastrarPesquisa(p1);
        controller.cadastrarPesquisa(p2);
        controller.cadastrarPesquisa(p3);

        // 10. Filtrar por tipo
        System.out.println("\n10. Filtro por tipo:");

        System.out.println(
                "Pesquisas de intenção de voto: "
                        + controller.filtrarPorTipo(
                        TipoPesquisa.INTENCAO_DE_VOTO
                ).size()
        );

        // 11. Filtrar por situação
        System.out.println("\n11. Filtro por situação:");

        System.out.println(
                "Pesquisas planejadas: "
                        + controller.filtrarPorSituacao(
                        SituacaoPesquisa.PLANEJADA
                ).size()
        );

        // 12. Testar tipo nulo
        System.out.println("\n12. Teste de tipo nulo:");

        try {
            controller.filtrarPorTipo(null);
            System.out.println("ERRO: o tipo nulo foi aceito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        // 13. Testar situação nula
        System.out.println("\n13. Teste de situação nula:");

        try {
            controller.filtrarPorSituacao(null);
            System.out.println("ERRO: a situação nula foi aceita.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        }

        System.out.println("\n=== FIM DOS TESTES ===");
    }
}