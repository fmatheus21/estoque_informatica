CREATE TABLE produto (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(30) NOT NULL,
  ean varchar(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY nome_UNIQUE (nome),
  UNIQUE KEY ean_UNIQUE (ean)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;