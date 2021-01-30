 INSERT INTO users (username, password, enabled)
 values ('user',
   '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
   1);

INSERT INTO authorities (username, authority)
  values ('user', 'ROLE_USER');
  
  insert into location(name, city)
  values ('Castlerea', 'castlerea');
  
  insert into business_type(type, category)
  values ('Restaurant', '1');
  
    INSERT INTO business (name,short_description, short_name, alias, enabled, category_id, location_id, business_type_id, owner_name, image_path)
  values ('Bombay Palace', 'North Indian Restaurant', 'Bombay palace', 'BP', 'Y', '1', '1', '1', 'Mr BomBbay', '/images/bombay_palace/bombay_palace.png');
 
  
  INSERT INTO product (name,short_description, price, short_name, alias, enabled, category_id, business_id, main_image_path)
  values ('chicken biryani', 'nice mild dish', '9.99', 'biryani', 'bir', 'Y', '1', '1', '/images/bombay_palace/chicken_tikka.png');
 
    INSERT INTO category (name,description, alias)
  values ('South Indian', 'South Indian ', 'South Indian');
    INSERT INTO category (name,description, alias)
  values ('North Indian', 'North Indian', 'North Indian');
  
  INSERT INTO product (name,short_description, price, short_name, alias, enabled, category_id, business_id)
  values ('chicken vindaloo', 'hot and spicey', '9.99', 'vindaloo', 'vinda', 'Y', '1', '1');
  
  insert into CART_ITEM (id, quantity, product_id, customer_id) values ('1', '1', '1', '1');
  
