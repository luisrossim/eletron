package com.rossim.eletron.Service;

import com.rossim.eletron.DTO.PagamentoFormaDTO;
import com.rossim.eletron.Exception.RecordNotFoundException;
import com.rossim.eletron.Model.PagamentoForma;
import com.rossim.eletron.Repository.PagamentoFormaRepository;
import com.rossim.eletron.Mapper.PagamentoFormaMapper;
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
public class PagamentoFormaService {

    private final PagamentoFormaRepository pagamentoFormaRepository;
    private final PagamentoFormaMapper pagamentoFormaMapper;

    public PagamentoFormaDTO create(@Valid PagamentoFormaDTO pagamentoFormaDTO) {
        PagamentoForma pagamentoForma = pagamentoFormaMapper.toEntity(pagamentoFormaDTO);
        PagamentoForma savedPagamentoForma = pagamentoFormaRepository.save(pagamentoForma);
        return pagamentoFormaMapper.toDTO(savedPagamentoForma);
    }

    public PagamentoFormaDTO update(Long id, @Valid PagamentoFormaDTO pagamentoFormaDTO) {
        return pagamentoFormaRepository.findById(id)
                .map(registrobusca -> {
                    BeanUtils.copyProperties(pagamentoFormaDTO, registrobusca, "id");
                    return pagamentoFormaMapper.toDTO(pagamentoFormaRepository.save(registrobusca));
                })
                .orElseThrow(() -> new RecordNotFoundException(Constants.FORMA_NOT_FOUND));
    }

    public void delete(@PathVariable Long id){
        PagamentoForma pagamentoForma = pagamentoFormaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(Constants.FORMA_NOT_FOUND));
        pagamentoFormaRepository.delete(pagamentoForma);
    }

    public List<PagamentoFormaDTO> findAll() {
        return pagamentoFormaRepository.findAll().stream()
                .map(pagamentoFormaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PagamentoFormaDTO findById(@PathVariable Long id) {
        return pagamentoFormaRepository.findById(id)
                .map(pagamentoFormaMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(Constants.FORMA_NOT_FOUND));
    }
}
