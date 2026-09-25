package br.unb.eleicoes.apresentacao;

import javax.swing.*;
import java.awt.*;

public class ModuloFrame extends JFrame {
    public ModuloFrame(String modulo) {
        setTitle(modulo);
        setSize(720, 500);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel(modulo, SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel conteudo = new JPanel(new BorderLayout(10, 10));
        conteudo.add(new JLabel("Protótipo da tela — funcionalidades serão implementadas nas próximas etapas.",
                SwingConstants.CENTER), BorderLayout.NORTH);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (String opcao : opcoes(modulo)) modelo.addElement(opcao);
        JList<String> lista = new JList<>(modelo);
        conteudo.add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel acoes = new JPanel(new FlowLayout());
        JButton abrir = new JButton("Abrir opção");
        JButton voltar = new JButton("Fechar");
        acoes.add(abrir);
        acoes.add(voltar);
        conteudo.add(acoes, BorderLayout.SOUTH);

        abrir.addActionListener(e -> {
            String selecionada = lista.getSelectedValue();
            if (selecionada == null) {
                JOptionPane.showMessageDialog(this, "Selecione uma opção.");
                return;
            }
            JOptionPane.showMessageDialog(this,
                    "Protótipo: " + selecionada + "\n\nSem regra de negócio nesta etapa.",
                    modulo);
        });
        voltar.addActionListener(e -> dispose());

        painel.add(conteudo, BorderLayout.CENTER);
        add(painel);
    }

    private String[] opcoes(String modulo) {
        return switch (modulo) {
            case "Administração" -> new String[]{
                    "Administrar Usuários", "Gerenciar Permissões",
                    "Configurações dos Parâmetros", "Relatórios de Usuários"
            };
            case "Pesquisas" -> new String[]{
                    "Cadastrar Pesquisa", "Configuração da Pesquisa", "Consultar Pesquisas"
            };
            case "Coleta e Amostra" -> new String[]{
                    "Configuração da Amostra", "Registro de Respostas", "Consulta da Coleta"
            };
            case "Análise Estatística e Relatórios" -> new String[]{
                    "Processar Pesquisa", "Resultados Estatísticos",
                    "Comparar Pesquisas", "Relatórios e Gráficos"
            };
            case "Candidatos e Partidos" -> new String[]{
                    "Cadastro de Candidatos", "Cadastro de Partidos",
                    "Consulta de Candidatos", "Indicadores Cadastrais"
            };
            default -> new String[]{"Em construção"};
        };
    }
}
