package com.farmacia.kusuriya.interfaces.controller;

import com.farmacia.kusuriya.application.usecase.CriarPacienteUseCase;
import com.farmacia.kusuriya.domain.entity.Paciente;
import com.farmacia.kusuriya.domain.valueobject.CPF;
import com.farmacia.kusuriya.domain.valueobject.Endereco;
import com.farmacia.kusuriya.interfaces.dto.PacienteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    
    private final CriarPacienteUseCase criarPacienteUseCase;

    public PacienteController(CriarPacienteUseCase criarPacienteUseCase) {
        this.criarPacienteUseCase = criarPacienteUseCase;
    }

    @PostMapping
    public ResponseEntity<Long> criar(@RequestBody PacienteDTO dto) {
        CPF cpf = new CPF(dto.cpf());
        Endereco endereco = new Endereco(
            dto.endereco().rua(),
            dto.endereco().numero(),
            dto.endereco().complemento(),
            dto.endereco().bairro(),
            dto.endereco().cidade(),
            dto.endereco().estado(),
            dto.endereco().cep()
        );
        
        Paciente paciente = new Paciente(dto.nome(), cpf, dto.email(), dto.telefone(), endereco);
        Paciente salvo = criarPacienteUseCase.executar(paciente);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo.getId());
    }
}
