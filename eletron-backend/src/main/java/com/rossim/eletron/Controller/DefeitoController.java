package com.rossim.eletron.Controller;

import com.rossim.eletron.DTO.DefeitoDTO;
import com.rossim.eletron.Service.DefeitoService;
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
@RequestMapping("/api/defeito")
@RequiredArgsConstructor
@Tag(name = "DefeitoController", description = "Gerenciamento de defeitos")
public class DefeitoController {

    private final DefeitoService defeitoService;

    @PostMapping
    @Operation(description = "Create de defeitos")
    public ResponseEntity<DefeitoDTO> create(@RequestBody @Valid DefeitoDTO defeitoDTO) {
        return new ResponseEntity<>(defeitoService.create(defeitoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update de defeitos")
    public ResponseEntity<DefeitoDTO> update(@PathVariable Long id, @RequestBody @Valid DefeitoDTO defeitoDTO) {
        DefeitoDTO updatedDefeito = defeitoService.update(id, defeitoDTO);
        return new ResponseEntity<>(updatedDefeito, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete de defeitos")
    public ResponseEntity<DefeitoDTO> delete(@PathVariable Long id) {
        defeitoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @Operation(description = "Pesquisar um defeito")
    public ResponseEntity<DefeitoDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(defeitoService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Pesquisar todos os defeitos")
    public ResponseEntity<List<DefeitoDTO>> findAll() {
        List<DefeitoDTO> defeitoDTOList = defeitoService.findAll();
        return new ResponseEntity<>(defeitoDTOList, defeitoDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
