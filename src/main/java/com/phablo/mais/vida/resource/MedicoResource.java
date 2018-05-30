package com.phablo.mais.vida.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	/*Criando recurso */
	
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MEDICO') and #oauth2.hasScope('write')")
	@PostMapping
	public ResponseEntity<Medico> salvar(@Valid @RequestBody Medico medico){
		/*salvando recurso*/
		
		Medico medicoSalvo = medicoRepository.save(medico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(medicoSalvo.getId()).toUri();
		
		/*retorna o recurso no body da requisicao, a uri correspondente do mesmo no header e 201 created*/
		
		return ResponseEntity.created(uri).body(medicoSalvo);
	}
	
	//Buscando recurso pelo id 
	/*se o recurso foi encontrado retorna 200 ok e o recurso, se nao retorna 404 not found*/
	
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICO')and #oauth2.hasScope('read')")
	@GetMapping("/{id}")
	public ResponseEntity<Medico> buscarPeloId(@PathVariable Long id){
		
		Medico medico = medicoService.buscarPeloId(id);
		
		
		return ResponseEntity.ok(medico);
	}
	
	//Buscando e filtrando recursos
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICO')and #oauth2.hasScope('read')")
	@GetMapping()
	public List<Medico> listarTodos(MedicoFilter medicoFilter,Pageable pageable){
		
		List<Medico> medicos = medicoRepository.filtrar(medicoFilter, pageable);
		
		return medicos;
		
	}
	
	//Atualizando recurso
	/* se o recuso for encontrado, ele será atualizado e retornara no body da requesicao com o status 200 ok, caso contrario retornara 404 not found*/
	
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_MEDICO')and #oauth2.hasScope('write')")
	@PutMapping("/{id}")
	public ResponseEntity<Medico> atualizar (@PathVariable Long id,@Valid @RequestBody Medico medico){
		
		Medico medicoSalvo = medicoService.atualizar(id,medico);
		
		
		return ResponseEntity.ok().body(medicoSalvo);
	}
	
	//Apagando Recurso
	/*se o recurso foi encontrado retorna 204 no content, caso contrario retorna 404 not found*/
	
	@PreAuthorize("hasAuthority('ROLE_APAGAR_MEDICO')and #oauth2.hasScope('write')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long id) {
		medicoRepository.delete(id);
	}
}
