package com.rossim.eletron.Mapper;

import com.rossim.eletron.DTO.ReformadoDTO;
import com.rossim.eletron.Model.Reformado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReformadoMapper extends EntityMapper<ReformadoDTO, Reformado> {}
