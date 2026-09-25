package br.unb.eleicoes.apresentacao;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalFrame extends JFrame {
    public MenuPrincipalFrame() {
        setTitle("Menu Principal");
        setSize(700, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel botoes = new JPanel(new GridLayout(3, 2, 12, 12));

        adicionarBotao(botoes, "Administração", () -> abrir("Administração"));
        adicionarBotao(botoes, "Pesquisas", () -> abrir("Pesquisas"));
        adicionarBotao(botoes, "Coleta e Amostra", () -> abrir("Coleta e Amostra"));
        adicionarBotao(botoes, "Análise Estatística e Relatórios", () -> abrir("Análise Estatística e Relatórios"));
        adicionarBotao(botoes, "Candidatos e Partidos", () -> abrir("Candidatos e Partidos"));
        adicionarBotao(botoes, "Sair", () -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        painel.add(botoes, BorderLayout.CENTER);
        painel.add(new JLabel("Projeto acadêmico simulado — Etapa 1", SwingConstants.CENTER), BorderLayout.SOUTH);
        add(painel);
    }

    private void adicionarBotao(JPanel painel, String texto, Runnable acao) {
        JButton botao = new JButton(texto);
        botao.addActionListener(e -> acao.run());
        painel.add(botao);
    }

    private void abrir(String modulo) {
        new ModuloFrame(modulo).setVisible(true);
    }
}
