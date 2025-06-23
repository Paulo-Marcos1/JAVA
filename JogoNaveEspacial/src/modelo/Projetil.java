package modelo;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * Classe que representa um projétil disparado por uma nave.
 */
public class Projetil {
    private int posX, posY;       // Posição do projétil
    private int velocidade;       // Velocidade de movimento
    private int poder;            // Poder de dano
    private int largura, altura;  // Dimensões para cálculo de colisão
    private Image imagem;         // Imagem que representa o projétil
    private boolean visivel;      // Indica se o projétil está visível/ativo


    public Projetil(int posX, int posY, int velocidade, int poder, int largura, int altura) {
        this.posX = posX;
        this.posY = posY;
        this.velocidade = velocidade;
        this.poder = poder;
        this.largura = largura;
        this.altura = altura;
        this.visivel = true;

        try {
            this.imagem = javax.imageio.ImageIO.read(new java.io.File("src/imagens/missel.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Move o projétil na tela (normalmente para cima)
     */
    public void mover() {
        this.posY -= velocidade;
        
        // Se o projétil sair da tela, torna-o invisível
        if (this.posY < 0) {
            this.visivel = false;
        }
    }

    /**
     * Verifica colisão entre este projétil e outro objeto
     */
    public boolean verificarColisao(Rectangle outro) {
        Rectangle bounds = new Rectangle(posX, posY, largura, altura);
        return bounds.intersects(outro);
    }

    // Getters e Setters
    
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int getPoder() {
        return poder;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }
    
    /**
     * Retorna os limites do projétil para cálculos de colisão
     */
    public Rectangle getBounds() {
        return new Rectangle(posX, posY, largura, altura);
    }
}
