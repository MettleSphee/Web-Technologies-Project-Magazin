CREATE TABLE users (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    PRIMARY KEY (id),
    name VARCHAR,
    role VARCHAR,
    delivery_address TEXT,
    favorite_products uuid[]
);

CREATE TABLE categories (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    PRIMARY KEY (id),
    name VARCHAR,
    tag VARCHAR
);

CREATE TABLE vendors (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    PRIMARY KEY (id),
    name VARCHAR,
    owner_id uuid,
    description TEXT,
    validated BOOLEAN,
    CONSTRAINT owner_vendor_constraint
        FOREIGN KEY (owner_id)
            REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE promotions (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    PRIMARY KEY (id),
    discount_value DOUBLE PRECISION,
    discount_type VARCHAR
);

CREATE TABLE products (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    PRIMARY KEY (id),
    name VARCHAR,
    description TEXT,
    category_id uuid,
    price DOUBLE PRECISION,
    quantity INTEGER,
    vendor_id uuid,
    promotion_id uuid,
    CONSTRAINT category_constraint
        FOREIGN KEY (category_id)
            REFERENCES categories(id) ON DELETE SET NULL,
    CONSTRAINT vendor_constraint
        FOREIGN KEY (vendor_id)
            REFERENCES vendors(id) ON DELETE CASCADE,
    CONSTRAINT promotion_constraint
        FOREIGN KEY (promotion_id)
            REFERENCES promotions(id) ON DELETE SET NULL
);

CREATE TABLE couriers (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    PRIMARY KEY (id),
    name VARCHAR,
    delivery_price DOUBLE PRECISION
);

CREATE TABLE orders (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    PRIMARY KEY (id),
    user_id uuid,
    product_id uuid[],
    quantity INTEGER,
    courier_id uuid,
    total_price DOUBLE PRECISION,
    CONSTRAINT curier_constraint
        FOREIGN KEY (courier_id)
            REFERENCES couriers(id) ON DELETE SET NULL,
    CONSTRAINT order_user_constraint
        FOREIGN KEY (user_id)
            REFERENCES users(id) ON DELETE CASCADE
);

