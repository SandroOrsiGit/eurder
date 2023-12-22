CREATE TABLE orders
(
    order_id    SERIAL PRIMARY KEY ,
    total_price DOUBLE PRECISION,
    fk_user_id  BIGINT
);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (user_id);

CREATE TABLE item_groups
(
    item_group_id SERIAL PRIMARY KEY ,
    amount        INTEGER                                 NOT NULL,
    shipping_date date,
    item_id       BIGINT,
    fk_order_id   BIGINT
);

ALTER TABLE item_groups
    ADD CONSTRAINT FK_ITEMGROUP_ON_FK_ORDER FOREIGN KEY (fk_order_id) REFERENCES orders (order_id);

ALTER TABLE item_groups
    ADD CONSTRAINT FK_ITEMGROUP_ON_ITEM FOREIGN KEY (item_id) REFERENCES items (item_id);