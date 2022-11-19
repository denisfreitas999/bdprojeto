-- Criação do banco
create database projetodb;

-- Escolher o banco de dados
use projetodb;

-- Criação da tabela pessoa
create table tbpessoa (
	cpf varchar(11) primary key,
    primeiro_nome varchar(45) not null,
    sobrenome varchar(45) not null,
    telefone varchar(15)
);

create table tbcliente (
	cpf varchar(11) primary key,
    foreign key (cpf) references tbpessoa (cpf) on delete cascade
);

create table tbadministrador (
	cpf varchar(11) primary key,
    login varchar(15) not null,
    senha varchar(15) not null,
    foreign key (cpf) references tbpessoa (cpf) on delete cascade
);

create table tbfuncionario (
	cpf varchar(11) primary key,
    login varchar(15) not null,
    senha varchar(15) not null,
    adm_cpf_cadastro varchar(11) not null,
    foreign key (cpf) references tbpessoa (cpf) on delete cascade,
    foreign key (adm_cpf_cadastro) references tbadministrador (cpf) on delete cascade
);

create table tbcategoria (
	id_categoria int primary key auto_increment,
	nome varchar(45) not null
);

create table tbvenda (
	id_venda int primary key auto_increment,
    valor_total float not null,
    data_venda timestamp default current_timestamp,
    cliente_cpf varchar(11) not null,
    funcionario_cpf varchar(11) not null,
    foreign key (cliente_cpf) references tbcliente (cpf) on delete cascade,
    foreign key (funcionario_cpf) references tbfuncionario (cpf) on delete cascade
);

create table tbproduto (
	cod_produto int primary key auto_increment,
    nome varchar(45) not null,
	preco_custo float not null,
    preco_venda float not null,
    quantidade_estoque int not null,
    descricao varchar (45),
    adm_cpf_prodcadastro varchar(11) not null,
	id_categoria int not null,
    foreign key (adm_cpf_prodcadastro) references tbadministrador (cpf) on delete cascade,
	foreign key (id_categoria) references tbcategoria  (id_categoria) on delete cascade
);

create table tbitem (
	cod_item int primary key auto_increment,
    quantidade int not null,
    cod_prod int not null,
    cod_venda int not null,
    foreign key (cod_prod) references tbproduto (cod_produto) on delete cascade,
    foreign key (cod_venda) references tbvenda (id_venda) on delete cascade
);

create table tbformasdepagamento (
	id_venda int primary key,
    tipo_da_venda varchar(45),
    foreign key (id_venda) references tbvenda (id_venda) on delete cascade
);
create table tbendereco (
	id_endereco int primary key auto_increment,
    logradouro varchar(45) not null,
    estado varchar(45) not null,
    cidade varchar(45) not null,
    bairro varchar(45) not null,
    numero varchar(45) not null,
    cep varchar(45) not null,
    complemento varchar(45)
);

create table tbfornecedor (
	cnpj varchar(45) primary key,
    nome_da_empresa varchar(45) not null,
    telefone varchar(15) not null,
	email varchar(45),
    id_endereco int not null,
    foreign key (id_endereco) references tbendereco (id_endereco)
);
create table tbfornece (
	lote int primary key auto_increment,
    cnpj_fornecedor varchar(45) not null,
    cod_produto int not null,
    foreign key (cnpj_fornecedor) references tbfornecedor (cnpj) on delete cascade,
    foreign key (cod_produto) references tbproduto (cod_produto) on delete cascade
); 

create table tbdesconto (
	id_venda int primary key,
    porcentagem float,
    foreign key (id_venda) references tbvenda (id_venda) on delete cascade
);

create table tbreside (
	id_endereco int not null,
    cpf varchar(11) not null,
    foreign key (id_endereco) references tbendereco (id_endereco),
    foreign key (cpf) references tbpessoa (cpf)
);


-- Verificar tabela
describe tbpessoa;
describe tbcliente;
describe tbadministrador;
describe tbfuncionario;
describe tbcategoria;
describe tbproduto;
describe tbitem;
describe tbvenda;
describe tbdesconto;
describe tbformasdepagamento;
describe tbendereco;
describe tbfornecedor;
describe tbreside;
describe tbfornece;

