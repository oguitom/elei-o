package br.unb.eleicoes.dominio.candidatos;

import java.util.List;

public class TesteLeitorDadosCSV {

    public static void main(String[] args) {

        LeitorDadosCSV leitor = new LeitorDadosCSV();

        List<Partido> partidos = leitor.lerPartidos(
                "dados/partidos.csv"
        );

        List<Candidato> candidatos = leitor.lerCandidatos(
                "dados/candidatos.csv"
        );

        System.out.println(
                "Partidos carregados: " + partidos.size()
        );

        System.out.println(
                "Candidatos carregados: " + candidatos.size()
        );

        System.out.println();

        for (Candidato candidato : candidatos) {
            System.out.println(
                    "Candidato: " + candidato.getNome()
            );

            System.out.println(
                    "Número: " + candidato.getNumero()
            );

            System.out.println(
                    "Cargo: " + candidato.getCargo()
            );

            System.out.println(
                    "Partido: "
                            + candidato.getPartido().getSigla()
                            + " - "
                            + candidato.getPartido().getNome()
            );

            System.out.println("-------------------------");
        }
    }
}