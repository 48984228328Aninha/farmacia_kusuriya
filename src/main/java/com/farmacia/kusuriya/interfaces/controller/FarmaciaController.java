package com.farmacia.kusuriya.interfaces.controller;

import com.farmacia.kusuriya.application.usecase.ConsultarFarmaciaUseCase;
import com.farmacia.kusuriya.application.usecase.CriarFarmaciaUseCase;
import com.farmacia.kusuriya.domain.entity.Farmacia;
import com.farmacia.kusuriya.domain.valueobject.CNPJ;
import com.farmacia.kusuriya.domain.valueobject.Endereco;
import com.farmacia.kusuriya.interfaces.dto.EnderecoDTO;
import com.farmacia.kusuriya.interfaces.dto.FarmaciaDTO;
import com.farmacia.kusuriya.interfaces.dto.FarmaciaResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/farmacias")
public class FarmaciaController {
    
    private final CriarFarmaciaUseCase criarFarmaciaUseCase;
    private final ConsultarFarmaciaUseCase consultarFarmaciaUseCase;

    public FarmaciaController(CriarFarmaciaUseCase criarFarmaciaUseCase, 
                             ConsultarFarmaciaUseCase consultarFarmaciaUseCase) {
        this.criarFarmaciaUseCase = criarFarmaciaUseCase;
        this.consultarFarmaciaUseCase = consultarFarmaciaUseCase;
    }

    @PostMapping
    public ResponseEntity<FarmaciaResponseDTO> criar(@RequestBody FarmaciaDTO dto) {
        CNPJ cnpj = new CNPJ(dto.cnpj());
        Endereco endereco = new Endereco(
            dto.endereco().rua(),
            dto.endereco().numero(),
            dto.endereco().complemento(),
            dto.endereco().bairro(),
            dto.endereco().cidade(),
            dto.endereco().estado(),
            dto.endereco().cep()
        );
        
        Farmacia farmacia = new Farmacia(
            dto.nome(),
            cnpj,
            endereco,
            dto.telefone(),
            dto.email(),
            dto.horarioFuncionamento()
        );
        
        Farmacia salva = criarFarmaciaUseCase.executar(farmacia);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(salva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmaciaResponseDTO> buscarPorId(@PathVariable Long id) {
        Farmacia farmacia = consultarFarmaciaUseCase.buscarPorId(id);
        return ResponseEntity.ok(toResponseDTO(farmacia));
    }

    @GetMapping
    public ResponseEntity<List<FarmaciaResponseDTO>> listarTodas() {
        List<Farmacia> farmacias = consultarFarmaciaUseCase.listarTodas();
        List<FarmaciaResponseDTO> response = farmacias.stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<FarmaciaResponseDTO>> listarAtivas() {
        List<Farmacia> farmacias = consultarFarmaciaUseCase.listarAtivas();
        List<FarmaciaResponseDTO> response = farmacias.stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private FarmaciaResponseDTO toResponseDTO(Farmacia farmacia) {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
            farmacia.getEndereco().getRua(),
            farmacia.getEndereco().getNumero(),
            farmacia.getEndereco().getComplemento(),
            farmacia.getEndereco().getBairro(),
            farmacia.getEndereco().getCidade(),
            farmacia.getEndereco().getEstado(),
            farmacia.getEndereco().getCep()
        );
        
        return new FarmaciaResponseDTO(
            farmacia.getId(),
            farmacia.getNome(),
            farmacia.getCnpj().getValor(),
            enderecoDTO,
            farmacia.getTelefone(),
            farmacia.getEmail(),
            farmacia.getHorarioFuncionamento(),
            farmacia.isAtiva()
        );
    }
}
