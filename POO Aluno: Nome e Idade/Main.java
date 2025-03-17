import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        Aluno aluno = new Aluno("Paulo", LocalDate.of(2001,2,9));

        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Idade: " + aluno.calcularidade());
    }
}