-- Adição ao banco de dados
insert into tbpessoa (cpf, primeiro_nome, sobrenome, telefone) values ('01234567890', 'Marcelo', 'Henrique', '79998765432');
insert into tbpessoa (cpf, primeiro_nome, sobrenome, telefone) values ('06598756859', 'Josiel', 'Alemão', '79932165498');
insert into tbpessoa (cpf, primeiro_nome, sobrenome, telefone) values ('02365895685', 'Denisson', 'Freitas', '79985698745');
insert into tbpessoa (cpf, primeiro_nome, sobrenome, telefone) values ('12345678912', 'Administrador', 'ADM', '000000000000');
insert into tbpessoa (cpf, primeiro_nome, sobrenome, telefone) values ('98765432198', 'Funcionario', 'FUNC', '11111111111');
insert into tbpessoa (cpf, primeiro_nome, sobrenome, telefone) values ('03589525689', 'Syra', 'Suna', '79986957898');

insert into tbadministrador (cpf, login, senha) values ('01234567890', 'Mar@adm', 'Ma@adm2022');
insert into tbadministrador (cpf, login, senha) values ('12345678912', 'admin', 'admin');

insert into tbfuncionario (cpf, login, senha, adm_cpf_cadastro) values ('06598756859', 'Jos@func', 'Jos@func2022', '12345678912');
insert into tbfuncionario (cpf, login, senha, adm_cpf_cadastro) values ('98765432198', 'funcio', 'funcio', '12345678912');

insert into tbcliente (cpf) values ('02365895685');

insert into tbcategoria (nome) values ('alimentos');
insert into tbcategoria (nome) values ('papelaria');
insert into tbcategoria (nome) values ('eletrodomesticos');
insert into tbcategoria (nome) values ('eletroeletrônicos');

insert into tbproduto (nome,preco_custo,preco_venda,quantidade_estoque,descricao,adm_cpf_prodcadastro,id_categoria) 
	values ('Notebook Acer', 3000, 5000, 3, 'Notebook Acer de 1 Tera', '12345678912', 4);
insert into tbproduto (nome,preco_custo,preco_venda,quantidade_estoque,descricao,adm_cpf_prodcadastro,id_categoria) 
	values ('Resma Chamer', 15, 22, 99, 'Resma de Papel A4', '12345678912', 2);
    
insert into tbproduto (nome,preco_custo,preco_venda,quantidade_estoque,descricao,adm_cpf_prodcadastro,id_categoria) 
	values ('Celular Samsung Galaxy A21S', 1500, 2000, 25, 'Celular', '12345678912', 4);


insert into tbitem (cod_prod, quantidade, cod_venda) values (4, 2, 2);
insert into tbitem (cod_prod, quantidade, cod_venda) values (5, 2, 2);
insert into tbitem (cod_prod, quantidade, cod_venda) values (6, 1, 2);


insert into tbvenda (valor_total, cliente_cpf, funcionario_cpf) values (10110, '02365895685', '06598756859');

insert into tbdesconto (id_venda, porcentagem) values (2, 0.5);

insert into tbformasdepagamento (id_venda, tipo_da_venda) values (2, 'Dinheiro');

insert into tbendereco (logradouro,estado,cidade,bairro,numero,cep,complemento) values
	('Avenida X', 'Sergipe', 'Aracaju', 'Centro', '555', '49160-000', 'Perto do Terminal do Centro');

insert into tbendereco (logradouro,estado,cidade,bairro,numero,cep,complemento) values
	('Avenida Y', 'Sergipe', 'Nossa Senhora do Socorro', 'João Alves', '999', '49161-000', 'Perto do GBarbosa');
    
    insert into tbendereco (logradouro,estado,cidade,bairro,numero,cep,complemento) values
	(?, ?, ?, ?, ?, ?, ?);
    
insert into tbfornecedor (cnpj, nome_da_empresa,telefone,email,id_endereco) values 
	('71.794.450/0001-41', 'Computer CORP LTDA', '79986957595', 'computercorpltda@hotmail.com', 1);
    
insert into tbreside (id_endereco, cpf) values (2,0693265487);
insert into tbreside (id_endereco, cpf) values (?,?);


