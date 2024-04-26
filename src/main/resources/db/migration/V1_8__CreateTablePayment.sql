CREATE TABLE payment
(
    id             UUID PRIMARY KEY,
    cart_id        UUID REFERENCES cart (id),
    total_amount   DECIMAL(10, 2),
    payment_status varchar(255),
    payment_date   DATE
);
