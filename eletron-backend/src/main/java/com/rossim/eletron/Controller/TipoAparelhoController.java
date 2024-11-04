package com.rossim.eletron.Controller;

import com.rossim.eletron.DTO.TipoAparelhoDTO;
import com.rossim.eletron.Service.TipoAparelhoService;
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
@RequestMapping("/api/tipo-aparelho")
@RequiredArgsConstructor
@Tag(name = "TipoAparelhoController", description = "Gerenciamento de tipoAparelhos")
public class TipoAparelhoController {

    private final TipoAparelhoService tipoAparelhoService;

    @PostMapping
    @Operation(description = "Create de tipoAparelhos")
    public ResponseEntity<TipoAparelhoDTO> create(@RequestBody @Valid TipoAparelhoDTO tipoAparelhoDTO) {
        return new ResponseEntity<>(tipoAparelhoService.create(tipoAparelhoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update de tipoAparelhos")
    public ResponseEntity<TipoAparelhoDTO> update(@PathVariable Long id, @RequestBody @Valid TipoAparelhoDTO tipoAparelhoDTO) {
        TipoAparelhoDTO updatedTipoAparelho = tipoAparelhoService.update(id, tipoAparelhoDTO);
        return new ResponseEntity<>(updatedTipoAparelho, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete de tipoAparelhos")
    public ResponseEntity<TipoAparelhoDTO> delete(@PathVariable Long id) {
        tipoAparelhoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @Operation(description = "Pesquisar um tipoAparelho")
    public ResponseEntity<TipoAparelhoDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(tipoAparelhoService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Pesquisar todos os tipoAparelhos")
    public ResponseEntity<List<TipoAparelhoDTO>> findAll() {
        List<TipoAparelhoDTO> tipoAparelhoDTOList = tipoAparelhoService.findAll();
        return new ResponseEntity<>(tipoAparelhoDTOList, tipoAparelhoDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
