CREATE DATABASE exemplo_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE cliente (
id SERIAL PRIMARY KEY,
nome VARCHAR(100),
email VARCHAR(100),
idade INTEGER
);

#SEGUNDO SCRIPT

INSERT INTO cliente (nome, email, idade) VALUES
('Alice Santos', 'alice@email.com', 30),
('Bruno Lima', 'bruno@email.com', 25),
('Carla Souza', 'carla@email.com', 35);

SELECT *
FROM cliente

SELECT *
FROM cliente
WHERE (idade > 24)
