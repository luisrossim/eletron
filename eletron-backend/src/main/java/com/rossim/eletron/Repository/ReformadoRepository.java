package com.rossim.eletron.Repository;

import com.rossim.eletron.Model.Reformado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReformadoRepository extends JpaRepository<Reformado, Long> {}
