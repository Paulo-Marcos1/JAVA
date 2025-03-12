public class Main {
    public static void main(String[] args) {
        Aluno aluno = new Aluno("Paulo",  7.5, 5d, 8.5, 4.8);

        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Media: " + aluno.calcularMedia());
    }
}