CREATE TABLE paciente (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(8) NOT NULL
);

CREATE TABLE medico (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    crm_numero VARCHAR(20) NOT NULL,
    crm_estado VARCHAR(2) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    UNIQUE(crm_numero, crm_estado)
);

CREATE TABLE medicamento (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    principio_ativo VARCHAR(255) NOT NULL,
    dosagem VARCHAR(50) NOT NULL,
    controlado BOOLEAN NOT NULL
);

CREATE TABLE receita (
    id BIGSERIAL PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    url_arquivo VARCHAR(500) NOT NULL,
    data_emissao TIMESTAMP NOT NULL,
    validada BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (medico_id) REFERENCES medico(id)
);

CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    receita_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (receita_id) REFERENCES receita(id)
);

CREATE TABLE pedido_medicamento (
    pedido_id BIGINT NOT NULL,
    medicamento_id BIGINT NOT NULL,
    PRIMARY KEY (pedido_id, medicamento_id),
    FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    FOREIGN KEY (medicamento_id) REFERENCES medicamento(id)
);

CREATE TABLE entrega (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT UNIQUE NOT NULL,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_entrega TIMESTAMP,
    FOREIGN KEY (pedido_id) REFERENCES pedido(id)
);
