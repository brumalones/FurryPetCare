CREATE TABLE cart_service
(
    cart_id    uuid references cart (id),
    service_id uuid references services (id),
    primary key (cart_id, service_id)

);
