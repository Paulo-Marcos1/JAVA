import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ConexaoPostgres {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/exemplo_db";
        String usuario = "postgres";
        String senha = "Estacio@123";
        try {
// Estabelecendo conexão
            Connection conexao = DriverManager.getConnection(url,usuario, senha);
            System.out.println("Conexão com PostgreSQL estabelecida!");
// Criando e executando a consulta
            Statement stmt = conexao.createStatement();
            String sql = "SELECT * FROM cliente";
            ResultSet rs = stmt.executeQuery(sql);
// Exibindo os resultados
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int idade = rs.getInt("idade");
                System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email + ", Idade: " + idade);
            }
// Encerrando recursos
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            System.err.println("Erro na conexão ou na execução: " + e.getMessage());
        }
    }
}
