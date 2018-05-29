package com.phablo.mais.vida.repository.medico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.phablo.mais.vida.model.Especialidade;
import com.phablo.mais.vida.model.Especialidade_;
import com.phablo.mais.vida.model.Medico;
import com.phablo.mais.vida.model.Medico_;
import com.phablo.mais.vida.repository.filter.MedicoFilter;

public class MedicoRepositoryImpl implements MedicoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Medico> filtrar(MedicoFilter medicoFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Medico> criteria = builder.createQuery(Medico.class);
		Root<Medico> root = criteria.from(Medico.class);
		Predicate [] predicates = criarRestricoes(builder,medicoFilter,root);
		criteria.where(predicates);
		
		TypedQuery<Medico> query = manager.createQuery(criteria);
		
		
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(CriteriaBuilder builder, MedicoFilter medicoFilter, Root<Medico> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(medicoFilter.getEspecialidade())) {
			predicates.add(builder.like(builder.lower(root.get(Medico_.especialidade).get(Especialidade_.descricao)), "%"+medicoFilter.getEspecialidade().toLowerCase()+"%"));
		}
		
		if(!StringUtils.isEmpty(medicoFilter.getPrimeiroNome())) {
			predicates.add(builder.like(builder.lower(root.get(Medico_.primeiroNome)), "%"+medicoFilter.getPrimeiroNome().toLowerCase()+"%"));
		}
		
		if(!StringUtils.isEmpty(medicoFilter.getUltimoNome())) {
			predicates.add(builder.like(builder.lower(root.get(Medico_.ultimoNome)), "%"+medicoFilter.getUltimoNome().toLowerCase()+"%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
