public class Aluno {
    private String nome;
    private Double n1,n2,n3,n4;

    public Aluno (String nome, Double n1,Double n2,Double n3 ,Double n4){
        this.nome = nome;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(){
        this.nome = nome;
    }

    public  double calcularMedia(){
        double media = (n1+ n2+n3 +n4 )/4;
    return media;
    }
}