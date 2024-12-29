CREATE TABLE contato (
  id int NOT NULL AUTO_INCREMENT,
  id_pessoa int NOT NULL,
  telefone varchar(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY id_pessoa_UNIQUE (id_pessoa),
  CONSTRAINT fk_pessoa_contato FOREIGN KEY (id_pessoa) REFERENCES pessoa (id) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