insert into tbfornece (fornecedor_cnpj, cod_prod) values ('71.794.450/0001-41', 1);
    
-- Verificar alterações nas tabelas
select * from tbpessoa;
select * from tbfuncionario;
select * from tbadministrador;
select * from tbcliente;
select * from tbcategoria;	
select * from tbproduto;
select * from tbitem;
select * from tbvenda;
select * from tbdesconto;
select * from tbformasdepagamento;
select * from tbfornecedor;
select * from tbendereco;
select * from tbreside;

select * from tbpessoa p 
	join tbadministrador a on (p.cpf = a.cpf);
    
select * from tbpessoa p
	join tbfuncionario f on (p.cpf = f.cpf);
    
select * from tbpessoa p 
	join tbcliente c on (p.cpf = c.cpf);

select i.cod_item from tbvenda v 
	join tbitem i on (v.id_venda = i.cod_venda) 
	join tbproduto p on (i.cod_prod = p.cod_produto);
    
select count(i.cod_item) from tbvenda v 
	join tbitem i on (v.id_venda = i.cod_venda) 
	join tbproduto p on (i.cod_prod = p.cod_produto);
    
update tbvenda set cod_item = 1 where id_venda = 1;

select * from tbfornecedor f join tbfornece fo on (f.cnpj = fo.fornecedor_cnpj);

select * from tbadministrador where login='Mar@adm' and senha='Ma@adm2022';
select * from tbfuncionario where login='Jos@func' and senha='Jos@func2022';

DELETE FROM tbadministrador WHERE cpf='1111111111';

select pe.primeiro_nome, pe.sobrenome from tbpessoa pe join tbadministrador ad on (pe.cpf = ad.cpf);
select pe.primeiro_nome from tbpessoa pe join tbadministrador ad on (pe.cpf = ad.cpf) where pe.cpf = 523256898;
select cpf from tbadministrador where login='Mar@adm' and senha='Ma@adm2022';
select * from tbadministrador;

select * from tbpessoa pe join tbadministrador ad on (pe.cpf = ad.cpf) where pe.cpf = 523256898;

select ad.login, ad.senha, pe.primeiro_nome, pe.sobrenome from tbadministrador ad join tbpessoa pe on (ad.cpf = pe.cpf) where ad.login = 'Mar@adm' and ad.senha='Ma@adm2022';
select fu.login, fu.senha, pe.primeiro_nome, pe.sobrenome from tbfuncionario fu join tbpessoa pe on (pe.cpf = fu.cpf) where fu.login = 'Jos@func' and fu.senha='Jos@func2022';

select id_endereco from tbendereco where logradouro='Avenida B' and estado='Sergipe' and cidade='Aracaju' and bairro='Getúlio Vargas'
	and numero='555' and cep='49165000';

select id_endereco from tbendereco where logradouro='Avenida C' and estado='Bahia' and cidade='Salvador' and bairro='Pelourinho'
	and numero='999' and cep='49165000';
    
select id_endereco from tbendereco where logradouro=? and estado=? and cidade=? and bairro=?
	and numero=? and cep=?;
    
select * from tbendereco where id_endereco = '7';    

select * from tbpessoa pe join tbcliente cl on (pe.cpf = cl.cpf) where cl.cpf = '0693265487';

select cpf from tbpessoa where cpf = "06859865874";

select * from tbpessoa pe join tbreside re on (pe.cpf = re.cpf) 
	join tbendereco en on (re.id_endereco = en.id_endereco) where pe.cpf = '06854265925';

select * from tbendereco;

DELETE from tbreside where id_endereco = '12';
DELETE from tbendereco where id_endereco = '12';


select * from tbreside;

select * from tbendereco;

insert into tbpessoa(cpf, primeiro_nome, sobrenome, telefone) values ('02356998575','Vanessa','da Mata', '79965698565');
insert into tbcliente(cpf) values ('02356998575');
                
drop table tbpessoa;
drop table tbcliente;
drop table tbadministrador;
drop table tbfuncionario;
drop table tbcategoria;
drop table tbproduto;
drop table tbitem;
drop table tbvenda;
drop table tbdesconto;
drop table tbformasdepagamento;
drop table tbendereco;
drop table tbfornecedor;
drop table tbreside;
drop table tbfornece;
