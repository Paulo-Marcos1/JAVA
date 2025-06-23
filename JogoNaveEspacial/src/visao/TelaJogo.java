package visao;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.NaveEspacial;
import modelo.NaveAtaque;
import modelo.NaveDefesa;
import modelo.NaveExploradora;
import modelo.Projetil;
import modelo.Inimigo;
import modelo.Obstaculo;
import controle.ControladorJogo;
/**
 * Classe que representa a tela de jogo.
 * Responsável por renderizar e controlar o jogo.
 */
public class TelaJogo extends JPanel implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private JanelaPrincipal janelaPrincipal;
    private NaveEspacial nave;
    private List<Projetil> projeteis;
    private List<Inimigo> inimigos;
    private List<Obstaculo> obstaculos;
    private Timer timer;
    private int pontuacao;
    private int contadorPontuacao;
    private boolean jogando;
    private Random random;
    private ControladorJogo controlador;
    
    // Direções de movimento
    private boolean moveCima;
    private boolean moveBaixo;
    private boolean moveEsquerda;
    private boolean moveDireita;
    
    /**
     * Construtor da classe TelaJogo
     * 
     * @param janelaPrincipal Referência para a janela principal
     * @param tipoNave Tipo de nave escolhida pelo jogador
     */
    public TelaJogo(JanelaPrincipal janelaPrincipal, String tipoNave) {
        this.janelaPrincipal = janelaPrincipal;
        setName(JanelaPrincipal.TELA_JOGO);
        setBackground(Color.BLACK);
        setFocusable(true);
        
        // Inicializa listas e variáveis
        projeteis = new ArrayList<>();
        inimigos = new ArrayList<>();
        obstaculos = new ArrayList<>();
        pontuacao = 0;
        jogando = true;
        random = new Random();

        
        // Cria a nave escolhida
        criarNave(tipoNave);
        
        // Configura o controlador
        controlador = new ControladorJogo(this);
        
        // Configura o timer para atualização do jogo (60 FPS)
        timer = new Timer(1000/60, this);
        
        // Adiciona o listener de teclado
        addKeyListener(new TecladoAdapter());
        
        // Inicia o jogo
        iniciarJogo();
    }
    
    /**
     * Cria a nave de acordo com o tipo escolhido
     * 
     * @param tipoNave Tipo de nave escolhida
     */
    private void criarNave(String tipoNave) {
        // Posição inicial da nave
        int posX = 400 - 20; // Centro da tela (largura/2 - largura_nave/2)
        int posY = 500;      // Próximo à parte inferior
        
        // Cria a nave de acordo com o tipo
        switch (tipoNave) {
            case "ataque":
                nave = new NaveAtaque(posX, posY);
                break;
            case "defesa":
                nave = new NaveDefesa(posX, posY);
                break;
            case "exploradora":
                nave = new NaveExploradora(posX, posY);
                break;
            default:
                nave = new NaveAtaque(posX, posY); // Padrão
        }
    }
    
    /**
     * Inicia o jogo
     */
    private void iniciarJogo() {
        jogando = true;
        timer.start();
    }
    
    /**
     * Finaliza o jogo
     */
    public void finalizarJogo() {
        jogando = false;
        timer.stop();
        
        // Salva a pontuação no banco de dados
        controlador.salvarPontuacao(pontuacao);
        
        // Volta para a tela inicial
        janelaPrincipal.mostrarTela(JanelaPrincipal.TELA_INICIAL);
    }
    
    /**
     * Atualiza o estado do jogo a cada tick do timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (jogando) {
            // Atualiza a posição da nave
            atualizarNave();
            
            // Atualiza os projéteis
            atualizarProjeteis();
            
            // Atualiza os inimigos
            atualizarInimigos();
            
            // Atualiza os obstáculos
            atualizarObstaculos();
            
            // Verifica colisões
            verificarColisoes();
            
            // Gera novos inimigos e obstáculos
            gerarInimigosEObstaculos();
        }
        
        // Redesenha a tela
        repaint();
    }
    
    /**
     * Atualiza a posição da nave com base nas teclas pressionadas
     */
    private void atualizarNave() {
        int direcaoX = 0;
        int direcaoY = 0;
        
        if (moveEsquerda) direcaoX = -1;
        if (moveDireita) direcaoX = 1;
        if (moveCima) direcaoY = -1;
        if (moveBaixo) direcaoY = 1;
        
        nave.mover(direcaoX, direcaoY);
    }
    
    /**
     * Atualiza os projéteis, movendo-os e removendo os invisíveis
     */
    private void atualizarProjeteis() {
        // Move todos os projéteis
        for (int i = 0; i < projeteis.size(); i++) {
            Projetil projetil = projeteis.get(i);
            projetil.mover();
            
            // Remove projéteis invisíveis
            if (!projetil.isVisivel()) {
                projeteis.remove(i);
                i--;
            }
        }
    }
    
    /**
     * Atualiza os inimigos, movendo-os e removendo os invisíveis
     */
    private void atualizarInimigos() {
        // Move todos os inimigos
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            inimigo.mover();
            
            // Remove inimigos invisíveis
            if (!inimigo.isVisivel()) {
                inimigos.remove(i);
                i--;
            }
        }
    }
    
    /**
     * Atualiza os obstáculos, movendo-os e removendo os invisíveis
     */
    private void atualizarObstaculos() {
        // Move todos os obstáculos
        for (int i = 0; i < obstaculos.size(); i++) {
            Obstaculo obstaculo = obstaculos.get(i);
            obstaculo.mover();
            
            // Remove obstáculos invisíveis
            if (!obstaculo.isVisivel()) {
                obstaculos.remove(i);
                i--;
            }
        }
    }
    
    /**
     * Verifica colisões entre os elementos do jogo
     */
    private void verificarColisoes() {
        // Obtém os limites da nave
        Rectangle naveRect = nave.getBounds();
        
        // Verifica colisão entre projéteis e inimigos
        for (int i = 0; i < projeteis.size(); i++) {
            Projetil projetil = projeteis.get(i);
            Rectangle projetilRect = projetil.getBounds();
            
            for (int j = 0; j < inimigos.size(); j++) {
                Inimigo inimigo = inimigos.get(j);
                
                if (projetil.verificarColisao(inimigo.getBounds())) {
                    // Inimigo atingido
                    if (inimigo.sofrerDano(projetil.getPoder())) {
                        // Inimigo destruído
                        pontuacao += inimigo.getPontos();
                    }
                    
                    // Remove o projétil
                    projetil.setVisivel(false);
                    break;
                }
            }
        }

        if (nave.isVisivel()){
            contadorPontuacao++;
            if (contadorPontuacao >= 60) { // Aproximadamente 1 segundo (60 frames)
                pontuacao += 2;
                contadorPontuacao = 0; // Reinicia o contador
            }
        }
        // Verifica colisão entre nave e inimigos
        for (Inimigo inimigo : inimigos) {
            if (nave.verificarColisao(inimigo.getBounds())) {
                // Nave atingida por inimigo
                nave.sofrerDano(10);
                inimigo.setVisivel(false);
                
                // Verifica se a nave foi destruída
                if (!nave.isVisivel()) {
                    finalizarJogo();
                    return;
                }
            }
        }
        
        // Verifica colisão entre nave e obstáculos
        for (Obstaculo obstaculo : obstaculos) {
            if (nave.verificarColisao(obstaculo.getBounds())) {
                // Nave atingida por obstáculo
                nave.sofrerDano(20);
                obstaculo.setVisivel(false);
                
                // Verifica se a nave foi destruída
                if (!nave.isVisivel()) {
                    finalizarJogo();
                    return;
                }
            }
        }
    }
    
    /**
     * Gera novos inimigos e obstáculos aleatoriamente
     */
    private void gerarInimigosEObstaculos() {
        // Chance de gerar um novo inimigo (2%)
        if (random.nextInt(100) < 2) {
            int posX = random.nextInt(750); // Posição X aleatória
            int velocidade = 1 + random.nextInt(3); // Velocidade entre 1 e 3
            int vida = 10 + random.nextInt(20); // Vida entre 10 e 30
            int pontos = 10 + random.nextInt(20); // Pontos entre 10 e 30
            
            Inimigo novoInimigo = new Inimigo(posX, 0, velocidade, vida, pontos, 40, 40);
            inimigos.add(novoInimigo);
        }
        
        // Chance de gerar um novo obstáculo (1%)
        if (random.nextInt(100) < 1) {
            int posX = random.nextInt(750); // Posição X aleatória
            int velocidade = 2 + random.nextInt(4); // Velocidade entre 2 e 5
            
            Obstaculo novoObstaculo = new Obstaculo(posX, 0, velocidade, 30, 30);
            obstaculos.add(novoObstaculo);
        }
    }
    
    /**
     * Adiciona um novo projétil à lista
     */
    public void adicionarProjetil(Projetil projetil) {
        projeteis.add(projetil);
    }
    
    /**
     * Desenha todos os elementos do jogo
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenha o fundo
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Desenha a nave
        if (nave.isVisivel()) {
            if (nave.getImagem() != null) {
                g2d.drawImage(nave.getImagem(), nave.getPosX(), nave.getPosY(), nave.getLargura(), nave.getAltura(), this);
            } else {
                // Fallback para retângulo colorido se a imagem não for carregada
                g2d.setColor(Color.GREEN);
                g2d.fillRect(nave.getPosX(), nave.getPosY(), nave.getLargura(), nave.getAltura());
            }
        }

        // Desenha os projéteis
        for (Projetil projetil : projeteis) {
            if (projetil.isVisivel()) {
                if (projetil.getImagem() != null) {
                    g2d.drawImage(projetil.getImagem(), projetil.getPosX(), projetil.getPosY(),
                            projetil.getLargura(), projetil.getAltura(), this);
                } else {
                    g2d.setColor(Color.YELLOW);
                    g2d.fillRect(projetil.getPosX(), projetil.getPosY(), projetil.getLargura(), projetil.getAltura());
                }
            }
        }

        // Desenha os inimigos
        for (Inimigo inimigo : inimigos) {
            if (inimigo.isVisivel()) {
                if (inimigo.getImagem() != null) {
                    g2d.drawImage(inimigo.getImagem(), inimigo.getPosX(), inimigo.getPosY(),
                            inimigo.getLargura(), inimigo.getAltura(), this);
                } else {
                    g2d.setColor(Color.RED);
                    g2d.fillRect(inimigo.getPosX(), inimigo.getPosY(), inimigo.getLargura(), inimigo.getAltura());
                }
            }
        }

        // Desenha os obstáculos
        for (Obstaculo obstaculo : obstaculos) {
            if (obstaculo.isVisivel()) {
                if (obstaculo.getImagem() != null) {
                    g2d.drawImage(obstaculo.getImagem(), obstaculo.getPosX(), obstaculo.getPosY(),
                            obstaculo.getLargura(), obstaculo.getAltura(), this);
                } else {
                    g2d.setColor(Color.GRAY);
                    g2d.fillRect(obstaculo.getPosX(), obstaculo.getPosY(), obstaculo.getLargura(), obstaculo.getAltura());
                }
            }
        }

        // Desenha a pontuação e vida (mantém como está)
        g2d.setColor(Color.WHITE);
        g2d.drawString("Pontuação: " + pontuacao, 10, 20);
        g2d.drawString("Vida: " + nave.getVida(), 10, 40);
    }


    /**
     * Classe interna para tratar eventos de teclado
     */
    private class TecladoAdapter extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    moveEsquerda = true;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    moveDireita = true;
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    moveCima = true;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    moveBaixo = true;
                    break;
                case KeyEvent.VK_SPACE:
                    // Atira
                    if (jogando) {
                        Projetil projetil = nave.atirar();
                        projeteis.add(projetil);
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    // Pausa o jogo
                    jogando = !jogando;
                    if (jogando) {
                        timer.start();
                    } else {
                        timer.stop();
                    }
                    break;
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    moveEsquerda = false;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    moveDireita = false;
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    moveCima = false;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    moveBaixo = false;
                    break;
            }
        }
    }
    
    // Getters e Setters
    
    public NaveEspacial getNave() {
        return nave;
    }
    
    public List<Projetil> getProjeteis() {
        return projeteis;
    }
    
    public List<Inimigo> getInimigos() {
        return inimigos;
    }
    
    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }
    
    public int getPontuacao() {
        return pontuacao;
    }
    
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    public boolean isJogando() {
        return jogando;
    }
    
    public void setJogando(boolean jogando) {
        this.jogando = jogando;
    }
}
