create table ocorrencia (
	id bigint not null auto_increment,
	entrega_id bigint not null,
	descricao varchar(255) not null,
	data_registro datetime not null,
	
	primary key (id)
); 

alter table ocorrencia add constraint fk_ocorrencia_entrega foreign key (entrega_id) references entrega (id);