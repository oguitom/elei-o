package br.unb.eleicoes.dominio.candidatos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeitorDadosCSV {

    public List<Partido> lerPartidos(String caminhoArquivo) {
        List<Partido> partidos = new ArrayList<>();

        try (BufferedReader leitor = Files.newBufferedReader(
                Path.of(caminhoArquivo), StandardCharsets.UTF_8)) {

            String linha;
            int numeroLinha = 0;

            while ((linha = leitor.readLine()) != null) {
                numeroLinha++;

                // Ignora o cabeçalho do arquivo.
                if (numeroLinha == 1) {
                    continue;
                }

                if (linha.isBlank()) {
                    continue;
                }

                String[] dados = linha.split(",", -1);

                if (dados.length != 3) {
                    throw new IllegalArgumentException(
                            "Formato inválido no arquivo de partidos, linha "
                                    + numeroLinha + "."
                    );
                }

                Partido partido = new Partido(
                        dados[0].trim(),
                        dados[1].trim(),
                        dados[2].trim()
                );

                partidos.add(partido);
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Não foi possível ler o arquivo de partidos: "
                            + caminhoArquivo, e
            );
        }

        return List.copyOf(partidos);
    }

    public List<Candidato> lerCandidatos(String caminhoArquivo) {
        List<Partido> partidos = lerPartidos("dados/partidos.csv");
        return lerCandidatos(caminhoArquivo, partidos);
    }

    public List<Candidato> lerCandidatos(
            String caminhoArquivo,
            List<Partido> partidos) {
        List<Candidato> candidatos = new ArrayList<>();

        try (BufferedReader leitor = Files.newBufferedReader(
                Path.of(caminhoArquivo), StandardCharsets.UTF_8)) {

            String linha;
            int numeroLinha = 0;

            while ((linha = leitor.readLine()) != null) {
                numeroLinha++;

                if (numeroLinha == 1) {
                    continue;
                }

                if (linha.isBlank()) {
                    continue;
                }

                String[] dados = linha.split(",", -1);

                if (dados.length != 5) {
                    throw new IllegalArgumentException(
                            "Formato inválido no arquivo de candidatos, linha "
                                    + numeroLinha + "."
                    );
                }

                String codigoPartido = dados[4].trim();
                Partido partidoEncontrado = null;

                for (Partido partido : partidos) {
                    if (partido.getCodigo().equals(codigoPartido)) {
                        partidoEncontrado = partido;
                        break;
                    }
                }

                if (partidoEncontrado == null) {
                    throw new IllegalArgumentException(
                            "Partido com código " + codigoPartido
                                    + " não encontrado na linha " + numeroLinha
                                    + " do arquivo de candidatos."
                    );
                }

                Candidato candidato = new Candidato(
                        dados[0].trim(),
                        dados[1].trim(),
                        dados[2].trim(),
                        dados[3].trim(),
                        partidoEncontrado
                );

                candidatos.add(candidato);
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Não foi possível ler o arquivo de candidatos: "
                            + caminhoArquivo, e
            );
        }

        return List.copyOf(candidatos);
    }
    public List<Candidato> lerCandidatos(
            String caminhoArquivoCandidatos,
            String caminhoArquivoPartidos) {

        List<Partido> partidos = lerPartidos(caminhoArquivoPartidos);

        // A leitura dos candidatos será aproveitada
        // na próxima alteração.
        return lerCandidatos(caminhoArquivoCandidatos);
    }
}