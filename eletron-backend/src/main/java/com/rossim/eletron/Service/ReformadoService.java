package com.rossim.eletron.Service;

import com.rossim.eletron.DTO.ReformadoDTO;
import com.rossim.eletron.Exception.RecordNotFoundException;
import com.rossim.eletron.Model.Marca;
import com.rossim.eletron.Model.Reformado;
import com.rossim.eletron.Repository.MarcaRepository;
import com.rossim.eletron.Repository.ReformadoRepository;
import com.rossim.eletron.Mapper.ReformadoMapper;
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
public class ReformadoService {

    private final ReformadoRepository reformadoRepository;
    private final MarcaRepository marcaRepository;
    private final ReformadoMapper reformadoMapper;

    public ReformadoDTO create(@Valid ReformadoDTO reformadoDTO) {
        Reformado reformado = reformadoMapper.toEntity(reformadoDTO);
        Reformado savedReformado = reformadoRepository.save(reformado);
        return reformadoMapper.toDTO(savedReformado);
    }

    public ReformadoDTO update(Long id, @Valid ReformadoDTO reformadoDTO) {
        return reformadoRepository.findById(id)
                .map(registrobusca -> {
                    BeanUtils.copyProperties(reformadoDTO, registrobusca, "id", "criadoEm");

                    Marca marca = marcaRepository.findById(reformadoDTO.marca().id())
                            .orElseThrow(() -> new RecordNotFoundException(reformadoDTO.marca().id(), "marca"));

                    registrobusca.setMarca(marca);
                    return reformadoMapper.toDTO(reformadoRepository.save(registrobusca));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable Long id){
        Reformado reformado = reformadoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        reformadoRepository.delete(reformado);
    }

    public List<ReformadoDTO> findAll() {
        return reformadoRepository.findAll().stream()
                .map(reformadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReformadoDTO findById(@PathVariable Long id) {
        return reformadoRepository.findById(id)
                .map(reformadoMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }
}