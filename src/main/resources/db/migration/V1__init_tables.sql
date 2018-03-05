create sequence authority_seq start with 1 increment by 1;
create sequence hibernate_sequence start with 1 increment by 1;
create sequence user_seq start with 1 increment by 1;

create table authority (id bigint not null, name varchar(50) not null, primary key (id));
create table user_authority (user_id bigint not null, authority_id bigint not null);
create table userjwt (id bigint not null, email varchar(50) not null, enabled boolean not null, firstname varchar(50) not null, lastpasswordresetdate timestamp not null, lastname varchar(50) not null, password varchar(100) not null, username varchar(50) not null, primary key (id));

alter table userjwt add constraint userjwt_uc unique (username);
alter table user_authority add constraint user_authority_authority_id_uc foreign key (authority_id) references authority;
alter table user_authority add constraint user_authority_user_id_uc foreign key (user_id) references userjwt;
