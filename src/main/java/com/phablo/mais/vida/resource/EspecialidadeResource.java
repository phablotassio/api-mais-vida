package com.phablo.mais.vida.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	
	@PostMapping 
	private ResponseEntity<Especialidade> salvar(@Valid @RequestBody Especialidade especialidade){
		
		Especialidade especialidadeSalva = especialidadeRepository.save(especialidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(especialidadeSalva).toUri();
		
		return ResponseEntity.created(uri).body(especialidadeSalva);
		
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<Especialidade> atualizar(@PathVariable Long id,@Valid @RequestBody Especialidade especialidade){
		
		Especialidade especialidadeSalva = especialidadeService.atualizar(id,especialidade);
		
		
		return ResponseEntity.ok(especialidadeSalva);
	}
	
	@GetMapping()
	private List<Especialidade> buscarTodos(){
		
		List<Especialidade> especialidades = especialidadeRepository.findAll();
		
		return  especialidades;
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Especialidade> buscarPeloId(@PathVariable Long id){
		
		Especialidade especialidade = especialidadeService.buscarPeloId(id);
		
		return ResponseEntity.ok(especialidade);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		
		especialidadeRepository.delete(id);
		
		
	}

}
