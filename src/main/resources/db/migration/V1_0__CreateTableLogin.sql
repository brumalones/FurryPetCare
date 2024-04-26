CREATE TABLE login
(

    id                 uuid primary key,
    username           varchar(255) not null,
    password           varchar(255) not null,
    email              varchar(255) not null,
    verification_token numeric,
    token_expiration   timestamp,
    is_enabled         boolean      not null,
    created_at         timestamp    not null,
    updated_at         timestamp,
    last_login         timestamp,
    role_authorities   varchar(255) not null

);
