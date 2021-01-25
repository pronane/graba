CREATE TABLE users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  contact varchar(255), 
  email varchar(255),
   first_name varchar(255),
    last_name varchar(255), 
    active boolean,
     address_line1 varchar(255),
      address_line2 varchar(255),
       auth_provider varchar(255),
        city varchar(255),
         created_time timestamp,
          phone_number varchar(255),
           postal_code varchar(255),
            reset_password_token varchar(255), 
            state varchar(255),
             verification_code varchar(255),
    PRIMARY KEY (username)
);
  
CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
  on authorities (username,authority);


create table if not exists persistent_logins ( 
  username VARCHAR(100) not null, 
  series varchar(64) primary key, 
  token varchar(64) not null, 
  last_used timestamp not null 
);



create table if not exists product (id INTEGER not null AUTO_INCREMENT, active boolean, alias varchar(255), 
average_rating float, brand_id integer, category_id integer, created_date timestamp, DISCOUNT_PERCENT float, 
full_description varchar(255), height float, in_stock boolean, length float, main_image varchar(255), name varchar(255),
 price float, review_count integer, short_description varchar(255), type integer, updated_date timestamp, weight float,
 width float, cart_item_id bigint , URI varchar(255), details varchar(255), short_name varchar(255), primary key (id));

CREATE ALIAS IF NOT EXISTS FT_INIT FOR "org.h2.fulltext.FullText.init";
CALL FT_INIT();
CALL FT_CREATE_INDEX('PUBLIC', 'PRODUCT', NULL);

create table brand (id bigint not null, primary key (id));
create table cart_item (id bigint not null, quantity integer, product_id bigint, customer_id bigint, primary key (id));
create table customer_order (id bigint not null, quantity integer, product_id bigint, customer_id bigint, subtotal float, primary key (id));
create table order_item (id bigint not null, quantity integer, product_id bigint, customer_id bigint, subtotal float, primary key (id));
create table category (id bigint not null, primary key (id));
create table country (id bigint not null, by_name_asc varchar(255), primary key (id));
create table customer (cart_item_id bigint, customer_id bigint not null, primary key (customer_id));
 create table product_category (id bigint not null, primary key (id));
create table product_image (id bigint not null, primary key (id));
 
 
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

INSERT INTO users (username, password, enabled)
 values ('user',
   '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
   1);

INSERT INTO authorities (username, authority)
  values ('user', 'ROLE_USER');
  
  
  INSERT INTO product (name,short_description, price, short_name, alias)
  values ('chicken biryani', 'nice mild dish', '9.99', 'biryani', 'bir');

  create sequence hibernate_sequence start with 1 increment by 1