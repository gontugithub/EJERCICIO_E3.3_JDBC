DROP DATABASE IF EXISTS tienda;

CREATE DATABASE tienda;

USE tienda;

DROP TABLE IF EXISTS compra_producto;
DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS compra;
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
	id INT PRIMARY KEY auto_increment not null,
	nombre VARCHAR(100)
);

CREATE TABLE compra (
	id INT PRIMARY KEY not null auto_increment,
	concepto VARCHAR(300),
	id_cliente INT,
	FOREIGN KEY (id_cliente) REFERENCES cliente(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE producto (
	id INT(11) PRIMARY KEY,
	nombre VARCHAR(200),
	precio DECIMAL(6,2)
);

CREATE TABLE compra_producto (
	id_compra INT(11),
	id_producto INT(11),
	unidades INT(11) CHECK (unidades >= 1),
	PRIMARY KEY (id_compra, id_producto),
	FOREIGN KEY (id_compra) REFERENCES compra(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY (id_producto) REFERENCES producto(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

INSERT INTO cliente (id, nombre) VALUES
(1, 'María López Rojas'),
(2, 'Juan Fernández Luna'),
(3, 'Sergio Méndez Lago');

INSERT INTO compra (id, concepto, id_cliente) VALUES
(1, 'Regalo para mi hermana', 1),
(2, 'Regalo para mi madre', 1),
(3, 'Materiales para el trabajo', 2),
(4, 'Canjeo de cheque regalo', 3);

INSERT INTO producto (id, nombre, precio) VALUES
(1, 'Anillo de plata sencillo', '299.99'),
(2, 'Anillo de plata con zafiro', '299.99'),
(3, 'Cadena de plata', '359.99'),
(4, 'Lingotes de plata esterlina', '2150.50');

INSERT INTO compra_producto (id_compra, id_producto, unidades) VALUES
(1, 1, 1),
(1, 2, 1),
(2, 3, 1),
(3, 4, 5),
(4, 1, 1);

