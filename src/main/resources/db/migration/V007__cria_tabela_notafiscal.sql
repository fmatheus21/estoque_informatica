CREATE TABLE notafical (
  id binary(16) NOT NULL,
  id_fornecedor binary(16) NOT NULL,
  numero varchar(20) NOT NULL,
  chave_acesso varchar(44) NOT NULL,
  arquivo_xml longtext NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY chave_acesso_UNIQUE (chave_acesso),
  KEY fk_fornecedor_notafiscal_idx (id_fornecedor),
  CONSTRAINT fk_fornecedor_notafiscal FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;