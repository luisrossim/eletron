package com.rossim.eletron.Service;

import com.rossim.eletron.DTO.DefeitoDTO;
import com.rossim.eletron.Exception.RecordNotFoundException;
import com.rossim.eletron.Model.Defeito;
import com.rossim.eletron.Repository.DefeitoRepository;
import com.rossim.eletron.Mapper.DefeitoMapper;
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
public class DefeitoService {

    private final DefeitoRepository defeitoRepository;
    private final DefeitoMapper defeitoMapper;

    public DefeitoDTO create(@Valid DefeitoDTO defeitoDTO) {
        Defeito defeito = defeitoMapper.toEntity(defeitoDTO);
        Defeito savedDefeito = defeitoRepository.save(defeito);
        return defeitoMapper.toDTO(savedDefeito);
    }

    public DefeitoDTO update(Long id, @Valid DefeitoDTO defeitoDTO) {
        return defeitoRepository.findById(id)
                .map(registrobusca -> {
                    BeanUtils.copyProperties(defeitoDTO, registrobusca, "id");
                    return defeitoMapper.toDTO(defeitoRepository.save(registrobusca));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable Long id){
        Defeito defeito = defeitoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        defeitoRepository.delete(defeito);
    }

    public List<DefeitoDTO> findAll() {
        return defeitoRepository.findAll().stream()
                .map(defeitoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DefeitoDTO findById(@PathVariable Long id) {
        return defeitoRepository.findById(id)
                .map(defeitoMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }
}
