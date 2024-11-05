package com.rossim.eletron.Service;

import com.rossim.eletron.DTO.ChangeStatusDTO;
import com.rossim.eletron.DTO.ServicoDTO;
import com.rossim.eletron.Enum.StatesAction;
import com.rossim.eletron.Exception.RecordNotFoundException;
import com.rossim.eletron.Model.Cliente;
import com.rossim.eletron.Model.HistoricoServico;
import com.rossim.eletron.Model.Servico;
import com.rossim.eletron.Model.StatusServico;
import com.rossim.eletron.Repository.ClienteRepository;
import com.rossim.eletron.Repository.ServicoRepository;
import com.rossim.eletron.Mapper.ServicoMapper;
import com.rossim.eletron.Utils.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RequiredArgsConstructor
@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoMapper servicoMapper;


    public ServicoDTO create(@Valid ServicoDTO servicoDTO) {
        Cliente cliente = findCliente(servicoDTO.cliente().id());

        Servico servico = servicoMapper.toEntity(servicoDTO);

        HistoricoServico historico = createHistoricoServico(servico, "Serviço cadastrado");
        servico.getHistoricoServico().add(historico);

        Servico savedServico = servicoRepository.save(servico);

        log.info("Servico cadastrado com sucesso (Cliente: " + cliente.getId() + ")");
        return servicoMapper.toDTO(savedServico);
    }


    public ServicoDTO update(Long id, @Valid ServicoDTO servicoDTO) {
        Servico registrobusca = servicoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(Constants.SERVICO_NOT_FOUND));

        Cliente cliente = findCliente(servicoDTO.cliente().id());

        BeanUtils.copyProperties(servicoDTO, registrobusca, "id", "criadoEm", "status");
        registrobusca.setCliente(cliente);

        return servicoMapper.toDTO(servicoRepository.save(registrobusca));
    }


    public ServicoDTO changeStatus(@Valid ChangeStatusDTO changeStatusDTO) {
        Servico registrobusca = servicoRepository.findById(changeStatusDTO.servicoId())
                .orElseThrow(() -> new RecordNotFoundException(Constants.SERVICO_NOT_FOUND));

        registrobusca.setStatus(
                isNextState(changeStatusDTO.action())
                ? registrobusca.nextState()
                : registrobusca.previousState()
        );

        HistoricoServico historico = createHistoricoServico(registrobusca, changeStatusDTO.comments());
        registrobusca.getHistoricoServico().add(historico);

        return servicoMapper.toDTO(servicoRepository.save(registrobusca));
    }


    public void delete(@PathVariable Long id){
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(Constants.SERVICO_NOT_FOUND));
        servicoRepository.delete(servico);
    }


    public List<ServicoDTO> findAll() {
        return servicoRepository.findAll().stream()
                .map(servicoMapper::toDTO)
                .collect(Collectors.toList());
    }


    public ServicoDTO findById(@PathVariable Long id) {
        return servicoRepository.findById(id)
                .map(servicoMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(Constants.SERVICO_NOT_FOUND));
    }


    private Cliente findCliente(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RecordNotFoundException(Constants.CLIENTE_NOT_FOUND));
    }


    private boolean isNextState(String state) {
        return state.equals(StatesAction.NEXT.getDescricao());
    }


    private HistoricoServico createHistoricoServico(Servico servico, String comments) {
        return HistoricoServico.builder()
            .comments(comments)
            .status(servico.getStatus())
            .criadoEm(LocalDateTime.now())
            .build();
    }
}