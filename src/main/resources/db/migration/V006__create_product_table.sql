CREATE TABLE product (
  id int NOT NULL AUTO_INCREMENT,
  id_manufacturer int NOT NULL,
  name varchar(200) NOT NULL,
  ean varchar(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY name_UNIQUE (name),
  UNIQUE KEY ean_UNIQUE (ean),
  KEY fk_manufacturer_product (id_manufacturer),
  CONSTRAINT fk_manufacturer_product FOREIGN KEY (id_manufacturer) REFERENCES manufacturer (id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
