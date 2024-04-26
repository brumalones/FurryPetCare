CREATE TABLE services
(
    id          uuid primary key,
    guardian_id uuid,
    name        varchar(255),
    description varchar(255),
    price       DECIMAL(10, 2)
);
