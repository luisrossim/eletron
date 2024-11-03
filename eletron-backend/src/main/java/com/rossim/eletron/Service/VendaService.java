package com.rossim.eletron.Service;

import com.rossim.eletron.DTO.VendaDTO;
import com.rossim.eletron.Exception.RecordNotFoundException;
import com.rossim.eletron.Model.Venda;
import com.rossim.eletron.Repository.ClienteRepository;
import com.rossim.eletron.Repository.ReformadoRepository;
import com.rossim.eletron.Repository.PagamentoFormaRepository;
import com.rossim.eletron.Repository.VendaRepository;
import com.rossim.eletron.Mapper.VendaMapper;
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
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ReformadoRepository reformadoRepository;
    private final ClienteRepository clienteRepository;
    private final PagamentoFormaRepository pagamentoFormaRepository;
    private final VendaMapper vendaMapper;

    public VendaDTO create(@Valid VendaDTO vendaDTO) {
        var reformado = reformadoRepository.findById(vendaDTO.reformado().id())
                .orElseThrow(() -> new RecordNotFoundException(vendaDTO.reformado().id(), "reformado"));

        var cliente = clienteRepository.findById(vendaDTO.cliente().id())
                .orElseThrow(() -> new RecordNotFoundException(vendaDTO.cliente().id(), "cliente"));

        var pagamentoForma = pagamentoFormaRepository.findById(vendaDTO.pagamentoForma().id())
                .orElseThrow(() -> new RecordNotFoundException(vendaDTO.pagamentoForma().id(), "forma de pagamento"));

        Venda venda = vendaMapper.toEntity(vendaDTO);
        Venda savedVenda = vendaRepository.save(venda);

        return vendaMapper.toDTO(savedVenda);
    }


    public VendaDTO update(Long id, @Valid VendaDTO vendaDTO) {
        return vendaRepository.findById(id)
                .map(registrobusca -> {
                    var reformado = reformadoRepository.findById(vendaDTO.reformado().id())
                            .orElseThrow(() -> new RecordNotFoundException(vendaDTO.reformado().id(), "reformado"));

                    var cliente = clienteRepository.findById(vendaDTO.cliente().id())
                            .orElseThrow(() -> new RecordNotFoundException(vendaDTO.cliente().id(), "cliente"));

                    var pagamentoForma = pagamentoFormaRepository.findById(vendaDTO.pagamentoForma().id())
                            .orElseThrow(() -> new RecordNotFoundException(vendaDTO.pagamentoForma().id(), "forma de pagamento"));

                    BeanUtils.copyProperties(vendaDTO, registrobusca, "id", "efetuadaEm");

                    registrobusca.setReformado(reformado);
                    registrobusca.setCliente(cliente);
                    registrobusca.setPagamentoForma(pagamentoForma);

                    return vendaMapper.toDTO(vendaRepository.save(registrobusca));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }


    public void delete(@PathVariable Long id){
        Venda venda = vendaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        vendaRepository.delete(venda);
    }


    public List<VendaDTO> findAll() {
        return vendaRepository.findAll().stream()
                .map(vendaMapper::toDTO)
                .collect(Collectors.toList());
    }


    public VendaDTO findById(@PathVariable Long id) {
        return vendaRepository.findById(id)
                .map(vendaMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }
}