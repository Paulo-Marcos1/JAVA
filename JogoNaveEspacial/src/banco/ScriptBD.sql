-- Script para criação da tabela de pontuações no banco de dados SQLite

-- Cria a tabela de pontuações se não existir
CREATE TABLE IF NOT EXISTS pontuacoes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome_jogador TEXT NOT NULL,
    pontuacao INTEGER NOT NULL,
    data DATE NOT NULL
);
