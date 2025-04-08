public class PessoaJuridica extends Pessoa{
    protected long CNPJ;

    public PessoaJuridica(){

    }

    public long getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(long CNPJ) {
        this.CNPJ = CNPJ;
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
