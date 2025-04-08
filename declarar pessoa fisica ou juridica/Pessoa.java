public class Pessoa {
    protected String nome;
    protected String endereco;
    protected long tel;

    public String getNome(){
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(final long tel) {
        this.tel = tel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
