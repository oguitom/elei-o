package br.unb.eleicoes.dominio.pesquisas;

import br.unb.eleicoes.dominio.candidatos.Candidato;
import br.unb.eleicoes.dominio.candidatos.LeitorDadosCSV;

import java.util.List;

public class TesteAssociacaoCandidatos {

    public static void main(String[] args) {
        LeitorDadosCSV leitor = new LeitorDadosCSV();

        List<Candidato> candidatos = leitor.lerCandidatos(
                "dados/candidatos.csv"
        );

        Pesquisa pesquisa = new Pesquisa(
                "PES001",
                "Pesquisa de intenção de voto",
                "Governador",
                TipoPesquisa.INTENCAO_DE_VOTO,
                SituacaoPesquisa.PLANEJADA
        );

        System.out.println("Pesquisa: " + pesquisa.getNome());
        System.out.println("Cargo: " + pesquisa.getCargo());

        // Teste 1: iniciar sem candidatos.
        System.out.println("\nTeste 1: iniciar sem candidatos");

        try {
            pesquisa.iniciarColeta();
            System.out.println("ERRO: a coleta não deveria ter iniciado.");
        } catch (IllegalStateException erro) {
            System.out.println("Validação funcionando: " + erro.getMessage());
        }

        // Associar dois candidatos.
        pesquisa.adicionarCandidato(candidatos.get(0));
        pesquisa.adicionarCandidato(candidatos.get(1));

        System.out.println("\nCandidatos associados:");

        for (Candidato candidato : pesquisa.getCandidatos()) {
            System.out.println(
                    candidato.getNome()
                            + " - "
                            + candidato.getPartido().getSigla()
            );
        }

        // Teste 2: iniciar com candidatos.
        System.out.println("\nTeste 2: iniciar com candidatos");

        pesquisa.iniciarColeta();

        System.out.println("Situação: " + pesquisa.getSituacao());

        // Teste 3: tentar adicionar candidato durante a coleta.
        System.out.println("\nTeste 3: adicionar candidato durante a coleta");

        try {
            pesquisa.adicionarCandidato(candidatos.get(2));
            System.out.println("ERRO: o candidato não deveria ser associado.");
        } catch (IllegalStateException erro) {
            System.out.println("Validação funcionando: " + erro.getMessage());
        }

        // Teste 4: encerrar a coleta.
        System.out.println("\nTeste 4: encerrar a coleta");

        pesquisa.encerrarColeta();

        System.out.println("Situação após encerramento: "
                + pesquisa.getSituacao());

        // Teste 5: tentar encerrar novamente.
        System.out.println("\nTeste 5: tentar encerrar novamente");

        try {
            pesquisa.encerrarColeta();
            System.out.println(
                    "ERRO: a coleta não deveria ser encerrada novamente."
            );
        } catch (IllegalStateException erro) {
            System.out.println("Validação funcionando: " + erro.getMessage());
        }
    }
}