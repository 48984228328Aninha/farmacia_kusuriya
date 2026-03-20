ALTER TABLE medico ADD COLUMN telefone VARCHAR(20);
ALTER TABLE medico ADD COLUMN email VARCHAR(255);

CREATE INDEX idx_medico_especialidade ON medico(especialidade);
