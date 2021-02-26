INSERT INTO tbl_categories (id, name) VALUES (1, 'shoes');
INSERT INTO tbl_categories (id, name) VALUES (2, 'books');
INSERT INTO tbl_categories (id, name) VALUES (3, 'electronics');

INSERT INTO tbl_products (id, name, description, stock, price, status, created_at, category_id)
VALUES (1, 'Adidas', 'Walk in the air with spring boot', 5, 1200.59, 'Created', '2018-09-05', 1);

INSERT INTO tbl_products (id, name, description, stock, price, status, created_at, category_id)
VALUES (2, 'Nike', 'Nike Cloud', 7, 1400.59, 'Created', '2018-11-05', 1);