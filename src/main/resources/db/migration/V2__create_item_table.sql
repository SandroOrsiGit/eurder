CREATE TABLE eurder.item
(
    item_id         SERIAL PRIMARY KEY,
    name            VARCHAR(255)     NOT NULL,
    description     VARCHAR(255)     NOT NULL,
    price           DOUBLE PRECISION NOT NULL,
    amount_in_stock INTEGER          NOT NULL
);