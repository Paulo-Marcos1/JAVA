package modelo;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * Classe que representa um obstáculo no jogo.
 * Obstáculos são objetos que a nave deve evitar.
 */
public class Obstaculo {
    private int posX, posY;       // Posição do obstáculo
    private int velocidade;       // Velocidade de movimento
    private int largura, altura;  // Dimensões para cálculo de colisão
    private Image imagem;         // Imagem que representa o obstáculo
    private boolean visivel;      // Indica se o obstáculo está visível/ativo


    public Obstaculo(int posX, int posY, int velocidade, int largura, int altura) {
        this.posX = posX;
        this.posY = posY;
        this.velocidade = velocidade;
        this.largura = largura;
        this.altura = altura;
        this.visivel = true;

        try {
            this.imagem = javax.imageio.ImageIO.read(new java.io.File("src/imagens/obstaculo.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Move o obstáculo na tela (normalmente para baixo)
     */
    public void mover() {
        this.posY += velocidade;
        
        // Se o obstáculo sair da tela, torna-o invisível
        if (this.posY > 600) {
            this.visivel = false;
        }
    }

    /**
     * Verifica colisão entre este obstáculo e outro objeto
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
     * Retorna os limites do obstáculo para cálculos de colisão
     */
    public Rectangle getBounds() {
        return new Rectangle(posX, posY, largura, altura);
    }
}
