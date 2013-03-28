create table TB_AUTH(
      username varchar_ignorecase(50) not null primary key,
      password varchar_ignorecase(50) not null,
      enabled boolean not null);

  create table TB_AUTH_ROLE (
      username varchar_ignorecase(50) not null,
      authority varchar_ignorecase(50) not null,
      constraint fk_authorities_users foreign key(username) references TB_AUTH(username));
      create unique index ix_auth_username on TB_AUTH_ROLE (username,authority);