
CREATE TABLE product (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  price DOUBLE NOT NULL,
  quantity INT NOT NULL
);

INSERT INTO product (name, description, price, quantity) VALUES
  ('Samsung Galaxy A71', 'SmartPhone', 9000.50, 4),
  ('Asus X513EA', 'Laptop', 23000.80, 3),
  ('Sony WF-XB700', 'Headphones', 2000.50, 1),
  ('Garmin Forerunner 55', 'Sport watch', 5200.99, 2),
  ('Homo Deus: A Brief History of Tomorrow', 'Book', 750.20, 5);