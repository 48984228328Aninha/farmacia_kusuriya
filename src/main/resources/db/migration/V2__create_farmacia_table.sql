CREATE TABLE farmacia (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    horario_funcionamento VARCHAR(100),
    ativa BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE INDEX idx_farmacia_cnpj ON farmacia(cnpj);
CREATE INDEX idx_farmacia_ativa ON farmacia(ativa);
