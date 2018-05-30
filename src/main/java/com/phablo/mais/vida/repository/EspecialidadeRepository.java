package com.phablo.mais.vida.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.phablo.mais.vida.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
	
	/*Filtrando especialidade com Spring data Jpa atraves da assinatura do metodo*/
	
	List<Especialidade> findByDescricaoStartingWith(String  descricao, Pageable pageable);

	


}
