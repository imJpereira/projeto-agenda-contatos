CREATE TABLE contatos_tb (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado CHAR(2) NOT NULL,
    email VARCHAR(100) UNIQUE,
    categoria VARCHAR(20) NOT NULL CHECK (categoria IN ('Aluno', 'Responsável', 'Professor', 'Funcionário', 'Gestor'))
);
