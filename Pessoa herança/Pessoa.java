import  java.util.Date;

class Pessoa {
    private String nome;
    private String cpf;
    private Date dataDeNascimento;


    public Pessoa(String nome, String cpf, Date dataDeNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public  String getCpf(){
        return cpf;
    }

    public  void setCpf(String cpf){
        this.cpf = cpf;
    }

    public Date getDataDeNascimento(){
        return dataDeNascimento;
    }

    public void  setDataDeNascimento(Date dataDeNascimento){
        this.dataDeNascimento = dataDeNascimento;
    }

}
