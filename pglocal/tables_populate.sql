INSERT INTO users(
	id, name, role, delivery_address, favorite_products)
	VALUES ('a7b4938f-7db1-4441-8ebe-84c482adb245', 'MettleSphee', 'admin', 'Strada Metalurgiei nr.69', NULL);
INSERT INTO users(
	name, role, delivery_address, favorite_products) VALUES
	('Marin','customer','Bulevardul Stefan cel Gros 19',NULL),
	('Sorin','customer','Strada Shaormei 42',NULL),
	('Vadim','customer','Calea Revolutiei 25',NULL);

INSERT INTO vendors(
	name, owner_id, description, validated)
	VALUES ('Altex', 'a7b4938f-7db1-4441-8ebe-84c482adb245', 'Daca gasesti in alta parte mai ieftin, primesti de 2x diferenta!', TRUE),
	('Dristor Kebab', 'a7b4938f-7db1-4441-8ebe-84c482adb245', 'Ia zi boss, ce-ti aduc?', TRUE);

INSERT INTO promotions(
	discount_value, discount_type)
	VALUES (15, 'percent'),
	(20, 'currency');

INSERT INTO couriers(
	name, delivery_price)
	VALUES ('FAN Curier', 15.00),
	('Sameday', 17.50);

INSERT INTO categories(
	name, tag)
	VALUES ('IT si Tehnologie', 'it'),
	('Mancare','food');

INSERT INTO products(
	name, description, category_id, price, quantity, vendor_id, promotion_id)
	VALUES
	(   'laptop smecher',
	    'are intelu ala bun si duce si fortnait',
	    (SELECT id FROM categories
    	    WHERE tag = 'it'),
	    200,
	    2,
	    (SELECT id FROM vendors
    	    WHERE name = 'Altex'),
	    (SELECT id FROM promotions
     	    WHERE discount_value = 15
     	    AND discount_type = 'percent')
	),
	(   'shaorma',
	    'cea mai buna habibi',
	    (SELECT id FROM categories
            WHERE tag = 'food'),
	    25,
	    200,
	    (SELECT id FROM vendors
        	WHERE name = 'Dristor Kebab'),
        (SELECT id FROM promotions
         	WHERE discount_value = 15
         	AND discount_type = 'percent')
    );

INSERT INTO orders(
    user_id, product_id, quantity, courier_id, total_price)
    VALUES (
    (SELECT id FROM users
             WHERE name = 'Marin'),
    ARRAY [(SELECT id FROM products
            WHERE name = 'laptop smecher')],
    1,
    (SELECT id FROM couriers
            WHERE name = 'FAN Curier'),
    (SELECT(
        (SELECT delivery_price FROM couriers WHERE name = 'FAN Curier') +
        (SELECT price FROM products WHERE name = 'laptop smecher')
    ))
    ),
	(
    	(SELECT id FROM users
             	WHERE name = 'Sorin'),
    	ARRAY [(SELECT id FROM products
            	WHERE name = 'shaorma')],
        1,
        (SELECT id FROM couriers
            	WHERE name = 'FAN Curier'),
        (SELECT(
            (SELECT delivery_price from couriers WHERE name = 'FAN Curier') +
            (SELECT price from products WHERE name = 'shaorma')
        ))
    );