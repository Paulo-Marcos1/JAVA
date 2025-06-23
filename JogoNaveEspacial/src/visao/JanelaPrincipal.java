package visao;

import javax.swing.JFrame;
import java.awt.CardLayout;

/**
 * Classe que representa a janela principal do jogo.
 * Gerencia todas as telas do jogo usando CardLayout.
 */
public class JanelaPrincipal extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private CardLayout layout;
    
    // Constantes para identificar as telas
    public static final String TELA_INICIAL = "TelaInicial";
    public static final String TELA_SELECAO_NAVE = "TelaSelecaoNave";
    public static final String TELA_JOGO = "TelaJogo";
    public static final String TELA_RANKING = "TelaRanking";
    
    /**
     * Construtor da classe JanelaPrincipal
     */
    public JanelaPrincipal() {
        // Configurações básicas da janela
        setTitle("Jogo de Nave Espacial");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // Centraliza a janela
        
        // Configura o layout como CardLayout para alternar entre telas
        layout = new CardLayout();
        setLayout(layout);
        
        // Inicializa e adiciona as telas
        inicializarTelas();
    }
    
    /**
     * Inicializa e adiciona todas as telas ao CardLayout
     */
    private void inicializarTelas() {
        // Cria e adiciona a tela inicial
        TelaInicial telaInicial = new TelaInicial(this);
        add(telaInicial, TELA_INICIAL);
        
        // Cria e adiciona a tela de seleção de nave
        TelaSelecaoNave telaSelecaoNave = new TelaSelecaoNave(this);
        add(telaSelecaoNave, TELA_SELECAO_NAVE);
        
        // Cria e adiciona a tela de ranking
        TelaRanking telaRanking = new TelaRanking(this);
        add(telaRanking, TELA_RANKING);
        
        // A tela de jogo será criada dinamicamente quando o jogador escolher uma nave
    }
    
    /**
     * Cria e adiciona a tela de jogo com a nave escolhida
     */
    public void criarTelaJogo(String tipoNave) {
        // Remove a tela de jogo anterior se existir
        try {
            remove(getComponentByName(TELA_JOGO));
        } catch (Exception e) {
            // Ignora se não existir
        }
        
        // Cria e adiciona a nova tela de jogo
        TelaJogo telaJogo = new TelaJogo(this, tipoNave);
        add(telaJogo, TELA_JOGO);
    }
    
    /**
     * Muda para a tela especificada
     */
    public void mostrarTela(String nomeTela) {
        layout.show(getContentPane(), nomeTela);
        
        // Se for a tela de jogo, solicita o foco para capturar eventos de teclado
        if (nomeTela.equals(TELA_JOGO)) {
            getComponentByName(TELA_JOGO).requestFocus();
        }
    }
    
    /**
     * Obtém um componente pelo nome
     */
    private java.awt.Component getComponentByName(String nome) {
        for (java.awt.Component comp : getContentPane().getComponents()) {
            if (comp.getName() != null && comp.getName().equals(nome)) {
                return comp;
            }
        }
        return null;
    }
    
    /**
     * Método principal para iniciar o jogo
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            JanelaPrincipal janela = new JanelaPrincipal();
            janela.setVisible(true);
        });
    }
}
