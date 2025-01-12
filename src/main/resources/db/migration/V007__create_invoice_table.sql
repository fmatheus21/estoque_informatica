CREATE TABLE invoice (
  id binary(16) NOT NULL,
  id_supplier binary(16) NOT NULL,
  number varchar(20) NOT NULL,
  access_key varchar(44) NOT NULL,
  xml_file longtext NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY access_key_UNIQUE (access_key),
  KEY fk_supplier_invoice_idx (id_supplier),
  CONSTRAINT fk_supplier_invoice FOREIGN KEY (id_supplier) REFERENCES supplier (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;