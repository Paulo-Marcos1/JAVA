package modelo;

import java.awt.Image;

/**
 * Classe que representa uma nave exploradora.
 * Características: velocidade média, sensores especiais (maior alcance de tiro).
 */
public class NaveExploradora extends NaveEspacial {
    private static final int PODER_ATAQUE = 15;  // Poder de ataque médio
    

    public NaveExploradora(int posX, int posY) {
        // Velocidade média, vida média
        super(posX, posY, 6, 60, 45, 45);

        try {
            this.imagem = javax.imageio.ImageIO.read(new java.io.File("src/imagens/nave_exploradora.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Implementação do movimento para a nave exploradora
     * Movimento com velocidade média
     */
    @Override
    public void mover(int direcaoX, int direcaoY) {
        // Atualiza posição com base na direção e velocidade
        posX += direcaoX * velocidade;
        posY += direcaoY * velocidade;
        
        // Limites da tela (assumindo tela de 800x600)
        if (posX < 0) {
            posX = 0;
        } else if (posX > 755) { // 800 - largura
            posX = 755;
        }
        
        if (posY < 0) {
            posY = 0;
        } else if (posY > 555) { // 600 - altura
            posY = 555;
        }
    }
    
    /**
     * Implementação do disparo para a nave exploradora
     * Projétil com maior alcance (mais rápido)
     */
    @Override
    public Projetil atirar() {
        // Cria um projétil na posição da nave, com alta velocidade e poder médio
        return new Projetil(posX + largura/2 - 5, posY - 10, 12, PODER_ATAQUE, 10, 25);
    }
}
