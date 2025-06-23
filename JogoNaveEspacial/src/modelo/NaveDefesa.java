package modelo;

import java.awt.Image;

/**
 * Classe que representa uma nave de defesa.
 * Características: maior resistência, menor velocidade.
 */
public class NaveDefesa extends NaveEspacial {
    private static final int PODER_ATAQUE = 10;  // Poder de ataque padrão
    
    /**
     * Construtor da classe NaveDefesa
     * 
     * @param posX Posição inicial X
     * @param posY Posição inicial Y
     */
    public NaveDefesa(int posX, int posY) {
        // Construtor existente
        super(posX, posY, 4, 80, 40, 40);

        // Carrega a imagem da nave
        try {
            this.imagem = javax.imageio.ImageIO.read(new java.io.File("src/imagens/nave_defesa.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Implementação do movimento para a nave de defesa
     * Movimento mais lento devido à baixa velocidade
     */
    @Override
    public void mover(int direcaoX, int direcaoY) {
        // Atualiza posição com base na direção e velocidade
        posX += direcaoX * velocidade;
        posY += direcaoY * velocidade;
        
        // Limites da tela (assumindo tela de 800x600)
        if (posX < 0) {
            posX = 0;
        } else if (posX > 750) { // 800 - largura
            posX = 750;
        }
        
        if (posY < 0) {
            posY = 0;
        } else if (posY > 550) { // 600 - altura
            posY = 550;
        }
    }
    
    /**
     * Implementação do disparo para a nave de defesa
     * Projétil padrão
     */
    @Override
    public Projetil atirar() {
        // Cria um projétil na posição da nave, com velocidade e poder padrão
        return new Projetil(posX + largura/2 - 5, posY - 10, 7, PODER_ATAQUE, 10, 20);
    }
}
