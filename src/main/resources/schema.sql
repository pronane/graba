CREATE TABLE users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1,
    contact VARCHAR(255),
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    active BOOLEAN,
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    auth_provider VARCHAR(255),
    city VARCHAR(255),
    created_time TIMESTAMP,
    phone_number VARCHAR(255),
    postal_code VARCHAR(255),
    reset_password_token VARCHAR(255),
    state VARCHAR(255),
    verification_code VARCHAR(255),
    PRIMARY KEY (username)
);
  
CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username)
        REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username
  on authorities (username,authority);


CREATE TABLE IF NOT EXISTS persistent_logins (
    username VARCHAR(100) NOT NULL,
    series VARCHAR(64) PRIMARY KEY,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS business_type (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(64),
    category VARCHAR(64) NOT NULL
);

 
CREATE TABLE IF NOT EXISTS business (
    id BIGINT NOT NULL AUTO_INCREMENT,
    active BOOLEAN,
    alias VARCHAR(255),
    average_rating FLOAT,
    category_id INTEGER,
    created_date TIMESTAMP,
    full_description VARCHAR(255),
    image_path VARCHAR(255),
    name VARCHAR(255),
    review_count INTEGER,
    short_description VARCHAR(255),
    type INTEGER,
    updated_date TIMESTAMP,
    URI VARCHAR(255),
    details VARCHAR(255),
    short_name VARCHAR(255),
    enabled VARCHAR(1),
    location_id BIGINT NOT NULL,
    business_type_id BIGINT NOT NULL,
    owner_name VARCHAR(255),
    PRIMARY KEY (id)
);
     
CREATE TABLE IF NOT EXISTS product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    active BOOLEAN,
    alias VARCHAR(255),
    average_rating FLOAT,
    brand_id INTEGER,
    category_id INTEGER,
    created_date TIMESTAMP,
    DISCOUNT_PERCENT FLOAT,
    full_description VARCHAR(255),
    height FLOAT,
    in_stock BOOLEAN,
    length FLOAT,
    main_image_path VARCHAR(255),
    name VARCHAR(255),
    price FLOAT,
    review_count INTEGER,
    short_description VARCHAR(255),
    type INTEGER,
    updated_date TIMESTAMP,
    weight FLOAT,
    width FLOAT,
    cart_item_id BIGINT,
    URI VARCHAR(255),
    details VARCHAR(255),
    short_name VARCHAR(255),
    enabled VARCHAR(1),
    business_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
     
CREATE TABLE IF NOT EXISTS LOCATION (
    id BIGINT NOT NULL AUTO_INCREMENT,
    country VARCHAR(255),
    name VARCHAR(255),
    created_date TIMESTAMP,
    ipAddress VARCHAR(255),
    city VARCHAR(255),
    latitude VARCHAR(255),
    longitude INTEGER,
    PRIMARY KEY (id)
);
  
CREATE TABLE category (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    description VARCHAR(255),
    alias VARCHAR(15),
    image_path VARCHAR(255),
    PRIMARY KEY (id)
);
 

--CREATE ALIAS IF NOT EXISTS FT_INIT FOR "org.h2.fulltext.FullText.init";
--CALL FT_INIT();
--CALL FT_CREATE_INDEX('PUBLIC', 'PRODUCT', NULL);

create table BRAND (id bigint not null, primary key (id));
CREATE TABLE cart_item (
    id BIGINT NOT NULL,
    quantity INTEGER,
    product_id BIGINT,
    customer_id BIGINT,
    PRIMARY KEY (id)
);
CREATE TABLE customer_order (
    id BIGINT NOT NULL,
    quantity INTEGER,
    product_id BIGINT,
    customer_id BIGINT,
    subtotal FLOAT,
    PRIMARY KEY (id)
);
CREATE TABLE order_item (
    id BIGINT NOT NULL,
    quantity INTEGER,
    product_id BIGINT,
    customer_id BIGINT,
    subtotal FLOAT,
    PRIMARY KEY (id)
);
CREATE TABLE country (
    id BIGINT NOT NULL,
    by_name_asc VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE customer (
    cart_item_id BIGINT,
    customer_id BIGINT NOT NULL,
    PRIMARY KEY (customer_id)
);
CREATE TABLE product_category (
    id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE product_image (
    id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
 
 
 --alter table user add constraint UK_pyu153aa0dw1iveq002987wj7 unique (user_role_id);
--alter table users_privileges add constraint UK_s3rcs4wqmgba3w6t6tnlj0mbm unique (product_id);
alter table customer add constraint FKgwqclqvbf3r34o7r8unn5roo3 foreign key (cart_item_id) references users;
alter table customer add constraint FKk6uxy0fl8d69lqxa9hk732ypo foreign key (customer_id) references cart_item;
--alter table product add constraint FKsc79bdh2s7ji05xwhovmhf3k2 foreign key (cart_item_id) references product;
--alter table product add constraint FKqj8ten3w52j6rpb0x88v20eiy foreign key (id) references cart_item;
--alter table user add constraint FKh2wc2dtfdo8maylne7mgubowq foreign key (user_role_id) references user_role;
--alter table user add constraint FKpjpul8usu5g8t0ft7tpmx1tof foreign key (user_id) references role;
--alter table users_privileges add constraint FKawu1071dymslj2ffwanqgkjc9 foreign key (product_id) references product_image;
--alter table users_privileges add constraint FKn5w6v9ko6yykmiywfuj9ewkbl foreign key (product_image) references product;
alter table cart_item add constraint sfadafsdafsdafds foreign key (product_id) references product;
--alter table product add FULLTEXT (name asc, alias asc, short_description asc, full_description asc)	
 
  -- User user/pass
  -- INSERT INTO users (username, password, enabled)
 --  values ('user',
  --   '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
  --   1);



  create sequence hibernate_sequence start with 3 increment by 1