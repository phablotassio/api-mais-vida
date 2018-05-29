package com.phablo.mais.vida.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.phablo.mais.vida.model.Especialidade;
import com.phablo.mais.vida.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	
	public List<Especialidade> buscarComFiltro(String descricao, Pageable pageable){
		
		List<Especialidade> especialidades ;
		
		if(StringUtils.isEmpty(descricao)) {
			especialidades = especialidadeRepository.findAll(pageable).getContent();
		}else {
			especialidades = especialidadeRepository.findByDescricaoStartingWith(descricao,pageable);
		}
		
		return especialidades;
		
	}
	

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
