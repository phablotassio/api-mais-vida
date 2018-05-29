package com.phablo.mais.vida.repository.medico;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.phablo.mais.vida.model.Medico;
import com.phablo.mais.vida.repository.filter.MedicoFilter;

public interface MedicoRepositoryQuery {
	
	public List<Medico> filtrar(MedicoFilter medicoFilter,Pageable pageable);

}
