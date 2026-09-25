package br.unb.eleicoes.apresentacao;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final JTextField emailField = new JTextField();
    private final JPasswordField senhaField = new JPasswordField();

    public LoginFrame() {
        setTitle("Sistema de Análise de Pesquisas Eleitorais - Login");
        setSize(480, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Sistema de Análise de Pesquisas Eleitorais", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 17));

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        painel.add(titulo, c);

        c.gridwidth = 1;
        c.gridx = 0; c.gridy++;
        painel.add(new JLabel("E-mail:"), c);
        c.gridx = 1;
        painel.add(emailField, c);

        c.gridx = 0; c.gridy++;
        painel.add(new JLabel("Senha:"), c);
        c.gridx = 1;
        painel.add(senhaField, c);

        JButton entrar = new JButton("Entrar");
        c.gridx = 0; c.gridy++;
        painel.add(entrar, c);

        JButton sair = new JButton("Sair");
        c.gridx = 1;
        painel.add(sair, c);

        JLabel aviso = new JLabel("Demonstração acadêmica — dados fictícios.", SwingConstants.CENTER);
        c.gridx = 0; c.gridy++; c.gridwidth = 2;
        painel.add(aviso, c);

        entrar.addActionListener(e -> autenticar());
        sair.addActionListener(e -> System.exit(0));

        add(painel);
    }

    private void autenticar() {
        String email = emailField.getText().trim();
        String senha = new String(senhaField.getPassword());

        if ("admin@eleicoes.local".equals(email) && "12345678".equals(senha)) {
            dispose();
            new MenuPrincipalFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Para a demonstração, utilize:\nadmin@eleicoes.local\n12345678",
                    "Acesso de demonstração",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
