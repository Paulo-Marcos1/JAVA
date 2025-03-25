public class Main {
    public static void main(String[] args) {
        Conta conta1 = new Conta();
        Conta conta2 = new Conta();

        conta1.setNome("Paulo");
        conta2.setNome("Davi");

        conta1.depositar(1400);
        conta2.depositar(500);

        System.out.println("REALIZANDO O DEPOSITO...");
        System.out.println("Nome: " + conta1.getNome());
        System.out.println("Saldo: " + conta1.getSaldo());
        System.out.println("------------------------");

        System.out.println("Nome: "+ conta2.getNome());
        System.out.println("Saldo: "+ conta2.getSaldo());
        System.out.println("-------------------------");

        conta1.sacar(1000);
        conta2.sacar(300);

        System.out.println("\nREALIZANDO O SAQUE...");
        System.out.println("Nome: " + conta1.getNome());
        System.out.println("Saldo: " + conta1.getSaldo());
        System.out.println("------------------------");

        System.out.println("Nome: "+ conta2.getNome());
        System.out.println("Saldo: "+ conta2.getSaldo());
        System.out.println("-------------------------");

    }

}