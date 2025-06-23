package visao;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import banco.PontuacaoDAO;
import modelo.Pontuacao;

/**
 * Classe que representa a tela de ranking do jogo.
 * Exibe as 5 melhores pontuações registradas no banco de dados.
 */
public class TelaRanking extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private JanelaPrincipal janelaPrincipal;
    private JTable tabelaRanking;
    private DefaultTableModel modeloTabela;
    private PontuacaoDAO pontuacaoDAO;
    
    /**
     * Construtor da classe TelaRanking
     * 
     * @param janelaPrincipal Referência para a janela principal
     */
    public TelaRanking(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        this.pontuacaoDAO = new PontuacaoDAO();
        setName(JanelaPrincipal.TELA_RANKING);
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
        JLabel labelTitulo = new JLabel("RANKING DE PONTUAÇÕES");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        labelTitulo.setForeground(Color.WHITE);
        painelTitulo.add(labelTitulo);
        add(painelTitulo, BorderLayout.NORTH);
        
        // Painel central com a tabela de ranking
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBackground(Color.BLACK);
        
        // Cria a tabela de ranking
        String[] colunas = {"Posição", "Nome", "Pontuação", "Data"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a edição das células
            }
        };
        
        tabelaRanking = new JTable(modeloTabela);
        tabelaRanking.setBackground(Color.BLACK);
        tabelaRanking.setForeground(Color.WHITE);
        tabelaRanking.setGridColor(Color.GRAY);
        tabelaRanking.getTableHeader().setBackground(new Color(50, 50, 50));
        tabelaRanking.getTableHeader().setForeground(Color.WHITE);
        tabelaRanking.setRowHeight(30);
        tabelaRanking.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JScrollPane scrollPane = new JScrollPane(tabelaRanking);
        scrollPane.setBackground(Color.BLACK);
        scrollPane.getViewport().setBackground(Color.BLACK);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(20, 50, 20, 50);
        
        painelCentral.add(scrollPane, gbc);
        add(painelCentral, BorderLayout.CENTER);
        
        // Painel para o botão de voltar
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(Color.BLACK);
        
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 16));
        botaoVoltar.addActionListener(e -> janelaPrincipal.mostrarTela(JanelaPrincipal.TELA_INICIAL));
        painelBotoes.add(botaoVoltar);
        
        add(painelBotoes, BorderLayout.SOUTH);
    }
    
    /**
     * Atualiza a tabela de ranking com as pontuações do banco de dados.
     * Este método é chamado sempre que a tela é exibida.
     */
    public void atualizarRanking() {
        // Limpa a tabela
        modeloTabela.setRowCount(0);
        
        try {
            // Obtém as 5 melhores pontuações
            List<Pontuacao> topPontuacoes = pontuacaoDAO.getTopPontuacoes();
            
            // Adiciona as pontuações à tabela
            int posicao = 1;
            for (Pontuacao p : topPontuacoes) {
                Object[] linha = {
                    posicao++,
                    p.getNomeJogador(),
                    p.getPontuacao(),
                    p.getData()
                };
                modeloTabela.addRow(linha);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar ranking: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Este método é chamado quando a tela é exibida
     */
    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            atualizarRanking();
        }
        super.setVisible(visible);
    }
}
