import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        JFrame janela = new JFrame("salva nois");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        janela.setLayout(new FlowLayout());

        JButton botao = new JButton("Clique aqui!");
        botao.setPreferredSize(new Dimension(100, 100));

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botão clicado!");
            }
        });

        janela.add(botao);
        janela.pack();
        janela.setVisible(true);
    }
}
