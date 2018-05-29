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
	
	
	public Especialidade buscarPeloId(Long id) {
		
		/*buscando a especialidade pelo id*/
		Especialidade especialidade = especialidadeRepository.findOne(id);
		
		if(especialidade == null) {
			/* se a especialidade nao for encontra lanca uma excecao e um  aviso: recurso nao encontrado*/
			throw new EmptyResultDataAccessException(1);
			
		}
		
		
		return especialidade;
	}
	
	public List<Especialidade> buscarComFiltro(String descricao, Pageable pageable){
		
		List<Especialidade> especialidades ;
		/*se a descricao for nula nao adiciona nenhum filtro*/
		if(StringUtils.isEmpty(descricao)) {
			especialidades = especialidadeRepository.findAll(pageable).getContent();
		}else {
			/*Filtrando pela descricao */
			especialidades = especialidadeRepository.findByDescricaoStartingWith(descricao,pageable);
		}
		
		return especialidades;
		
	}
	

	public Especialidade atualizar(Long id, Especialidade especialidade) {
		
		Especialidade especialidadeSalva = buscarPeloId(id);
		/*Atualizando o recurso*/
		BeanUtils.copyProperties(especialidade, especialidadeSalva, "id");
		especialidadeRepository.save(especialidadeSalva);
		
		return especialidadeSalva;
	}


}
