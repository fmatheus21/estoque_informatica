CREATE TABLE fornecedor (
  id binary(16) NOT NULL,
  id_pessoa int NOT NULL,
  data_alteracao datetime DEFAULT NULL,
  id_usuario_alteracao binary(16) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY id_pessoa_UNIQUE (id_pessoa),
  CONSTRAINT fk_pesspa_fornecedor FOREIGN KEY (id_pessoa) REFERENCES pessoa (id) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
