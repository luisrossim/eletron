package com.rossim.eletron.Service;

import com.rossim.eletron.DTO.TipoAparelhoDTO;
import com.rossim.eletron.Exception.RecordNotFoundException;
import com.rossim.eletron.Model.TipoAparelho;
import com.rossim.eletron.Repository.TipoAparelhoRepository;
import com.rossim.eletron.Mapper.TipoAparelhoMapper;
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
public class TipoAparelhoService {

    private final TipoAparelhoRepository tipoAparelhoRepository;
    private final TipoAparelhoMapper tipoAparelhoMapper;

    public TipoAparelhoDTO create(@Valid TipoAparelhoDTO tipoAparelhoDTO) {
        TipoAparelho tipoAparelho = tipoAparelhoMapper.toEntity(tipoAparelhoDTO);
        TipoAparelho savedTipoAparelho = tipoAparelhoRepository.save(tipoAparelho);
        return tipoAparelhoMapper.toDTO(savedTipoAparelho);
    }

    public TipoAparelhoDTO update(Long id, @Valid TipoAparelhoDTO tipoAparelhoDTO) {
        return tipoAparelhoRepository.findById(id)
                .map(registrobusca -> {
                    BeanUtils.copyProperties(tipoAparelhoDTO, registrobusca, "id");
                    return tipoAparelhoMapper.toDTO(tipoAparelhoRepository.save(registrobusca));
                })
                .orElseThrow(() -> new RecordNotFoundException(Constants.TIPO_APARELHO_NOT_FOUND));
    }

    public void delete(@PathVariable Long id){
        TipoAparelho tipoAparelho = tipoAparelhoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(Constants.TIPO_APARELHO_NOT_FOUND));
        tipoAparelhoRepository.delete(tipoAparelho);
    }

    public List<TipoAparelhoDTO> findAll() {
        return tipoAparelhoRepository.findAll().stream()
                .map(tipoAparelhoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TipoAparelhoDTO findById(@PathVariable Long id) {
        return tipoAparelhoRepository.findById(id)
                .map(tipoAparelhoMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(Constants.TIPO_APARELHO_NOT_FOUND));
    }
}
