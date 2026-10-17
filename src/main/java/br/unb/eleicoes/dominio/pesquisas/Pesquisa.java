package br.unb.eleicoes.dominio.pesquisas;

import java.time.LocalDate; //permite representar datas sem horário.
import java.util.List;
import java.util.ArrayList;
import br.unb.eleicoes.dominio.candidatos.Candidato;

public class Pesquisa {


    private String codigo; //Identificar unicamente a pesquisa.
    private String nome; //Armazenar seu título.
    private String cargo; //Indicar o cargo eleitoral investigado.
    private TipoPesquisa tipo; //Registrar o tipo de pesquisa.
    private SituacaoPesquisa situacao; //Representar seu estado no ciclo de vida.

    private LocalDate dataInicioColeta; //Registrar a data de início da coleta.
    private LocalDate dataFimColeta; //Registrar a data de fim da coleta.

    private List<String> abrangencia;
    private PlanoAmostral planoAmostral;
    private List<Pergunta> perguntas;
    private List<Candidato> candidatos;

    private String validarTextoObrigatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " não pode ser vazio.");
        }
        return valor.trim();
    }
    private void validarPeriodoColeta(
            LocalDate inicio, LocalDate fim) {
        if (inicio != null && fim != null && fim.isBefore(inicio)) {
            throw new IllegalArgumentException( "A data final da coleta não pode ser anterior à data inicial.");
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = validarTextoObrigatorio(codigo, "Código");
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = validarTextoObrigatorio(cargo, "Cargo");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = validarTextoObrigatorio(nome, "Nome");
    }

    public TipoPesquisa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPesquisa tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo da pesquisa deve ser informado.");
        }

        this.tipo = tipo;
    }
    public SituacaoPesquisa getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoPesquisa situacao) {
        if (situacao == null) {
            throw new IllegalArgumentException("A situação da pesquisa deve ser informada.");
        }
        this.situacao = situacao;
    }

    public LocalDate getDataInicioColeta() {
        return dataInicioColeta;
    }
    public void setDataInicioColeta(LocalDate dataInicioColeta) {
        validarPeriodoColeta(dataInicioColeta, this.dataFimColeta);
        this.dataInicioColeta = dataInicioColeta;
    }

    public LocalDate getDataFimColeta() {
        return dataFimColeta;
    }

    public void setDataFimColeta(LocalDate dataFimColeta) {
        validarPeriodoColeta(this.dataInicioColeta, dataFimColeta);
        this.dataFimColeta = dataFimColeta;
    }

    public List<String> getAbrangencia() {
        return abrangencia == null ? null : List.copyOf(abrangencia);
    }

    public void setAbrangencia(List<String> abrangencia) {
        if (abrangencia == null || abrangencia.isEmpty()) {
            throw new IllegalArgumentException("A pesquisa deve possuir pelo menos uma região.");
        }
        List<String> regioesValidadas = new ArrayList<>();
        for (String regiao : abrangencia) {
            regioesValidadas.add(validarTextoObrigatorio(regiao, "Região"));
        }
        this.abrangencia = List.copyOf(regioesValidadas);

    }


    public PlanoAmostral getPlanoAmostral() {
        return planoAmostral;
    }

    public void setPlanoAmostral(PlanoAmostral planoAmostral) {
        if (planoAmostral == null) {
            throw new IllegalArgumentException(
                    "O plano amostral deve ser informado."
            );
        }

        this.planoAmostral = planoAmostral;
    }

    public List<Pergunta> getPerguntas() {
        return List.copyOf(perguntas);
    }

    public void adicionarPergunta(Pergunta pergunta) {
        if (pergunta == null) {
            throw new IllegalArgumentException(
                    "A pergunta não pode ser nula.");
        }

        perguntas.add(pergunta);
    }

    public void removerPergunta(Pergunta pergunta) {
        perguntas.remove(pergunta);
    }


    public void adicionarCandidato(Candidato candidato) {
        if (situacao != SituacaoPesquisa.PLANEJADA) {
            throw new IllegalStateException(
                    "Só é possível associar candidatos a uma pesquisa planejada."
            );
        }
        if (candidato == null) {
            throw new IllegalArgumentException(
                    "O candidato não pode ser nulo.");
        }

        if (!this.cargo.equalsIgnoreCase(candidato.getCargo())) {
            throw new IllegalArgumentException(
                    "O cargo do candidato deve ser compatível "
                            + "com o cargo da pesquisa.");
        }

        for (Candidato candidatoExistente : candidatos) {
            if (candidatoExistente.getCodigo()
                    .equals(candidato.getCodigo())) {
                throw new IllegalArgumentException(
                        "O candidato já está associado a esta pesquisa.");
            }
        }

        candidatos.add(candidato);
    }

    public List<Candidato> getCandidatos() {
        return List.copyOf(candidatos);
    }

    private void validarProntaParaColeta() {
        if (tipo == TipoPesquisa.INTENCAO_DE_VOTO
                && candidatos.isEmpty()) {
            throw new IllegalStateException(
                    "Uma pesquisa de intenção de voto deve possuir "
                            + "pelo menos um candidato antes de iniciar a coleta."
            );
        }
    }

    public void iniciarColeta() {
        if (situacao != SituacaoPesquisa.PLANEJADA) {
            throw new IllegalStateException(
                    "Somente uma pesquisa planejada pode iniciar a coleta."
            );
        }

        validarProntaParaColeta();
        setSituacao(SituacaoPesquisa.EM_COLETA);
    }
    public void encerrarColeta() {
        if (situacao != SituacaoPesquisa.EM_COLETA) {
            throw new IllegalStateException(
                    "Somente uma pesquisa em coleta pode ser encerrada."
            );
        }

        setSituacao(SituacaoPesquisa.ENCERRADA);
    }

    public Pesquisa(String codigo,
                    String nome,
                    String cargo,
                    TipoPesquisa tipo,
                    SituacaoPesquisa situacao) {
        setCodigo(codigo);
        setNome(nome);
        setCargo(cargo);
        setTipo(tipo);
        setSituacao(situacao);

        this.perguntas = new ArrayList<>();
        this.candidatos = new ArrayList<>();
    }
}