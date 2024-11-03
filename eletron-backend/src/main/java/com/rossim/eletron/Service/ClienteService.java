package com.rossim.eletron.Service;

import com.rossim.eletron.DTO.ClienteDTO;
import com.rossim.eletron.Exception.RecordNotFoundException;
import com.rossim.eletron.Model.Cliente;
import com.rossim.eletron.Repository.ClienteRepository;
import com.rossim.eletron.Mapper.ClienteMapper;
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
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteDTO create(@Valid ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(savedCliente);
    }

    public ClienteDTO update(Long id, @Valid ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .map(registrobusca -> {
                    BeanUtils.copyProperties(clienteDTO, registrobusca, "id", "criadoEm");
                    return clienteMapper.toDTO(clienteRepository.save(registrobusca));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO findById(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }
}