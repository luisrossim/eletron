package com.rossim.eletron.Mapper;

import com.rossim.eletron.DTO.VendaDTO;
import com.rossim.eletron.Model.Venda;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendaMapper extends EntityMapper<VendaDTO, Venda> {}
