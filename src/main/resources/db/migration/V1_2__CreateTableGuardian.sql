CREATE TABLE guardian
(

    id         uuid primary key,
    first_name varchar(255),
    last_name  varchar(255),
    address_id uuid,
    email      varchar(255),
    phone      varchar(255),
    FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE CASCADE

);
