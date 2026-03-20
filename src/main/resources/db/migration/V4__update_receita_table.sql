ALTER TABLE receita ADD COLUMN data_validacao TIMESTAMP;
ALTER TABLE receita ADD COLUMN observacoes TEXT;

CREATE INDEX idx_receita_paciente ON receita(paciente_id);
CREATE INDEX idx_receita_validada ON receita(validada);
CREATE INDEX idx_receita_data_emissao ON receita(data_emissao);
