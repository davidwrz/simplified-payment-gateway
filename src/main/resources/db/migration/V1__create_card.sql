CREATE TABLE IF NOT EXISTS card (
    id BIGINT PRIMARY KEY,
    number VARCHAR(16) UNIQUE,
    expiration_date DATE,
    cvv VARCHAR(4)
);
