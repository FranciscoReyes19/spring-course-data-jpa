INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 1, 'Francisco', 'Reyes', 'aresfrt@live.com','2017-08-28','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 2, 'Juan', 'Reyes', 'juanfrt@live.com','2019-03-28','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 3, 'Pedro', 'Guzman', 'pedrofrt@live.com','2019-03-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 4, 'Raul', 'Guzman', 'Raul@live.com','2019-03-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 5, 'Julian', 'Guzman', 'Julian@live.com','2019-03-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 6, 'Gael', 'Guzman', 'Gael@live.com','2019-03-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 7, 'Kike', 'Guzman', 'Kike@live.com','2019-04-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 8, 'Sofia', 'Guzman', 'Sofia@live.com','2018-03-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 9, 'Tito', 'Guzman', 'Tito@live.com','2019-12-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 10, 'Hulk', 'Guzman', 'Hulk@live.com','2019-03-19','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 11, 'Spiderman', 'Guzman', 'Spiderman@live.com','2017-03-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 12, 'Superman', 'Guzman', 'Superman@live.com','2019-06-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 13, 'Andres', 'Guzman', 'Andres@live.com','2019-03-12','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 14, 'Joaquin', 'Guzman', 'Joaquin@live.com','2014-03-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 15, 'Dulce', 'Guzman', 'Dulce@live.com','2019-04-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 16, 'Gina', 'Guzman', 'Gina@live.com','2019-11-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 17, 'Doris', 'Guzman', 'Doris@live.com','2019-03-28','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 18, 'Ulrich', 'Guzman', 'Ulrich@live.com','2019-05-08','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 19, 'Stephen', 'Guzman', 'Stephen@live.com','2019-03-21','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 20, 'Maximilian', 'Guzman', 'Maximilian@live.com','2019-02-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 21, 'Willy', 'Guzman', 'Willy@live.com','2019-03-02','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 22, 'Roberto', 'Guzman', 'Roberto@live.com','2020-03-18','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 23, 'Mariana', 'Guzman', 'Mariana@live.com','2019-01-01','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 24, 'Mario', 'Guzman', 'Mario@live.com','2019-03-04','');
INSERT INTO clientes (id, name, surname, email, created_at, photo) VALUES( 25, 'Jonathan', 'Guzman', 'Jonathan@live.com','2019-03-11','');

/* Populate tabla productos */
INSERT INTO products (name, price,created_at) VALUES('Panasonic Pantalla LCD', 25.9990, NOW());
INSERT INTO products (name, price,created_at) VALUES('Sony Camara digital DSC-W320B', 123.490, NOW());
INSERT INTO products (name, price,created_at) VALUES('Apple ipod shuffle', 14.99990 , NOW());
INSERT INTO products (name, price,created_at) VALUES('Sony Notebook z110', 37.990, NOW());
INSERT INTO products (name, price,created_at) VALUES('Hewlett Packard Multifunctional F2280', 70.990, NOW());
INSERT INTO products (name, price,created_at) VALUES('Bianchi Bycle 26 Inches', 70.990, NOW());
INSERT INTO products (name, price,created_at) VALUES('Panasonic Pantalla LCD', 25.9990, NOW());
INSERT INTO products (name, price,created_at) VALUES('Mica comoda 5 Cajones', 30.9990, NOW());

INSERT INTO invoices (description, details, cliente_id, created_at) VALUE('Factura equipos de oficina', null, 1, NOW());
INSERT INTO invoices_items (amount, invoice_id, product_id) VALUES(15, 1, 1);
INSERT INTO invoices_items (amount, invoice_id, product_id) VALUES(21, 1, 4);
INSERT INTO invoices_items (amount, invoice_id, product_id) VALUES(33, 1, 5);
INSERT INTO invoices_items (amount, invoice_id, product_id) VALUES(18, 1, 7);

INSERT INTO invoices (description, details, cliente_id, created_at) VALUE('Factura bicicleta', 'Alguna nota importante', 1, NOW());
INSERT INTO invoices_items (amount, invoice_id, product_id) VALUES(34, 2, 6);