package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Pontuacao;

/**
 * Classe responsável por realizar operações de acesso a dados relacionadas às pontuações.
 * Implementa o padrão DAO (Data Access Object).
 */
public class PontuacaoDAO {
    
    private Connection connection;
    
    /**
     * Construtor da classe PontuacaoDAO
     */
    public PontuacaoDAO() {
        // Obtém a conexão com o banco de dados através do Singleton
        this.connection = ConexaoDB.getInstance().getConnection();
    }
    
    /**
     * Insere uma nova pontuação no banco de dados
     * 
     * @param pontuacao Objeto Pontuacao a ser inserido
     * @return true se a inserção foi bem-sucedida, false caso contrário
     */
    public boolean inserirPontuacao(Pontuacao pontuacao) {
        String sql = "INSERT INTO pontuacoes (nome_jogador, pontuacao, data) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pontuacao.getNomeJogador());
            stmt.setInt(2, pontuacao.getPontuacao());
            stmt.setString(3, pontuacao.getData());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir pontuação: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Obtém as 5 melhores pontuações do banco de dados
     * 
     * @return Lista com as 5 melhores pontuações
     */
    public List<Pontuacao> getTopPontuacoes() {
        List<Pontuacao> pontuacoes = new ArrayList<>();
        String sql = "SELECT * FROM pontuacoes ORDER BY pontuacao DESC LIMIT 5";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Pontuacao pontuacao = new Pontuacao(
                    rs.getInt("id"),
                    rs.getString("nome_jogador"),
                    rs.getInt("pontuacao"),
                    rs.getString("data")
                );
                pontuacoes.add(pontuacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter top pontuações: " + e.getMessage());
            e.printStackTrace();
        }
        
        return pontuacoes;
    }
    
    /**
     * Obtém uma pontuação específica pelo ID
     *
     */
    public Pontuacao getPontuacaoPorId(int id) {
        String sql = "SELECT * FROM pontuacoes WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Pontuacao(
                        rs.getInt("id"),
                        rs.getString("nome_jogador"),
                        rs.getInt("pontuacao"),
                        rs.getString("data")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter pontuação por ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Atualiza uma pontuação existente no banco de dados
     */
    public boolean atualizarPontuacao(Pontuacao pontuacao) {
        String sql = "UPDATE pontuacoes SET nome_jogador = ?, pontuacao = ?, data = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pontuacao.getNomeJogador());
            stmt.setInt(2, pontuacao.getPontuacao());
            stmt.setString(3, pontuacao.getData());
            stmt.setInt(4, pontuacao.getId());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pontuação: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Remove uma pontuação do banco de dados
     */
    public boolean removerPontuacao(int id) {
        String sql = "DELETE FROM pontuacoes WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao remover pontuação: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
