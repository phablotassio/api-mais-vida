package com.phablo.mais.vida.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Medico.class)
public abstract class Medico_ {

	public static volatile SingularAttribute<Medico, String> ultimoNome;
	public static volatile SingularAttribute<Medico, String> primeiroNome;
	public static volatile SingularAttribute<Medico, Boolean> ativo;
	public static volatile SingularAttribute<Medico, Estado> estado;
	public static volatile SingularAttribute<Medico, Especialidade> especialidade;
	public static volatile SingularAttribute<Medico, Boolean> ocupado;
	public static volatile SingularAttribute<Medico, Long> id;
	public static volatile SingularAttribute<Medico, String> email;

}

