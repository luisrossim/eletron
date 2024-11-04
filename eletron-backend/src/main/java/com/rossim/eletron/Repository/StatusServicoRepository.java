package com.rossim.eletron.Repository;

import com.rossim.eletron.Model.StatusServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusServicoRepository extends JpaRepository<StatusServico, Long> {}
