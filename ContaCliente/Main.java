public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();

        cliente1.setNome("Paulo");
        cliente2.setNome("Davi");

        cliente1.setCpf("129-321-321-29");
        cliente2.setCpf("153-273-892-02");

        cliente1.depositar(1400);
        cliente2.depositar(500);

        System.out.println("REALIZANDO O DEPOSITO...\n");
        System.out.println("Nome: " + cliente1.getNome());
        System.out.println("CPF: " + cliente1.getCpf());
        System.out.println("Saldo: " + cliente1.getSaldo());
        System.out.println("------------------------");

        System.out.println("Nome: "+ cliente2.getNome());
        System.out.println("CPF: "+ cliente2.getCpf());
        System.out.println("Saldo: "+ cliente2.getSaldo());
        System.out.println("-------------------------");

        cliente1.sacar(1000);
        cliente2.sacar(300);

        System.out.println("\nREALIZANDO O SAQUE...\n");
        System.out.println("Nome: " + cliente1.getNome());
        System.out.println("CPF: " + cliente1.getCpf());
        System.out.println("Saldo: " + cliente1.getSaldo());
        System.out.println("------------------------");

        System.out.println("Nome: "+ cliente2.getNome());
        System.out.println("CPF: " + cliente2.getCpf());
        System.out.println("Saldo: "+ cliente2.getSaldo());
        System.out.println("-------------------------");

    }

}