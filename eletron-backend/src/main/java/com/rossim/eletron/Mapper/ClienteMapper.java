package com.rossim.eletron.Mapper;

import com.rossim.eletron.DTO.ClienteDTO;
import com.rossim.eletron.Model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente> {}
