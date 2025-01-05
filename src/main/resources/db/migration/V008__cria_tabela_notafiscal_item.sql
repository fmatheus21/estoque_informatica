CREATE TABLE notafiscal_item (
  id int NOT NULL AUTO_INCREMENT,
  id_notafiscal binary(16) NOT NULL,
  id_produto int NOT NULL,
  serial_number varchar(40) NOT NULL,
  observacao mediumtext,
  id_usuario_criacao binary(16) NOT NULL,
  data_criacao datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY serial_number_UNIQUE (serial_number),
  KEY fk_notafiscal_item_idx (id_notafiscal),
  KEY fk_produto_item_idx (id_produto),
  CONSTRAINT fk_produto_item FOREIGN KEY (id_produto) REFERENCES produto (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_notafiscal_item FOREIGN KEY (id_notafiscal) REFERENCES notafical (id) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
