package modelo;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * Classe abstrata que representa uma nave espacial genérica.
 * Serve como base para todos os tipos específicos de naves no jogo.
 */
public abstract class NaveEspacial {
    // Atributos protegidos para acesso nas subclasses
    protected int posX, posY;         // Posição da nave no espaço de jogo
    protected int velocidade;         // Velocidade de movimento da nave
    protected int vida;               // Pontos de vida da nave
    protected int largura, altura;    // Dimensões para cálculo de colisão
    protected Image imagem;           // Imagem que representa a nave visualmente
    protected boolean visivel;        // Indica se a nave está visível/ativa no jogo

    /**
     * Construtor da classe NaveEspacial
     */
    public NaveEspacial(int posX, int posY, int velocidade, int vida, int largura, int altura) {
        this.posX = posX;
        this.posY = posY;
        this.velocidade = velocidade;
        this.vida = vida;
        this.largura = largura;
        this.altura = altura;
        this.visivel = true;
    }

    /**
     * Método abstrato para mover a nave
     * Cada tipo de nave implementará sua própria lógica de movimento

     */
    public abstract void mover(int direcaoX, int direcaoY);

    /**
     * Método abstrato para a nave atirar
     * Cada tipo de nave implementará seu próprio tipo de projétil

     */
    public abstract Projetil atirar();

    /**
     * Verifica colisão entre esta nave e outro objeto
     */
    public boolean verificarColisao(Rectangle outro) {
        Rectangle bounds = new Rectangle(posX, posY, largura, altura);
        return bounds.intersects(outro);
    }

    /**
     * Reduz a vida da nave quando sofre dano
     * 
     * @param dano Quantidade de dano a ser aplicada
     */
    public void sofrerDano(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
            this.visivel = false;
        }
    }

    // Getters e Setters
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
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
     * Retorna os limites da nave para cálculos de colisão
     */
    public Rectangle getBounds() {
        return new Rectangle(posX, posY, largura, altura);
    }
}
