package com.rossim.eletron.Mapper;

import com.rossim.eletron.DTO.PagamentoFormaDTO;
import com.rossim.eletron.Model.PagamentoForma;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagamentoFormaMapper extends EntityMapper<PagamentoFormaDTO, PagamentoForma> {}
