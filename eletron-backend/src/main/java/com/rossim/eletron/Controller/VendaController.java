package com.rossim.eletron.Controller;

import com.rossim.eletron.DTO.VendaDTO;
import com.rossim.eletron.Service.VendaService;
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
@RequestMapping("/api/venda")
@RequiredArgsConstructor
@Tag(name = "VendaController", description = "Gerenciamento de vendas")
public class VendaController {

    private final VendaService vendaService;

    @PostMapping
    @Operation(description = "Create de vendas")
    public ResponseEntity<VendaDTO> create(@RequestBody @Valid VendaDTO vendaDTO) {
        return new ResponseEntity<>(vendaService.create(vendaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update de vendas")
    public ResponseEntity<VendaDTO> update(@PathVariable Long id, @RequestBody @Valid VendaDTO vendaDTO) {
        VendaDTO updatedVenda = vendaService.update(id, vendaDTO);
        return new ResponseEntity<>(updatedVenda, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete de vendas")
    public ResponseEntity<VendaDTO> delete(@PathVariable Long id) {
        vendaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @Operation(description = "Pesquisar uma venda")
    public ResponseEntity<VendaDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(vendaService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Pesquisar todas as vendas")
    public ResponseEntity<List<VendaDTO>> findAll() {
        List<VendaDTO> vendaDTOList = vendaService.findAll();
        return new ResponseEntity<>(vendaDTOList, vendaDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
