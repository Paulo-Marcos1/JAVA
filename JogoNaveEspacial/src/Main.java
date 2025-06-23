import visao.JanelaPrincipal;

public class Main {

    public static void main(String[] args) {
        // Inicia a interface gráfica do jogo
        java.awt.EventQueue.invokeLater(() -> {
            JanelaPrincipal janela = new JanelaPrincipal();
            janela.setVisible(true);
        });
    }
}
