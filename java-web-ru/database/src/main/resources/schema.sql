-- BEGIN
DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title varchar(255) NOT NULL,
  price INT
);
-- END
