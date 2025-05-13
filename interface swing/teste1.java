import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main {
    public static void main(String[] args) {

        JFrame janela = new JFrame("salva nois");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500,500);

        JButton botao = new JButton("Clique aqui!");
        botao.setSize(200,200);
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botão clicado!");
            }
        });

        janela.add(botao);

        janela.setVisible(true);
    }
}
