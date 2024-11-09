package com.rossim.eletron.Controller;

import com.rossim.eletron.DTO.PagamentoFormaDTO;
import com.rossim.eletron.Service.PagamentoFormaService;
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
@RequestMapping("/api/pagamento-forma")
@RequiredArgsConstructor
@Tag(name = "PagamentoFormaController", description = "Gerenciamento de formas de pagamento")
public class PagamentoFormaController {

    private final PagamentoFormaService pagamentoFormaService;

    @PostMapping
    @Operation(description = "Create de formas de pagamento")
    public ResponseEntity<PagamentoFormaDTO> create(@RequestBody @Valid PagamentoFormaDTO pagamentoFormaDTO) {
        return new ResponseEntity<>(pagamentoFormaService.create(pagamentoFormaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update de formas de pagamento")
    public ResponseEntity<PagamentoFormaDTO> update(@PathVariable Long id, @RequestBody @Valid PagamentoFormaDTO pagamentoFormaDTO) {
        PagamentoFormaDTO updatedPagamentoForma = pagamentoFormaService.update(id, pagamentoFormaDTO);
        return new ResponseEntity<>(updatedPagamentoForma, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete de formas de pagamento")
    public ResponseEntity<PagamentoFormaDTO> delete(@PathVariable Long id) {
        pagamentoFormaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @Operation(description = "Pesquisar uma forma de pagamento")
    public ResponseEntity<PagamentoFormaDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(pagamentoFormaService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Pesquisar todas as formas de pagamento")
    public ResponseEntity<List<PagamentoFormaDTO>> findAll() {
        List<PagamentoFormaDTO> pagamentoFormaDTOList = pagamentoFormaService.findAll();
        return new ResponseEntity<>(pagamentoFormaDTOList, pagamentoFormaDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
