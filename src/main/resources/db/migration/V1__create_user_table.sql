CREATE TABLE IF NOT EXISTS "address"
(
    address_id SERIAL PRIMARY KEY ,
    city VARCHAR(255) NOT NULL ,
    street_name VARCHAR(255) NOT NULL ,
    house_number VARCHAR(255) NOT NULL ,
    postal_code VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "user"
(
    user_id SERIAL PRIMARY KEY ,
    first_name VARCHAR(255) NOT NULL ,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE ,
    phone_number VARCHAR(255) NOT NULL ,
    fk_address_id INTEGER NOT NULL ,

CONSTRAINT fk_address
FOREIGN KEY (fk_address_id)
REFERENCES address (address_id)
)