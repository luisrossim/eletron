package com.rossim.eletron.Service;

import com.rossim.eletron.DTO.VendaDTO;
import com.rossim.eletron.Exception.BusinessRuleException;
import com.rossim.eletron.Exception.RecordNotFoundException;
import com.rossim.eletron.Model.Cliente;
import com.rossim.eletron.Model.PagamentoForma;
import com.rossim.eletron.Model.Reformado;
import com.rossim.eletron.Model.Venda;
import com.rossim.eletron.Repository.ClienteRepository;
import com.rossim.eletron.Repository.ReformadoRepository;
import com.rossim.eletron.Repository.PagamentoFormaRepository;
import com.rossim.eletron.Repository.VendaRepository;
import com.rossim.eletron.Mapper.VendaMapper;
import com.rossim.eletron.Utils.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
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
        Reformado reformado = findReformado(vendaDTO.reformado().id());
        Cliente cliente = findCliente(vendaDTO.cliente().id());
        PagamentoForma pagamentoForma = findPagamentoForma(vendaDTO.pagamentoForma().id());

        if (reformado.isVendido()) {
            throw new BusinessRuleException(Constants.REFORMADO_JA_VENDIDO);
        } else {
            reformado.setVendido(true);
            reformadoRepository.save(reformado);
        }

        Venda venda = vendaMapper.toEntity(vendaDTO);
        Venda savedVenda = vendaRepository.save(venda);

        log.info("Venda efetuada com sucesso (Reformado: " + reformado.getId() + ", Cliente: " + cliente.getId() + ")");
        return vendaMapper.toDTO(savedVenda);
    }


    public VendaDTO update(Long id, @Valid VendaDTO vendaDTO) {
        Venda registrobusca = vendaRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(Constants.VENDA_NOT_FOUND));

        Reformado reformado = findReformado(vendaDTO.reformado().id());
        Cliente cliente = findCliente(vendaDTO.cliente().id());
        PagamentoForma pagamentoForma = findPagamentoForma(vendaDTO.pagamentoForma().id());

        BeanUtils.copyProperties(vendaDTO, registrobusca, "id", "efetuadaEm");
        registrobusca.setReformado(reformado);
        registrobusca.setCliente(cliente);
        registrobusca.setPagamentoForma(pagamentoForma);

        return vendaMapper.toDTO(vendaRepository.save(registrobusca));
    }


    public void delete(@PathVariable Long id){
        Venda venda = vendaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(Constants.VENDA_NOT_FOUND));

        Reformado reformado = findReformado(venda.getReformado().getId());
        reformado.setVendido(false);
        reformadoRepository.save(reformado);

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
                .orElseThrow(() -> new RecordNotFoundException(Constants.VENDA_NOT_FOUND));
    }





    private Reformado findReformado(Long reformadoId) {
        return reformadoRepository.findById(reformadoId)
                .orElseThrow(() -> new RecordNotFoundException(Constants.REFORMADO_NOT_FOUND));
    }

    private Cliente findCliente(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RecordNotFoundException(Constants.CLIENTE_NOT_FOUND));
    }

    private PagamentoForma findPagamentoForma(Long pagamentoFormaId) {
        return pagamentoFormaRepository.findById(pagamentoFormaId)
                .orElseThrow(() -> new RecordNotFoundException(Constants.FORMA_NOT_FOUND));
    }
}