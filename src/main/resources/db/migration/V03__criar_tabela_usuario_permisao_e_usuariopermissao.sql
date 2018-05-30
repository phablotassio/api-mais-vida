CREATE TABLE usuario (
	id int unsigned auto_increment PRIMARY KEY,
	papel VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	id int unsigned auto_increment PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	id_permissao int unsigned NOT NULL,
    id_usuario int unsigned NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO usuario (id, papel, email, senha) values (1, 'Administrador', 'admin@maisvida.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (id, papel, email, senha) values (2, 'Secretaria', 'maria@maisvida.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');



-- inserindo permissoes
INSERT INTO permissao (id, descricao) values (1, 'ROLE_CADASTRAR_MEDICO');
INSERT INTO permissao (id, descricao) values (2, 'ROLE_PESQUISAR_MEDICO');
INSERT INTO permissao (id, descricao) values (3, 'ROLE_ATUALIZAR_MEDICO');
INSERT INTO permissao (id, descricao) values (4, 'ROLE_APAGAR_MEDICO');

-- inserindo permissoes
INSERT INTO permissao (id, descricao) values (5, 'ROLE_CADASTRAR_ESPECIALIDADE');
INSERT INTO permissao (id, descricao) values (6, 'ROLE_APAGAR_ESPECIALIDADE');
INSERT INTO permissao (id, descricao) values (7, 'ROLE_PESQUISAR_ESPECIALIDADE');
INSERT INTO permissao (id, descricao) values (8, 'ROLE_ATUALIZAR_ESPECIALIDADE');


-- adicionando permissoes ao adm
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 6);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 8);



-- adicionando permissoes a secretaria
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 6);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 8);




