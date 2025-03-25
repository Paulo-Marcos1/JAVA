import java.util.Date;

class Empregado extends Pessoa {
    private double salario;

    // Construtor

    public Empregado(String nome, String cpf, Date dataDeNascimento, double salario){
        super(nome, cpf, dataDeNascimento);
        this.salario = salario;
    }

    public double getSalario(){
        return salario;
    }
}
