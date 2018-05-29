package com.phablo.mais.vida.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phablo.mais.vida.model.Medico;
import com.phablo.mais.vida.repository.medico.MedicoRepositoryQuery;

public interface MedicoRepository extends JpaRepository<Medico, Long> , MedicoRepositoryQuery{

}
