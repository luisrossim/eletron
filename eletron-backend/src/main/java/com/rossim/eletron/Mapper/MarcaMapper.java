package com.rossim.eletron.Mapper;

import com.rossim.eletron.DTO.MarcaDTO;
import com.rossim.eletron.Model.Marca;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarcaMapper extends EntityMapper<MarcaDTO, Marca> {}
