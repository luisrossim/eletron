package com.rossim.eletron.Mapper;

import com.rossim.eletron.DTO.ServicoDTO;
import com.rossim.eletron.Model.Servico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicoMapper extends EntityMapper<ServicoDTO, Servico> {}
