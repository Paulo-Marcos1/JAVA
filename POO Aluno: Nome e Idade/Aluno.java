import java.time.LocalDate;
import java.time.Period;

public class Aluno {

    private String nome;
    private LocalDate dataDeNascimento;

    public Aluno(String nome, LocalDate dataDeNascimento){
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(){
        this.nome = nome;
    }

    public  LocalDate getdataDeNascimento(){
        return dataDeNascimento;
    }

    public void setdataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public int calcularidade(){

        return Period.between(this.dataDeNascimento, LocalDate.now()).getYears();
    }
}
