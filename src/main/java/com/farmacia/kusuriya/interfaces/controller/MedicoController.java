package com.farmacia.kusuriya.interfaces.controller;

import com.farmacia.kusuriya.application.usecase.ConsultarMedicoUseCase;
import com.farmacia.kusuriya.application.usecase.CriarMedicoUseCase;
import com.farmacia.kusuriya.domain.entity.Medico;
import com.farmacia.kusuriya.domain.valueobject.CRM;
import com.farmacia.kusuriya.interfaces.dto.MedicoDTO;
import com.farmacia.kusuriya.interfaces.dto.MedicoResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    private final CriarMedicoUseCase criarMedicoUseCase;
    private final ConsultarMedicoUseCase consultarMedicoUseCase;

    public MedicoController(CriarMedicoUseCase criarMedicoUseCase, 
                           ConsultarMedicoUseCase consultarMedicoUseCase) {
        this.criarMedicoUseCase = criarMedicoUseCase;
        this.consultarMedicoUseCase = consultarMedicoUseCase;
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> criar(@RequestBody MedicoDTO dto) {
        CRM crm = new CRM(dto.crmNumero(), dto.crmEstado());
        
        Medico medico = new Medico(
            dto.nome(),
            crm,
            dto.especialidade(),
            dto.telefone(),
            dto.email()
        );
        
        Medico salvo = criarMedicoUseCase.executar(medico);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(salvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> buscarPorId(@PathVariable Long id) {
        Medico medico = consultarMedicoUseCase.buscarPorId(id);
        return ResponseEntity.ok(toResponseDTO(medico));
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> listarTodos() {
        List<Medico> medicos = consultarMedicoUseCase.listarTodos();
        List<MedicoResponseDTO> response = medicos.stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<MedicoResponseDTO>> listarPorEspecialidade(@PathVariable String especialidade) {
        List<Medico> medicos = consultarMedicoUseCase.listarPorEspecialidade(especialidade);
        List<MedicoResponseDTO> response = medicos.stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private MedicoResponseDTO toResponseDTO(Medico medico) {
        return new MedicoResponseDTO(
            medico.getId(),
            medico.getNome(),
            medico.getCrm().getNumero(),
            medico.getCrm().getEstado(),
            medico.getEspecialidade(),
            medico.getTelefone(),
            medico.getEmail()
        );
    }
}
