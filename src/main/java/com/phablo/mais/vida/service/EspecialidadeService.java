package com.phablo.mais.vida.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.phablo.mais.vida.model.Especialidade;
import com.phablo.mais.vida.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	

	public Especialidade atualizar(Long id, Especialidade especialidade) {
		
		Especialidade especialidadeSalva = buscarPeloId(id);
		BeanUtils.copyProperties(especialidade, especialidadeSalva, "id");
		especialidadeRepository.save(especialidadeSalva);
		
		return especialidadeSalva;
	}
	
	public Especialidade buscarPeloId(Long id) {
		
		Especialidade especialidade = especialidadeRepository.findOne(id);
		
		if(especialidade == null) {
			throw new EmptyResultDataAccessException(1);
			
		}
		
		
		return especialidade;
	}

}
