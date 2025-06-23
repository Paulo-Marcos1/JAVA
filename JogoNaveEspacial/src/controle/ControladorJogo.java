package controle;

import java.util.List;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;

import visao.TelaJogo;
import banco.PontuacaoDAO;
import modelo.Pontuacao;

/**
 * Classe responsável por controlar o fluxo do jogo.
 */
public class ControladorJogo {
    
    private TelaJogo telaJogo;
    private PontuacaoDAO pontuacaoDAO;
    

    public ControladorJogo(TelaJogo telaJogo) {
        this.telaJogo = telaJogo;
        this.pontuacaoDAO = new PontuacaoDAO();
    }
    
    /**
     * Salva a pontuação do jogador no banco de dados
     */
    public void salvarPontuacao(int pontuacao) {
        try {
            // Solicita o nome do jogador
            String nomeJogador = JOptionPane.showInputDialog(
                telaJogo,
                "Fim de jogo! Sua pontuação: " + pontuacao + "\nDigite seu nome:",
                "Fim de Jogo",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            // Se o jogador cancelar ou não digitar um nome, usa "Anônimo"
            if (nomeJogador == null || nomeJogador.trim().isEmpty()) {
                nomeJogador = "Anônimo";
            }
            
            // Formata a data atual
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dataAtual = sdf.format(new Date());
            
            // Cria o objeto Pontuacao
            Pontuacao novaPontuacao = new Pontuacao(nomeJogador, pontuacao, dataAtual);
            
            // Salva no banco de dados
            pontuacaoDAO.inserirPontuacao(novaPontuacao);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                telaJogo,
                "Erro ao salvar pontuação: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
    
    /**
     * Obtém as melhores pontuações do banco de dados

     */
    public List<Pontuacao> obterMelhoresPontuacoes() {
        try {
            return pontuacaoDAO.getTopPontuacoes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                telaJogo,
                "Erro ao obter pontuações: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Verifica se a pontuação atual é um novo recorde
     */
    public boolean verificarNovoRecorde(int pontuacao) {
        try {
            List<Pontuacao> topPontuacoes = pontuacaoDAO.getTopPontuacoes();
            
            // Se não houver pontuações ou a pontuação atual for maior que a menor do top 5
            if (topPontuacoes.isEmpty() || topPontuacoes.size() < 5) {
                return true;
            } else {
                // Verifica se a pontuação atual é maior que a menor do top 5
                int menorPontuacao = Integer.MAX_VALUE;
                for (Pontuacao p : topPontuacoes) {
                    if (p.getPontuacao() < menorPontuacao) {
                        menorPontuacao = p.getPontuacao();
                    }
                }
                
                return pontuacao > menorPontuacao;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
