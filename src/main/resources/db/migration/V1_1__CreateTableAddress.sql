CREATE TABLE address
(
    id      uuid primary key,
    street  varchar(255),
    number  bigint,
    country varchar(255),
    city    varchar(255),
    state   varchar(255),
    zip     varchar(255)
);