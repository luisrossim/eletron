package com.rossim.eletron.Repository;

import com.rossim.eletron.Model.TipoAparelho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAparelhoRepository extends JpaRepository<TipoAparelho, Long> {}
