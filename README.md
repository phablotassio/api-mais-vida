# api-mais-vida
Api Mais Vida


Primeiramente executar a aplicação 

Depois abrir o postman e gerar o token

Existem 2 usuarios o: login = admin senha = admin@maisvida.com / login = maria senha = maria@maisvida.com 

o usuario admin possui todas permissoes, enquanto o segundo apenas buscar medicos e manipular as especialidades

os medicos podem ser pesquisados pelo ultimo, primeiro nome ou especialidade 

para filtrar basta passar o que deseja filtrar exemplo:

localhost:8084/medicos?ultimoNome=&page=0&size=2&sort=primeiroNome,desc

na query acima se busca o medico pelo ultimo nome e já é passado os detalhes da paginacao / ordenacao, que sao dinamicos 

as especialidades podem ser pesquisadas pelo nome 


as requisicoes do postman já estão pré configuradas bastando apenas alterar o body




