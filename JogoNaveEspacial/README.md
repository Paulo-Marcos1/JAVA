# Relatório Final - Jogo de Nave Espacial em Java

## Visão Geral
Este projeto implementa um jogo de nave espacial em Java, utilizando Programação Orientada a Objetos (POO), interface gráfica Swing e persistência de dados com JDBC, conforme solicitado na atividade prática.

## Estrutura do Projeto
O projeto está organizado nos seguintes pacotes:

1. **modelo**: Classes que representam os objetos do jogo
   - `NaveEspacial.java` (classe abstrata)
   - `NaveAtaque.java`
   - `NaveDefesa.java`
   - `NaveExploradora.java`
   - `Projetil.java`
   - `Inimigo.java`
   - `Obstaculo.java`
   - `Pontuacao.java`

2. **visao**: Classes relacionadas à interface gráfica
   - `JanelaPrincipal.java`
   - `TelaInicial.java`
   - `TelaSelecaoNave.java`
   - `TelaJogo.java`
   - `TelaRanking.java`

3. **controle**: Classes controladoras do jogo
   - `ControladorJogo.java`

4. **banco**: Classes para conexão e operações com o banco de dados
   - `ConexaoDB.java`
   - `PontuacaoDAO.java`
   - `ScriptBD.sql`

5. **Classe principal**
   - `Main.java`

## Requisitos Implementados

### 1. Estrutura POO
- Criada a classe abstrata `NaveEspacial` com atributos e métodos comuns
- Implementadas três classes filhas específicas:
  - `NaveAtaque`: Alta velocidade e poder de ataque
  - `NaveDefesa`: Maior resistência, menor velocidade
  - `NaveExploradora`: Velocidade média, sensores especiais (maior alcance)
- Aplicados conceitos de herança, polimorfismo (sobrescrita de métodos) e encapsulamento

### 2. Interface Gráfica com Swing
- Implementada janela principal (`JanelaPrincipal`) usando JFrame
- Criados painéis de jogo (`TelaJogo`) usando JPanel
- Adicionados componentes como botões, labels e tabelas
- Implementada animação dos elementos usando Timer
- Criada interface para seleção de nave e exibição de ranking

### 3. Persistência com JDBC
- Configurada conexão com banco de dados SQLite
- Criada tabela para armazenar pontuações dos jogadores
- Implementadas operações para:
  - Inserir pontuação ao final da partida
  - Recuperar e exibir as 5 maiores pontuações

### 4. Funcionalidades do Jogo
- Seleção entre 3 tipos de naves antes de iniciar
- Controle da nave com teclas direcionais
- Disparo de projéteis com a tecla espaço
- Aparecimento periódico de inimigos e obstáculos
- Sistema de colisão implementado
- Contador de pontos baseado em inimigos destruídos
- Registro da pontuação no banco de dados após o fim da partida

## Como Executar o Jogo
1. Certifique-se de ter o Java JDK instalado (versão 8 ou superior)
2. Adicione o driver JDBC do SQLite ao classpath (incluído no projeto)
3. Compile todas as classes do projeto
4. Execute a classe `Main`

## Controles do Jogo
- **Setas direcionais** ou **WASD**: Movimentam a nave
- **Espaço**: Dispara projéteis
- **ESC**: Pausa/continua o jogo

## Observações Técnicas
- O jogo utiliza SQLite como banco de dados por sua simplicidade e portabilidade
- A estrutura de classes segue o padrão MVC (Model-View-Controller)
- O sistema de colisão utiliza retângulos delimitadores (bounding boxes)
- A animação é controlada por um Timer que atualiza o estado do jogo a cada frame
