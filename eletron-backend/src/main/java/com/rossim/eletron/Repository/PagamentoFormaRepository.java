package com.rossim.eletron.Repository;

import com.rossim.eletron.Model.PagamentoForma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoFormaRepository extends JpaRepository<PagamentoForma, Long> {}
