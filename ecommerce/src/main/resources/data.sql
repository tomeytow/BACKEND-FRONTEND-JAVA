CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255)
);

INSERT INTO cliente (nombre, apellido, email, password)
VALUES ('Tomas', 'Gomez', 'gomeztomassalvador@gmail.com', '123');

DROP TABLE IF EXISTS producto;

CREATE TABLE producto (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10,2),
    imagen_url VARCHAR(255),
    stock INTEGER NOT NULL
);

INSERT INTO producto (id, nombre, precio, imagen_url, stock)
VALUES (101, 'Producto especial', 99.99, 'https://via.placeholder.com/150', 10);