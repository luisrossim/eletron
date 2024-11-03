package com.rossim.eletron.Mapper;

import com.rossim.eletron.DTO.ClienteDTO;
import com.rossim.eletron.Model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente> {}
