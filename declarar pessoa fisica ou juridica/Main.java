import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite 1 para pessoa fisica ou 2 para pessoa juridica: ");
        int op = input.nextInt();
        input.nextLine();
         if (op == 1){
             PessoaFisica pessoa1= new PessoaFisica();
             System.out.print("Digite seu nome: ");
             String nome = input.nextLine();
             pessoa1.setNome(nome);
             System.out.print("Digite seu Endereço: ");
             String endereco = input.nextLine();
             pessoa1.setEndereco(endereco);
             System.out.print("Digite seu numero de celular: ");
             long tel = input.nextLong();
             pessoa1.setTel(tel);
             System.out.print("Digite o seu CPF: ");
             long cpf = input.nextLong();
             pessoa1.setCPF(cpf);
             System.out.println("Mostrando informações.");
             System.out.println("Nome: "+ pessoa1.getNome());
             System.out.println("CPF: "+ pessoa1.getCPF());
             System.out.println("Telefone: " + pessoa1.getTel());
             System.out.println("Endereço: "+ pessoa1.getEndereco());
         }
         if (op == 2){
             PessoaJuridica pessoa = new PessoaJuridica();
             System.out.print("Digite seu nome: ");
             String nome = input.nextLine();
             pessoa.setNome(nome);
             System.out.print("Digite seu Endereço: ");
             String endereco = input.nextLine();
             pessoa.setEndereco(endereco);
             System.out.print("Digite seu numero de celular: ");
             long tel = input.nextLong();
             pessoa.setTel(tel);
             System.out.print("Digite o seu CNPJ: ");
             long cnpj = input.nextLong();
             pessoa.setCNPJ(cnpj);
             System.out.println("Mostrando informações.");
             System.out.println("Nome: "+ pessoa.getNome());
             System.out.println("CNPJ: "+ pessoa.getCNPJ());
             System.out.println("Telefone: " + pessoa.getTel());
             System.out.println("Endereço: "+ pessoa.getEndereco());
         }
    }
}
