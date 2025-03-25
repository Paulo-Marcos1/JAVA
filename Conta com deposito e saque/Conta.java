public class Conta {

    private double saldo;
    private String nome;

    public void depositar(double Valor){
        this.saldo = this.saldo + Valor + (Valor * 0.10);

    }
    public void sacar(double Valor){
        this.saldo = this.saldo - Valor;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
