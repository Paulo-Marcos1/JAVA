package visao;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;

/**
 * Classe que representa a tela de seleção de nave.
 * Permite ao jogador escolher entre os três tipos de nave disponíveis.
 */
public class TelaSelecaoNave extends JPanel {

    private static final long serialVersionUID = 1L;
    private JanelaPrincipal janelaPrincipal;

    /**
     * Construtor da classe TelaSelecaoNave
     *
     * @param janelaPrincipal Referência para a janela principal
     */
    public TelaSelecaoNave(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        setName(JanelaPrincipal.TELA_SELECAO_NAVE);
        inicializarComponentes();
    }

    /**
     * Inicializa e configura os componentes da tela
     */
    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Painel para o título
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(Color.BLACK);
        JLabel labelTitulo = new JLabel("SELECIONE SUA NAVE");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        labelTitulo.setForeground(Color.WHITE);
        painelTitulo.add(labelTitulo);
        add(painelTitulo, BorderLayout.NORTH);

        // Painel central com as opções de nave
        JPanel painelNaves = new JPanel(new GridLayout(1, 3, 20, 0));
        painelNaves.setBackground(Color.BLACK);
        painelNaves.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Nave de Ataque
        JPanel painelNaveAtaque = criarPainelNave(
                "Nave de Ataque",
                "Alta velocidade e poder de ataque",
                "Baixa resistência",
                "ataque"
        );
        painelNaves.add(painelNaveAtaque);

        // Nave de Defesa
        JPanel painelNaveDefesa = criarPainelNave(
                "Nave de Defesa",
                "Alta resistência",
                "Baixa velocidade",
                "defesa"
        );
        painelNaves.add(painelNaveDefesa);

        // Nave Exploradora
        JPanel painelNaveExploradora = criarPainelNave(
                "Nave Exploradora",
                "Velocidade média",
                "Sensores especiais (maior alcance)",
                "exploradora"
        );
        painelNaves.add(painelNaveExploradora);

        add(painelNaves, BorderLayout.CENTER);

        // Painel para o botão de voltar
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setBackground(Color.BLACK);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 16));
        botaoVoltar.addActionListener(e -> janelaPrincipal.mostrarTela(JanelaPrincipal.TELA_INICIAL));
        painelBotoes.add(botaoVoltar);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    /**
     * Cria um painel para exibir informações de uma nave
     *
     * @param titulo Título da nave
     * @param caracteristica1 Primeira característica da nave
     * @param caracteristica2 Segunda característica da nave
     * @param tipoNave Tipo da nave para identificação
     * @return JPanel configurado com as informações da nave
     */
    private JPanel criarPainelNave(String titulo, String caracteristica1, String caracteristica2, String tipoNave) {
        JPanel painelNave = new JPanel(new BorderLayout());
        painelNave.setBackground(new Color(20, 20, 50));
        painelNave.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        // Título da nave
        JLabel labelTitulo = new JLabel(titulo, JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setForeground(Color.WHITE);
        painelNave.add(labelTitulo, BorderLayout.NORTH);

        // Imagem da nave
        JPanel painelImagem = new JPanel();
        painelImagem.setBackground(new Color(20, 20, 50));

        // Carrega a imagem da nave correspondente
        ImageIcon icone = null;
        try {
            String caminhoImagem = "src/imagens/nave_" + tipoNave + ".png";
            icone = new ImageIcon(caminhoImagem);
            // Redimensiona a imagem para um tamanho adequado
            Image img = icone.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            icone = new ImageIcon(img);
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + e.getMessage());
        }

        // Cria o label com a imagem ou com texto alternativo se a imagem falhar
        JLabel labelImagem;
        if (icone != null && icone.getIconWidth() > 0) {
            labelImagem = new JLabel(icone);
        } else {
            labelImagem = new JLabel("[ Imagem da Nave ]");
            labelImagem.setForeground(Color.WHITE);
        }

        painelImagem.add(labelImagem);
        painelNave.add(painelImagem, BorderLayout.CENTER);

        // Características da nave
        JPanel painelCaracteristicas = new JPanel(new GridLayout(3, 1));
        painelCaracteristicas.setBackground(new Color(20, 20, 50));

        JLabel labelCaracteristica1 = new JLabel("• " + caracteristica1, JLabel.CENTER);
        labelCaracteristica1.setForeground(Color.WHITE);
        painelCaracteristicas.add(labelCaracteristica1);

        JLabel labelCaracteristica2 = new JLabel("• " + caracteristica2, JLabel.CENTER);
        labelCaracteristica2.setForeground(Color.WHITE);
        painelCaracteristicas.add(labelCaracteristica2);

        // Botão para selecionar a nave
        JButton botaoSelecionar = new JButton("Selecionar");
        botaoSelecionar.addActionListener(e -> {
            janelaPrincipal.criarTelaJogo(tipoNave);
            janelaPrincipal.mostrarTela(JanelaPrincipal.TELA_JOGO);
        });
        painelCaracteristicas.add(botaoSelecionar);

        painelNave.add(painelCaracteristicas, BorderLayout.SOUTH);

        return painelNave;
    }
}
