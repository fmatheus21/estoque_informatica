CREATE TABLE pessoa (
  id int NOT NULL AUTO_INCREMENT,
  id_pesoa_tipo int NOT NULL,
  nome varchar(100) NOT NULL,
  documento varchar(20) NOT NULL,
  data_criacao datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  id_usuario_criacao binary(16) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY documento_UNIQUE (documento),
  KEY fk_tipo_pessoa_idx (id_pesoa_tipo),
  CONSTRAINT fk_tipo_pessoa FOREIGN KEY (id_pesoa_tipo) REFERENCES pessoa_tipo (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

