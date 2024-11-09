package com.rossim.eletron.Controller;

import com.rossim.eletron.DTO.ReformadoDTO;
import com.rossim.eletron.Service.ReformadoService;
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
@RequestMapping("/api/reformado")
@RequiredArgsConstructor
@Tag(name = "ReformadoController", description = "Gerenciamento de reformados")
public class ReformadoController {

    private final ReformadoService reformadoService;

    @PostMapping
    @Operation(description = "Create de reformados")
    public ResponseEntity<ReformadoDTO> create(@RequestBody @Valid ReformadoDTO reformadoDTO) {
        return new ResponseEntity<>(reformadoService.create(reformadoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update de reformados")
    public ResponseEntity<ReformadoDTO> update(@PathVariable Long id, @RequestBody @Valid ReformadoDTO reformadoDTO) {
        ReformadoDTO updatedReformado = reformadoService.update(id, reformadoDTO);
        return new ResponseEntity<>(updatedReformado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete de reformados")
    public ResponseEntity<ReformadoDTO> delete(@PathVariable Long id) {
        reformadoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @Operation(description = "Pesquisar um reformado")
    public ResponseEntity<ReformadoDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(reformadoService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Pesquisar todos os reformados")
    public ResponseEntity<List<ReformadoDTO>> findAll() {
        List<ReformadoDTO> reformadoDTOList = reformadoService.findAll();
        return new ResponseEntity<>(reformadoDTOList, reformadoDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
