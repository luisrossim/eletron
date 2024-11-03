package com.rossim.eletron.Repository;

import com.rossim.eletron.Model.EstadoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoServicoRepository extends JpaRepository<EstadoServico, Long> {}