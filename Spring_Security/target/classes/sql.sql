create table usuario
(
  id int auto_increment primary key,
  email    varchar(255) null,
  senha varchar(255) null,
  funcao    int          null,
  nome varchar(255) null
);