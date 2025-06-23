package visao;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Classe que representa a tela inicial do jogo.
 * Exibe o título do jogo e botões para iniciar o jogo e ver o ranking.
 */
public class TelaInicial extends JPanel {

    
    private static final long serialVersionUID = 1L;
    private JanelaPrincipal janelaPrincipal;
    
    /**
     * Construtor da classe TelaInicial

     */
    public TelaInicial(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        setName(JanelaPrincipal.TELA_INICIAL);
        inicializarComponentes();
    }
    
    /**
     * Inicializa e configura os componentes da tela
     */
    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        
        // Painel para o título
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(Color.BLACK);
        JLabel labelTitulo = new JLabel("Guerra Intergalatica");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        labelTitulo.setForeground(Color.WHITE);
        painelTitulo.add(labelTitulo);
        add(painelTitulo, BorderLayout.NORTH);
        
        // Painel central com botões
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBackground(Color.BLACK);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        
        // Botão para iniciar o jogo
        JButton botaoIniciar = new JButton("Iniciar Jogo");
        botaoIniciar.setFont(new Font("Arial", Font.BOLD, 20));
        botaoIniciar.addActionListener(e -> janelaPrincipal.mostrarTela(JanelaPrincipal.TELA_SELECAO_NAVE));
        painelCentral.add(botaoIniciar, gbc);
        
        // Botão para ver o ranking
        JButton botaoRanking = new JButton("Ver Ranking");
        botaoRanking.setFont(new Font("Arial", Font.BOLD, 20));
        botaoRanking.addActionListener(e -> janelaPrincipal.mostrarTela(JanelaPrincipal.TELA_RANKING));
        painelCentral.add(botaoRanking, gbc);
        
        // Botão para sair do jogo
        JButton botaoSair = new JButton("Sair");
        botaoSair.setFont(new Font("Arial", Font.BOLD, 20));
        botaoSair.addActionListener(e -> System.exit(0));
        painelCentral.add(botaoSair, gbc);
        
        add(painelCentral, BorderLayout.CENTER);
        

    }
}
