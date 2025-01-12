CREATE TABLE invoice_item (
  id int NOT NULL AUTO_INCREMENT,
  id_invoice binary(16) NOT NULL,
  id_product int NOT NULL,
  serial_number varchar(40) NOT NULL,
  asset varchar(10),
  observation mediumtext,
  id_user_created binary(16) NOT NULL,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY serial_number_UNIQUE (serial_number),
  KEY fk_invoice_item_idx (id_invoice),
  KEY fk_product_item_idx (id_product),
  CONSTRAINT fk_product_item FOREIGN KEY (id_product) REFERENCES product (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_invoice_item FOREIGN KEY (id_invoice) REFERENCES invoice (id) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
