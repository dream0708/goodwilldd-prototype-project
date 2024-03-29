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

CREATE TABLE TB_MASTER_BOARD(
	bseq INT(11) NOT NULL,
	boardName VARCHAR(50) NOT NULL,
	boardDesc TEXT,
	boardListSize INT(11) DEFAULT 10,
	boardListPageSize INT(11) DEFAULT 10,
	boardType SMALLINT DEFAULT 1,
	reLevel BIGINT(11) NOT NULL DEFAULT 0,
	reStep BIGINT(11) NOT NULL DEFAULT 0
);

create unique index ix_master_board on TB_MASTER_BOARD (bseq, boardName);

CREATE SEQUENCE IF NOT EXISTS SEQ_MASTER_BOARD START WITH 1 INCREMENT BY 1;

CREATE TABLE TB_BOARD_DATA(
	bseq INT(10) NOT NULL primary key,
	boardName VARCHAR(50) NULL DEFAULT NULL,
	subject TEXT NOT NULL,
	content TEXT NULL,
	register VARCHAR(50) NULL DEFAULT NULL,
	regdate TIMESTAMP NOT NULL,
	readNum INT(11) NOT NULL DEFAULT '0',
	isEnabled TINYINT(4) NOT NULL DEFAULT '0',
	reLevel INT(11) NOT NULL DEFAULT '0',
	reStep BIGINT(11) NOT NULL DEFAULT '0');
	
create unique index ix_board_data on TB_BOARD_DATA (bseq, boardName);

CREATE SEQUENCE IF NOT EXISTS SEQ_BOARD_DATA START WITH 1 INCREMENT BY 1;
