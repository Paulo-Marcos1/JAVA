package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados.
 */
public class ConexaoDB {
    
    private static ConexaoDB instance;
    private Connection connection;
    private static final String DB_URL = "jdbc:sqlite:jogo_nave.db";

    private ConexaoDB() {
        try {
            // Carrega o driver JDBC do SQLite
            Class.forName("org.sqlite.JDBC");
            
            // Estabelece a conexão com o banco de dados
            connection = DriverManager.getConnection(DB_URL);
            
            // Cria a tabela de pontuações se não existir
            criarTabelaPontuacoes();
            
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver JDBC: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * @return Instância de ConexaoDB
     */
    public static synchronized ConexaoDB getInstance() {
        if (instance == null) {
            instance = new ConexaoDB();
        }
        return instance;
    }
    
    /**
     * Retorna a conexão com o banco de dados
     * 
     * @return Conexão com o banco de dados
     */
    public Connection getConnection() {
        return connection;
    }
    
    /**
     * Cria a tabela de pontuações no banco de dados se não existir
     * 
     * @throws SQLException Em caso de erro na execução do SQL
     */
    private void criarTabelaPontuacoes() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS pontuacoes (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "nome_jogador TEXT NOT NULL, " +
                     "pontuacao INTEGER NOT NULL, " +
                     "data DATE NOT NULL)";
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
    
    /**
     * Fecha a conexão com o banco de dados
     */
    public void fecharConexao() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão com o banco de dados fechada com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
