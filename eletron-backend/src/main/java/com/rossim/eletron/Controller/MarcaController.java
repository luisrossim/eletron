package com.rossim.eletron.Controller;

import com.rossim.eletron.DTO.MarcaDTO;
import com.rossim.eletron.Service.MarcaService;
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
@RequestMapping("/api/marca")
@RequiredArgsConstructor
@Tag(name = "MarcaController", description = "Gerenciamento de marcas")
public class MarcaController {

    private final MarcaService marcaService;

    @PostMapping
    @Operation(description = "Create de marcas")
    public ResponseEntity<MarcaDTO> create(@RequestBody @Valid MarcaDTO marcaDTO) {
        return new ResponseEntity<>(marcaService.create(marcaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update de marcas")
    public ResponseEntity<MarcaDTO> update(@PathVariable Long id, @RequestBody @Valid MarcaDTO marcaDTO) {
        MarcaDTO updatedMarca = marcaService.update(id, marcaDTO);
        return new ResponseEntity<>(updatedMarca, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete de marcas")
    public ResponseEntity<MarcaDTO> delete(@PathVariable Long id) {
        marcaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @Operation(description = "Pesquisar uma marca")
    public ResponseEntity<MarcaDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(marcaService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Pesquisar todas as marcas")
    public ResponseEntity<List<MarcaDTO>> findAll() {
        List<MarcaDTO> marcaDTOList = marcaService.findAll();
        return new ResponseEntity<>(marcaDTOList, marcaDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
