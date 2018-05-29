CREATE TABLE medico (
    id int unsigned auto_increment,
    ativo BOOLEAN NOT NULL,
    ocupado BOOLEAN NOT NULL,
    primeiro_nome varchar(50) not null,
    ultimo_nome varchar(50) not null,
    estado char(2) not null,
    email varchar(60) not null,
    id_especialidade int unsigned not null,
    foreign key(id_especialidade) references especialidade(id),
    primary key(id)


)Engine = Innodb default charset = utf8;
