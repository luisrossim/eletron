package com.rossim.eletron.Service;

import com.rossim.eletron.DTO.MarcaDTO;
import com.rossim.eletron.Exception.RecordNotFoundException;
import com.rossim.eletron.Model.Marca;
import com.rossim.eletron.Repository.MarcaRepository;
import com.rossim.eletron.Mapper.MarcaMapper;
import com.rossim.eletron.Utils.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RequiredArgsConstructor
@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    public MarcaDTO create(@Valid MarcaDTO marcaDTO) {
        Marca marca = marcaMapper.toEntity(marcaDTO);
        Marca savedMarca = marcaRepository.save(marca);
        return marcaMapper.toDTO(savedMarca);
    }

    public MarcaDTO update(Long id, @Valid MarcaDTO marcaDTO) {
        return marcaRepository.findById(id)
                .map(registrobusca -> {
                    BeanUtils.copyProperties(marcaDTO, registrobusca, "id");
                    return marcaMapper.toDTO(marcaRepository.save(registrobusca));
                })
                .orElseThrow(() -> new RecordNotFoundException(Constants.MARCA_NOT_FOUND));
    }

    public void delete(@PathVariable Long id){
        Marca marca = marcaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(Constants.MARCA_NOT_FOUND));
        marcaRepository.delete(marca);
    }

    public List<MarcaDTO> findAll() {
        return marcaRepository.findAll().stream()
                .map(marcaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MarcaDTO findById(@PathVariable Long id) {
        return marcaRepository.findById(id)
                .map(marcaMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(Constants.MARCA_NOT_FOUND));
    }
}
