package com.rossim.eletron.Mapper;

import com.rossim.eletron.DTO.TipoAparelhoDTO;
import com.rossim.eletron.Model.TipoAparelho;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoAparelhoMapper extends EntityMapper<TipoAparelhoDTO, TipoAparelho> {}
