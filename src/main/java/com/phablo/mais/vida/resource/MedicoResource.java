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

import com.phablo.mais.vida.model.Medico;
import com.phablo.mais.vida.repository.MedicoRepository;
import com.phablo.mais.vida.repository.filter.MedicoFilter;
import com.phablo.mais.vida.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoResource {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private MedicoService medicoService;
	
	@PostMapping
	public ResponseEntity<Medico> salvar(@Valid @RequestBody Medico medico){
		
		Medico medicoSalvo = medicoRepository.save(medico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(medicoSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(medicoSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Medico> buscarPeloId(@PathVariable Long id){
		
		Medico medico = medicoService.buscarPeloId(id);
		
		return ResponseEntity.ok(medico);
	}
	
	@GetMapping()
	public List<Medico> listarTodos(MedicoFilter medicoFilter,Pageable pageable){
		
		List<Medico> medicos = medicoRepository.filtrar(medicoFilter, pageable);
		
		return medicos;
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Medico> atualizar (@PathVariable Long id,@Valid @RequestBody Medico medico){
		
		Medico medicoSalvo = medicoService.atualizar(id,medico);
		
		
		return ResponseEntity.ok().body(medicoSalvo);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long id) {
		medicoRepository.delete(id);
	}
}
