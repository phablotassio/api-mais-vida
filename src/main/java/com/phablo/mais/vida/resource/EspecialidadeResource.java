package com.phablo.mais.vida.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.phablo.mais.vida.model.Especialidade;
import com.phablo.mais.vida.repository.EspecialidadeRepository;
import com.phablo.mais.vida.service.EspecialidadeService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeResource {
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private EspecialidadeService especialidadeService;
	
	/*Criando recurso */
	
	@PostMapping 
	private ResponseEntity<Especialidade> salvar(@Valid @RequestBody Especialidade especialidade){
		/*salvando recurso*/
		
		Especialidade especialidadeSalva = especialidadeRepository.save(especialidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(especialidadeSalva).toUri();
		
		/*retorna o recurso no body da requisicao, a uri correspondente do mesmo no header e 201 created*/
		
		return ResponseEntity.created(uri).body(especialidadeSalva);
		
	}
	
	//Atualizando recurso
	/* se o recuso for encontrado, ele será atualizado e retornara no body da requisicaocom o status 200 ok, caso contrario retornara 404 not found*/
	
	@PutMapping("/{id}")
	private ResponseEntity<Especialidade> atualizar(@PathVariable Long id,@Valid @RequestBody Especialidade especialidade){
		
		Especialidade especialidadeSalva = especialidadeService.atualizar(id,especialidade);
		
		
		return ResponseEntity.ok(especialidadeSalva);
	}
	
	//Buscando e filtrando recursos
	
	@GetMapping()
	private List<Especialidade> buscarTodos(String descricao,Pageable pageable){
		
		
		List<Especialidade> especialidades = especialidadeService.buscarComFiltro(descricao,pageable);
		
		return  especialidades;
	}
	
	//Buscando recurso pelo id
	/*se o recurso foi encontrado retorna 204 no content, caso contrario retorna 404 not found*/
	
	@GetMapping("/{id}")
	private ResponseEntity<Especialidade> buscarPeloId(@PathVariable Long id){
		
		Especialidade especialidade = especialidadeService.buscarPeloId(id);
		
		
		return ResponseEntity.ok(especialidade);
	}
	
	//Apagando Recurso
	/*se o recurso foi encontrado retorna 204 no content, se nao retorna 404 not found*/
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		
		especialidadeRepository.delete(id);
		
		
	}

}
