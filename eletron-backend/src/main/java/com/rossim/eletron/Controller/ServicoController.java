package com.rossim.eletron.Controller;

import com.rossim.eletron.DTO.ServicoDTO;
import com.rossim.eletron.Service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/servico")
@RequiredArgsConstructor
@Tag(name = "ServicoController", description = "Gerenciamento de servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    @Operation(description = "Create de servicos")
    public ResponseEntity<ServicoDTO> create(@RequestBody @Valid ServicoDTO servicoDTO) {
        return new ResponseEntity<>(servicoService.create(servicoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update de servicos")
    public ResponseEntity<ServicoDTO> update(@PathVariable Long id, @RequestBody @Valid ServicoDTO servicoDTO) {
        ServicoDTO updatedServico = servicoService.update(id, servicoDTO);
        return new ResponseEntity<>(updatedServico, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete de servicos")
    public ResponseEntity<ServicoDTO> delete(@PathVariable Long id) {
        servicoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @Operation(description = "Pesquisar um servico")
    public ResponseEntity<ServicoDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(servicoService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Pesquisar todos os servicos")
    public ResponseEntity<List<ServicoDTO>> findAll() {
        List<ServicoDTO> servicoDTOList = servicoService.findAll();
        return new ResponseEntity<>(servicoDTOList, servicoDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
