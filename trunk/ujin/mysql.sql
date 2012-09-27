create database ujin;

grant all privileges on ujin.* to ujin@localhost identified by 'ujin' with grant option;

FLUSH PRIVILEGES;

create table ujin_finances_data(
  _idx bigint AUTO_INCREMENT primary key,
  fyear varchar(4) not null,
  fmonth varchar(2) not null,
  fday varchar(2) not null,
  fname varchar(50),
  fmoney varchar(20),
  fetc text,
  regdate timestamp,
  customerIdx int not null
);

create table ujin_customer(
_idx bigint AUTO_INCREMENT primary key,
customerName varchar(200) not null,
customerEmail varchar(100),
customerTel varchar(20),
customerAddr varchar(300),
customerBigo text
);