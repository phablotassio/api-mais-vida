package com.phablo.mais.vida.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.phablo.mais.vida.model.Medico;
import com.phablo.mais.vida.repository.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;

	public Medico atualizar(Long id, Medico medico) {
		
		Medico medicoSalvo = buscarPeloId(id);
		BeanUtils.copyProperties(medico, medicoSalvo,"id");
		medicoRepository.save(medicoSalvo);
		
		return medicoSalvo;
	}
	
	public Medico buscarPeloId(Long id) {
		
		Medico medico = medicoRepository.findOne(id);
		
		if(medico==null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return medico;
		
		
	}

}
