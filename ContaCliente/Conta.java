public class Conta {

    private double saldo = 0;


    public void depositar(double Valor){
        this.saldo = this.saldo + Valor + (Valor * 0.10);

    }
    public void sacar(double Valor){
        this.saldo = this.saldo - Valor;
    }

    public double getSaldo() {
        return this.saldo;
    }

}
