CREATE TABLE IF NOT EXISTS category (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS products (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    name_product VARCHAR(255),
    available_quantity DOUBLE PRECISION NOT NULL,
    price DECIMAL(38,2),
    category_id INTEGER,
    CONSTRAINT fkCategory FOREIGN KEY (category_id) REFERENCES category(id)
    );