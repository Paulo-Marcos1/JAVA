package modelo;

/**
 * Classe que representa uma pontuação no ranking do jogo.
 */
public class Pontuacao {
    private int id;
    private String nomeJogador;
    private int pontuacao;
    private String data;
    

    public Pontuacao(int id, String nomeJogador, int pontuacao, String data) {
        this.id = id;
        this.nomeJogador = nomeJogador;
        this.pontuacao = pontuacao;
        this.data = data;
    }
    

    public Pontuacao(String nomeJogador, int pontuacao, String data) {
        this(-1, nomeJogador, pontuacao, data);
    }
    
    // Getters e Setters
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNomeJogador() {
        return nomeJogador;
    }
    
    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }
    
    public int getPontuacao() {
        return pontuacao;
    }
    
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "Pontuacao [id=" + id + ", nomeJogador=" + nomeJogador + ", pontuacao=" + pontuacao + ", data=" + data + "]";
    }
}
