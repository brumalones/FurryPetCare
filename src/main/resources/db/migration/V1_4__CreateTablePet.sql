CREATE TABLE pet
(

    id          uuid primary key,
    guardian_id uuid,
    name        varchar(255),
    category    varchar(255),
    breed       varchar(255),
    age         int,
    gender      varchar(255),
    weight      int,
    behavior    varchar(255),
    FOREIGN KEY (guardian_id) REFERENCES guardian (id)
);
