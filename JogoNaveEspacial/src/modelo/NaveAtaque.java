package modelo;

import java.awt.Image;

/**
 * Classe que representa uma nave de ataque.
 * Características: alta velocidade e poder de ataque, menor resistência.
 */
public class NaveAtaque extends NaveEspacial {
    private static final int PODER_ATAQUE = 20;  // Poder de ataque superior
    
    /**
     * Construtor da classe NaveAtaque

     */
    public NaveAtaque(int posX, int posY) {
        // Construtor existente
        super(posX, posY, 8, 50, 40, 40);

        // Carrega a imagem da nave
        try {
            this.imagem = javax.imageio.ImageIO.read(new java.io.File("src/imagens/nave_ataque.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Implementação do movimento para a nave de ataque
     * Movimento mais rápido devido à alta velocidade
     */
    @Override
    public void mover(int direcaoX, int direcaoY) {
        // Atualiza posição com base na direção e velocidade
        posX += direcaoX * velocidade;
        posY += direcaoY * velocidade;
        
        // Limites da tela (assumindo tela de 800x600)
        if (posX < 0) {
            posX = 0;
        } else if (posX > 760) { // 800 - largura
            posX = 760;
        }
        
        if (posY < 0) {
            posY = 0;
        } else if (posY > 520) { // 600 - altura
            posY = 520;
        }
    }
    
    /**
     * Implementação do disparo para a nave de ataque
     * Projétil mais poderoso e rápido
     */
    @Override
    public Projetil atirar() {
        // Cria um projétil na posição da nave, com alta velocidade e poder
        return new Projetil(posX + largura/2 - 5, posY - 10, 10, PODER_ATAQUE, 10, 20);
    }
}
