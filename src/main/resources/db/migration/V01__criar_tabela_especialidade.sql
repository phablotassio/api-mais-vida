CREATE TABLE especialidade (
    id int unsigned auto_increment,
    descricao varchar(55) not null,
    primary key(id)
)Engine = Innodb default charset = utf8;

--------- Inserindo algumas especialidades
insert into especialidade (descricao) values ('ortopedia');
insert into especialidade (descricao) values ('cardiologia');
insert into especialidade (descricao) values ('dermatologia');
insert into especialidade (descricao) values ('radiologia');
insert into especialidade (descricao) values ('oncologia');


