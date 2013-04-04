create table TB_AUTH(
      username varchar(50) not null primary key,
      password varchar(50) not null,
      enabled boolean not null);

create table TB_AUTH_ROLE (
      username varchar(50) not null,
      authority varchar(50) not null,
      constraint fk_authorities_users foreign key(username) references TB_AUTH(username));

create unique index ix_auth_username on TB_AUTH_ROLE (username,authority);
      
create table tb_example(
	seq number not null primary key,
	username varchar(50) not null,
	email varchar(255) not null,
	mobilePhone varchar(13) not null
);      