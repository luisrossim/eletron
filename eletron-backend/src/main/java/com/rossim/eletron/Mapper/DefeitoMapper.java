package com.rossim.eletron.Mapper;

import com.rossim.eletron.DTO.DefeitoDTO;
import com.rossim.eletron.Model.Defeito;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DefeitoMapper extends EntityMapper<DefeitoDTO, Defeito> {}
