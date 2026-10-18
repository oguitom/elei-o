package br.unb.eleicoes.dominio.controladores;

import br.unb.eleicoes.dominio.pesquisas.Pesquisa;
import br.unb.eleicoes.dominio.pesquisas.TipoPesquisa;
import br.unb.eleicoes.dominio.pesquisas.SituacaoPesquisa;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class PesquisaController {

    private final List<Pesquisa> pesquisas = new ArrayList<>();

    public void cadastrarPesquisa(Pesquisa pesquisa) {
        if (pesquisa == null) {
            throw new IllegalArgumentException(
                    "A pesquisa não pode ser nula."
            );
        }

        for (Pesquisa cadastrada : pesquisas) {
            if (cadastrada.getCodigo().equals(pesquisa.getCodigo())) {
                throw new IllegalArgumentException(
                        "Já existe uma pesquisa com esse código."
                );
            }
        }

        pesquisas.add(pesquisa);
    }

    public List<Pesquisa> listarPesquisas() {
        return List.copyOf(pesquisas);
    }

    public Pesquisa buscarPorCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                    "O código da pesquisa deve ser informado."
            );
        }

        for (Pesquisa pesquisa : pesquisas) {
            if (pesquisa.getCodigo().equals(codigo.trim())) {
                return pesquisa;
            }
        }

        return null;
    }

    public void atualizarPesquisa(Pesquisa pesquisaAtualizada) {
        if (pesquisaAtualizada == null) {
            throw new IllegalArgumentException(
                    "A pesquisa não pode ser nula."
            );
        }

        for (int i = 0; i < pesquisas.size(); i++) {
            if (pesquisas.get(i).getCodigo()
                    .equals(pesquisaAtualizada.getCodigo())) {
                pesquisas.set(i, pesquisaAtualizada);
                return;
            }
        }

        throw new IllegalArgumentException(
                "Pesquisa não encontrada para atualização."
        );
    }

    public boolean removerPesquisa(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                    "O código da pesquisa deve ser informado."
            );
        }

        return pesquisas.removeIf(
                pesquisa -> pesquisa.getCodigo().equals(codigo.trim())
        );
    }

    public List<Pesquisa> filtrarPorTipo(TipoPesquisa tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException(
                    "O tipo da pesquisa deve ser informado."
            );
        }

        return pesquisas.stream()
                .filter(pesquisa -> pesquisa.getTipo() == tipo)
                .collect(Collectors.toList());
    }

    public List<Pesquisa> filtrarPorSituacao(SituacaoPesquisa situacao) {
        if (situacao == null) {
            throw new IllegalArgumentException(
                    "A situação da pesquisa deve ser informada."
            );
        }

        return pesquisas.stream()
                .filter(pesquisa -> pesquisa.getSituacao() == situacao)
                .collect(Collectors.toList());
    }
}