public class PessoaFisica extends Pessoa{
    protected long CPF;

    public PessoaFisica(){

    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getEndereco() {
        return super.getEndereco();
    }

    @Override
    public long getTel() {
        return super.getTel();
    }


}
