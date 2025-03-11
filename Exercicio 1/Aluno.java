import java.time.LocalDate;
import java.time.Period;

public class Aluno {

    private String nome;
    private LocalDate DatadeNascimento;

    public Aluno(String nome, LocalDate DatadeNascimento){
        this.nome = nome;
        this.DatadeNascimento = DatadeNascimento;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(){
        this.nome = nome;
    }

    public  LocalDate getDatadeNascimento(){
        return DatadeNascimento;
    }

    public void setDatadeNascimento(LocalDate datadeNascimento) {
        this.DatadeNascimento = datadeNascimento;
    }

    public int calcularidade(){

        return Period.between(this.DatadeNascimento, LocalDate.now()).getYears();
    }
}
