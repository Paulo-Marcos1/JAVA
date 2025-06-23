package modelo;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * Classe que representa um inimigo no jogo.
 */
public class Inimigo {
    private int posX, posY;       // Posição do inimigo
    private int velocidade;       // Velocidade de movimento
    private int vida;             // Pontos de vida
    private int pontos;           // Valor em pontos ao ser destruído
    private int largura, altura;  // Dimensões para cálculo de colisão
    private Image imagem;         // Imagem que representa o inimigo
    private boolean visivel;      // Indica se o inimigo está visível/ativo

    /**
     * Construtor da classe Inimigo
     */
    public Inimigo(int posX, int posY, int velocidade, int vida, int pontos, int largura, int altura) {
        this.posX = posX;
        this.posY = posY;
        this.velocidade = velocidade;
        this.vida = vida;
        this.pontos = pontos;
        this.largura = largura;
        this.altura = altura;
        this.visivel = true;

        try {
            this.imagem = javax.imageio.ImageIO.read(new java.io.File("src/imagens/inimigo.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Move o inimigo na tela (normalmente para baixo)
     */
    public void mover() {
        this.posY += velocidade;
        
        // Se o inimigo sair da tela, torna-o invisível
        if (this.posY > 600) {
            this.visivel = false;
        }
    }

    /**
     * Verifica colisão entre este inimigo e outro objeto
     */
    public boolean verificarColisao(Rectangle outro) {
        Rectangle bounds = new Rectangle(posX, posY, largura, altura);
        return bounds.intersects(outro);
    }
    
    /**
     * Reduz a vida do inimigo quando sofre dano
     */
    public boolean sofrerDano(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
            this.visivel = false;
            return true; // Inimigo destruído
        }
        return false; // Inimigo ainda vivo
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

    public int getVida() {
        return vida;
    }

    public int getPontos() {
        return pontos;
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
     * Retorna os limites do inimigo para cálculos de colisão
     */
    public Rectangle getBounds() {
        return new Rectangle(posX, posY, largura, altura);
    }
}
