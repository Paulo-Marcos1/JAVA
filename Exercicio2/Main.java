import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Aluno aluno1 = new Aluno("Paulo",  7.5, 5d, 8.5, 4.8);
        Aluno aluno2 = new Aluno("Isaque", 7d, 5.7, 9d, 6.9);
        Aluno aluno3 = new Aluno("Yuri", 9.7, 10d, 6.4, 8.4 );

        ArrayList<Aluno>alunos = new ArrayList<>();
            alunos.add(aluno1);
            alunos.add(aluno2);
            alunos.add(aluno3);


        for (int i = 0; i < alunos.size(); i++) {
          for (int j =  i + 1; j < alunos.size(); j++) {
              if (alunos.get(i).calcularMedia() < alunos.get(j).calcularMedia()) {
                  Aluno troca = alunos.get(i);
                  alunos.set(i, alunos.get(j));
                  alunos.set(j, troca);
              }
          }
        }
        for (Aluno aluno : alunos){
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Média: " + aluno.calcularMedia());
            System.out.println("------------------------");
        }


    }
}
