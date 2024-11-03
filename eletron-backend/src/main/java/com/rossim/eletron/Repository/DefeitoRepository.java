package com.rossim.eletron.Repository;

import com.rossim.eletron.Model.Defeito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefeitoRepository extends JpaRepository<Defeito, Long> {}
